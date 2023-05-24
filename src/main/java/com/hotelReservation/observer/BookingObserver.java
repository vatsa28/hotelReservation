package com.hotelReservation.observer;

import com.hotelReservation.entities.HotelBooking;

/*OBSERVER PATTERN: The observer class interface for the observers to implement.*/

public interface BookingObserver {
    void update(HotelBooking hbr);
}
