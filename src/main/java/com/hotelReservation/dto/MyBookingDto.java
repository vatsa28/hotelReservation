package com.hotelReservation.dto;

import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.entities.UserBooking;

import java.util.List;

public class MyBookingDto {

    private List<HotelBooking> pastBookings;

    private List<HotelBooking> currentBookings;

    public List<HotelBooking> getPastBookings() {
        return pastBookings;
    }

    public void setPastBookings(List<HotelBooking> pastBookings) {
        this.pastBookings = pastBookings;
    }

    public List<HotelBooking> getCurrentBookings() {
        return currentBookings;
    }

    public void setCurrentBookings(List<HotelBooking> currentBookings) {
        this.currentBookings = currentBookings;
    }

    @Override
    public String toString() {
        return "MyBookingDto{" +
                "pastBookings=" + pastBookings +
                ", currentBookings=" + currentBookings +
                '}';
    }
}
