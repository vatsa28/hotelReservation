package com.hotelReservation.entities;

import com.hotelReservation.BookingStatus;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = UserBooking.TABLE_NAME)
public class UserBooking implements Serializable{
    public static final String TABLE_NAME = "bookings";


    public UserBooking(String username, String bookingId, BookingStatus status) {
        this.id = new UserBookingId(username, bookingId);
        this.bookingstatus = status;

    }

    public UserBooking() {

    }


    public UserBookingId getId() {
        return id;
    }

    public void setId(UserBookingId id) {
        this.id = id;
    }

    @EmbeddedId
    private UserBookingId id;

    public BookingStatus getBookingstatus() {
        return bookingstatus;
    }

    public void setBookingstatus(BookingStatus bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    @Enumerated(EnumType.STRING)

    private BookingStatus bookingstatus;

    @Override
    public String toString() {
        return "UserBooking{" +
                "id=" + id +
                ", bookingstatus=" + bookingstatus +
                '}';
    }
}
