package com.hotelReservation.repositories;

import com.hotelReservation.entities.BookingsOfHotel;
import com.hotelReservation.entities.BookingsOfHotelId;
import com.hotelReservation.entities.UserBooking;
import com.hotelReservation.entities.UserBookingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingsOfHotelRepository extends CrudRepository<BookingsOfHotel, BookingsOfHotelId> {

    @Query(value = "select * from bookingsofhotels b where b.hotelid=?1", nativeQuery = true)
    List<BookingsOfHotel> findBookingsByHotelId(int hotelId);
}
