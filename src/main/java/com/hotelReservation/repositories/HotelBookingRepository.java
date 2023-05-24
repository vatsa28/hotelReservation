package com.hotelReservation.repositories;

import com.hotelReservation.entities.HotelBooking;
import com.hotelReservation.entities.UserBooking;
import com.hotelReservation.entities.UserBookingId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelBookingRepository extends CrudRepository<HotelBooking, String> {

}
