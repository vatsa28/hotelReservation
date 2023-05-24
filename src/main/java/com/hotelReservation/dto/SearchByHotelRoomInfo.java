package com.hotelReservation.dto;

import com.hotelReservation.Constants;
import com.hotelReservation.RoomAddon;
import com.hotelReservation.RoomType;
import com.hotelReservation.entities.Hotel;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Map;

public class SearchByHotelRoomInfo {

    private Hotel hotel;

    private Map<RoomType, Integer> availableRooms;

    private Map<RoomType, Integer> roomprices;

    private Map<RoomAddon, Integer> roomaddonPrices;

    public Map<RoomType, Integer> getRoomprices() {
        return roomprices;
    }

    public void setRoomprices(Map<RoomType, Integer> roomprices) {
        this.roomprices = roomprices;
    }

    public Map<RoomAddon, Integer> getRoomaddonPrices() {
        return roomaddonPrices;
    }

    public void setRoomaddonPrices(Map<RoomAddon, Integer> roomaddonPrices) {
        this.roomaddonPrices = roomaddonPrices;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Map<RoomType, Integer> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Map<RoomType, Integer> availableRooms) {
        this.availableRooms = availableRooms;
    }

    public SearchByHotelRoomInfo(Hotel hotel, Map<RoomType, Integer> availableRooms) {
        this.hotel = hotel;
        this.availableRooms = availableRooms;
        this.roomaddonPrices = Constants.addonprices;
        this.roomprices = Constants.Roomprices;
    }

    @Override
    public String toString() {
        return "SearchByHotelRoomInfo{" +
                "hotel=" + hotel +
                ", availableRooms=" + availableRooms +
                ", roomprices=" + roomprices +
                ", roomaddonPrices=" + roomaddonPrices +
                '}';
    }
}
