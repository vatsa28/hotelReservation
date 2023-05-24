package com.hotelReservation.controllers;

import com.hotelReservation.dto.HotelBookingRequest;
import com.hotelReservation.entities.BookingsOfHotel;
import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.entities.Room;
import com.hotelReservation.observer.BookingSubject;
import com.hotelReservation.repositories.BookingsOfHotelRepository;
import com.hotelReservation.repositories.RoomRepository;
import com.hotelReservation.services.HotelBookingService;
import com.hotelReservation.services.RoomFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
public class HotelBookingController {

    /*DEPENDENCY INJECTION: The spring framework provides a way to inject the singleton objects provided whenever needed.
    * Here it is done through the Autowired annotation.*/
    @Autowired
    HotelBookingService hotelBookingService;

    @Autowired
    BookingsOfHotelRepository bookingsOfHotelRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomFactoryService roomFactoryService;

    @Autowired
    private BookingSubject bookingSubject;


    @RequestMapping(method = RequestMethod.POST, value = "/calculatePrice")
    public ResponseEntity<String> calculatePrice(@RequestBody HotelBookingRequest bookingInfo)
    {
        double price = 0;
        for(var type:bookingInfo.getRooms().keySet()){
            Room r = roomFactoryService.createRoom(type, 0, bookingInfo.getHotelid(), true);

            for(var addon:bookingInfo.getAddons()){
                r = roomFactoryService.createRoomWithAddon(r,addon);
            }

            r.calCalculatePrice();
            price = price + r.getFinalPrice()*bookingInfo.getRooms().get(type);
        }

        return ResponseEntity.status(HttpStatus.OK).body(Double.toString(price));

    }

    @RequestMapping(method = RequestMethod.POST, value = "/bookHotel")
    public ResponseEntity<String> bookHotel(@RequestBody HotelBookingRequest bookingInfo)
    {
        System.out.println(bookingInfo);
        UUID uuid = UUID.randomUUID();
        String bookingid = uuid.toString().substring(0,8);
        bookingInfo.setHotelbookingid(bookingid);

        var hotelBookingInfo = new HotelBooking(bookingInfo);

        // select rooms and make them unavailable.
        List<Room> hotelRooms= roomRepository.searchAvailableRoomsByHotel(bookingInfo.getHotelid());
        System.out.println(hotelRooms);
        var roomsBytype = hotelRooms.stream().collect(Collectors.groupingBy(s->s.getRoomtype()));
        System.out.println(roomsBytype);
        List<Room> toBeModifiedRooms = new ArrayList<>();
        for(var type:bookingInfo.getRooms().keySet()){
            if(bookingInfo.getRooms().get(type) > 0) {
                System.out.println(bookingInfo.getRooms().get(type));
                System.out.println(roomsBytype.get(type));
                toBeModifiedRooms.addAll(roomsBytype.get(type).subList(0, bookingInfo.getRooms().get(type)));
            }
        }

        toBeModifiedRooms.forEach(s->s.setIsavailable(false));

        var tobemodifiedIds =new ArrayList<String>();
        for(var room: toBeModifiedRooms){
            tobemodifiedIds.add(Integer.toString(room.getRoomid()));
        }

        //save to database.
        roomRepository.saveAll(toBeModifiedRooms);

        hotelBookingInfo.setBookedroomsids(String.join(",",tobemodifiedIds));

        hotelBookingService.saveBooking(hotelBookingInfo);
        bookingSubject.notifyObservers(hotelBookingInfo);

        //save bookings of hotel.
        bookingsOfHotelRepository.save(new BookingsOfHotel(bookingInfo.getHotelid(), bookingid));

        return ResponseEntity.status(HttpStatus.OK).body("Booking Successful");
    }


}
