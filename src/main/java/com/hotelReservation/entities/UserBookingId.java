package com.hotelReservation.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserBookingId implements Serializable {
    private String username;
    private String bookingid;

    public UserBookingId(String username, String bookingid)
    {
        this.username = username;
        this.bookingid = bookingid;
    }

    public UserBookingId()
    {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
    }


    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    @Override
    public String toString() {
        return "UserBookingId{" +
                "username='" + username + '\'' +
                ", bookingid='" + bookingid + '\'' +
                '}';
    }
}
