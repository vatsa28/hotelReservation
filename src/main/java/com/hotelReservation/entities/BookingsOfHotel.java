package com.hotelReservation.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "bookingsofhotels")
public class BookingsOfHotel implements Serializable{

    @EmbeddedId
    public BookingsOfHotelId bookingsOfHotelId;

    public BookingsOfHotel(){

    }

    public BookingsOfHotel(int hid, String bid){
        this.bookingsOfHotelId = new BookingsOfHotelId(hid, bid);
    }

}
