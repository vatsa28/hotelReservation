package com.hotelReservation.services;

import com.hotelReservation.BookingStatus;
import com.hotelReservation.dto.MyBookingDto;
import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.entities.UserBooking;
import com.hotelReservation.repositories.HotelBookingRepository;
import com.hotelReservation.repositories.UserBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBookingService {

    @Autowired
    private UserBookingRepository userBookingRepository;

    @Autowired
    private HotelBookingRepository hotelBookingRepository;


    public MyBookingDto findByUsername(String username){
        System.out.println(username);
        var ub = userBookingRepository.findByUsername(username);

        var allBookings = new MyBookingDto();
        if(!ub.isEmpty()){
            var bookingMap = ub.stream().collect(Collectors.groupingBy(UserBooking::getBookingstatus));
            List<UserBooking> currBookings = bookingMap.getOrDefault(BookingStatus.Current, new ArrayList<>());
            List<UserBooking> pastBookings = bookingMap.getOrDefault(BookingStatus.Past, new ArrayList<>());

            allBookings.setCurrentBookings(getHotelBookingById(currBookings));
            allBookings.setPastBookings(getHotelBookingById(pastBookings));
        }

        return allBookings;
    }

    private List<HotelBooking> getHotelBookingById(List<UserBooking> bookingsList) {

        List<HotelBooking> hotelBookingList = new ArrayList<>();
        for (UserBooking currBooking : bookingsList) {
            hotelBookingList.add(hotelBookingRepository.findById(currBooking.getId().getBookingid()).get());
        }
        return hotelBookingList;
    }

    public void saveUserBooking(UserBooking userbooking)
    {
        userBookingRepository.save(userbooking);
    }
}
