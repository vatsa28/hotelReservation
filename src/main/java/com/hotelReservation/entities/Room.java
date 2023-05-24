package com.hotelReservation.entities;

import com.hotelReservation.RoomType;
import jakarta.persistence.*;

/*DECORATOR PATTERN: Base class for addons. Rooms of those type will be created based on the chosen addons.*/

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    protected Integer roomid;

    protected Integer hotelid;

    protected boolean isavailable;

    @Enumerated(EnumType.STRING)
    protected RoomType roomtype;

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Transient
    protected double finalPrice;

    public double getAddonPercentage() {
        return addonPercentage;
    }

    public void setAddonPercentage(double addonPercentage) {
        this.addonPercentage = addonPercentage;
    }

    @Transient
    protected double price;

    @Transient double addonPercentage;

    public boolean isIsavailable() {
        return isavailable;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Room() {
    }

    public Room(Room r){
        this.roomid = r.getRoomid();
        this.hotelid = r.getRoomid();
        this.isavailable = r.getIsavailable();
        this.price = r.getPrice();
        this.finalPrice = r.getFinalPrice();
    }

    public Room(Integer roomid, Integer hotelId, RoomType roomType, boolean isAvailable) {
        this.roomid = roomid;
        this.hotelid = hotelId;
        this.roomtype = roomType;
        this.isavailable = isavailable;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelId) {
        this.hotelid = hotelId;
    }


    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(RoomType roomType) {
        this.roomtype = roomType;
    }

    public boolean getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(boolean isavailable) {
        this.isavailable = isavailable;
    }

    public void calCalculatePrice(){
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomid=" + roomid +
                ", hotelid=" + hotelid +
                ", isavailable=" + isavailable +
                ", roomtype=" + roomtype +
                '}';
    }
}