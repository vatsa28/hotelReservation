package com.hotelReservation.repositories;

import com.hotelReservation.entities.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    @Query(value = "select * from rooms r where r.isavailable = 1", nativeQuery = true)
    List<Room> searchRooms(Date fromDate, Date toDate);

    @Query(value = "select * from rooms r where r.hotelid=?1 and r.isavailable = 1", nativeQuery = true)
    List<Room> searchAvailableRoomsByHotel(int hotelId);
}
