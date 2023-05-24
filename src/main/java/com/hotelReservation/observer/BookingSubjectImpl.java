package com.hotelReservation.observer;

import com.hotelReservation.dto.HotelBookingRequest;
import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.services.HotelBookingService;

import java.util.ArrayList;
import java.util.List;

/*OBSERVER PATTERN: The Concrete subject  class that implements subject interface*/

public class BookingSubjectImpl implements BookingSubject{

    private List<BookingObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(BookingObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(HotelBooking hbr) {
        for (BookingObserver observer : observers) {
            observer.update(hbr);
        }
    }

}
