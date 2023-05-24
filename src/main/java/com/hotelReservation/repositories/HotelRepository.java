package com.hotelReservation.repositories;

import com.hotelReservation.entities.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {


}
