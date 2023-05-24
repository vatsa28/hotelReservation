package com.hotelReservation.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookingsOfHotelId implements Serializable {

    private int hotelid;

    private String bookingid;

    public BookingsOfHotelId() {
    }

    public BookingsOfHotelId(int hotelid, String bookingid) {
        this.hotelid = hotelid;
        this.bookingid = bookingid;
    }

    public int getHotelid() {
        return hotelid;
    }

    public void setHotelid(int hotelid) {
        this.hotelid = hotelid;
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }
}
