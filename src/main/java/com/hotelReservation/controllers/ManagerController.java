package com.hotelReservation.controllers;

import com.google.gson.Gson;
import com.hotelReservation.BookingStatus;
import com.hotelReservation.dto.CheckoutRequestDto;
import com.hotelReservation.entities.BookingsOfHotel;
import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.entities.User;
import com.hotelReservation.entities.UserBookingId;
import com.hotelReservation.repositories.BookingsOfHotelRepository;
import com.hotelReservation.repositories.HotelBookingRepository;
import com.hotelReservation.repositories.RoomRepository;
import com.hotelReservation.repositories.UserBookingRepository;
import com.hotelReservation.services.UserBookingService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ManagerController {

    @Autowired
    private BookingsOfHotelRepository bookingsOfHotelRepository;

    @Autowired
    private HotelBookingRepository hotelBookingRepository;

    @Autowired
    private UserBookingRepository userBookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/hotelBookings")
    public ResponseEntity<String> hotelBookings(@RequestParam Integer hotelid)
    {
        List<BookingsOfHotel> bookings = bookingsOfHotelRepository.findBookingsByHotelId(hotelid);
        List<HotelBooking> bookinginfo = new ArrayList<>();
        for(var booking:bookings){
            bookinginfo.add(hotelBookingRepository.findById(booking.bookingsOfHotelId.getBookingid()).get());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Gson().toJson(bookinginfo));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkout")
    public ResponseEntity<String> checkout(@RequestBody CheckoutRequestDto cr)
    {
        var bookingc = userBookingRepository.findById(new UserBookingId(cr.getUsername(), cr.getBookingid())).get();
        bookingc.setBookingstatus(BookingStatus.Past);

        userBookingRepository.save(bookingc);

        String roomids = hotelBookingRepository.findById(cr.getBookingid()).get().getBookedroomsids();

        List<Integer> ids = Arrays.asList(roomids.split(",")).stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());


        var uprooms = roomRepository.findAllById(ids);
        uprooms.forEach(s->s.setIsavailable(true));
        roomRepository.saveAll(uprooms);

        bookingsOfHotelRepository.delete(new BookingsOfHotel(cr.getHotelid(), cr.getBookingid()));
        return ResponseEntity.status(HttpStatus.OK)
                .body("Checked out for the booking:"+cr.getBookingid());
    }
}
