package com.hotelReservation.controllers;

import com.hotelReservation.RoomType;
import com.hotelReservation.dto.SearchByHotelRoomInfo;
import com.hotelReservation.dto.SearchFormRequest;
import com.hotelReservation.entities.Room;
import com.hotelReservation.repositories.HotelRepository;
import com.hotelReservation.repositories.RoomRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class RoomController {

    @Autowired
    RoomRepository roomsRepository;

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping(path="room/add")
    public @ResponseBody ResponseEntity<String> addNewRoom (@RequestBody Room room) {

        System.out.println("Req hit");
        System.out.println(room);
        roomsRepository.save(room);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping(path="room/get")
    public List<Room> getAllRooms(){

        Iterable<Room> rooms = roomsRepository.findAll();
        return StreamSupport.stream(rooms.spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping(path="room/search")
    public List<SearchByHotelRoomInfo> searchRooms(@RequestParam String fromDate, @RequestParam String toDate) throws ParseException {

        System.out.println(fromDate);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate1 = dateFormat.parse(fromDate);
        Date toDate1 = dateFormat.parse(toDate);


        List<Room> searchedRooms = roomsRepository.searchRooms(fromDate1, toDate1);

        System.out.println(searchedRooms);
        var groupingByHotels = searchedRooms.stream().collect(Collectors.groupingBy(s->s.getHotelid()));
        var roomAvailabiltyByHotelId = new HashMap<Integer, Map<RoomType, Integer>>();
        for(var hotelid:groupingByHotels.keySet()){
            roomAvailabiltyByHotelId.put(hotelid, new HashMap<>());
            for(RoomType type: RoomType.values()){
                roomAvailabiltyByHotelId.get(hotelid).put(type,0);
            }

            List<Room> rooms = groupingByHotels.get(hotelid);

            for(Room room: rooms){
                int count = roomAvailabiltyByHotelId.get(hotelid).get(room.getRoomtype());
                roomAvailabiltyByHotelId.get(hotelid).put(room.getRoomtype(), count+1);
            }
        }

        var response = new ArrayList<SearchByHotelRoomInfo>();
        for(var hotelid: roomAvailabiltyByHotelId.keySet()){
            response.add(
                    new SearchByHotelRoomInfo(hotelRepository.findById(hotelid).get()
                            ,roomAvailabiltyByHotelId.get(hotelid)));
        }

        System.out.println(response);
        return response;
    }
}