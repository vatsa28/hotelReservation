package com.hotelReservation.repositories;

import com.hotelReservation.entities.UserBooking;
import com.hotelReservation.entities.UserBookingId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookingRepository extends CrudRepository<UserBooking, UserBookingId> {

    @Query(value = "SELECT * FROM bookings u WHERE u.username=?1", nativeQuery = true)
    public List<UserBooking> findByUsername(String username);
}
