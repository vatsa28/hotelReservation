package com.hotelReservation.services;

import com.hotelReservation.BookingStatus;
import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.entities.UserBooking;
import com.hotelReservation.observer.BookingSubject;
import com.hotelReservation.repositories.HotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HotelBookingService {

    @Autowired
    HotelBookingRepository hotelBookingRepository;

    @Autowired
    UserBookingService userBookingService;


    public void saveBooking(HotelBooking hotelBooking)
    {

        hotelBookingRepository.save(hotelBooking);
        userBookingService.saveUserBooking(
                new UserBooking(hotelBooking.getUsername(),hotelBooking.getHotelbookingid(), BookingStatus.Current));
    }

}
