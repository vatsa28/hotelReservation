package com.hotelReservation.observer;

import com.hotelReservation.dto.HotelBookingRequest;
import com.hotelReservation.entities.HotelBooking;

/*OBSERVER PATTERN: The Subject class interface for the Subjects to implement.*/

public interface BookingSubject {
    void registerObserver(BookingObserver observer);
    void notifyObservers(HotelBooking hbr);
}
