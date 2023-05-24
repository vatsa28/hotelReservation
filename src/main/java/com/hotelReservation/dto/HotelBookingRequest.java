package com.hotelReservation.dto;

import com.hotelReservation.RoomAddon;
import com.hotelReservation.RoomType;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Date;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class HotelBookingRequest {

    private String hotelbookingid;

    private int hotelid;

    private Map<RoomType, Integer> rooms;

    private Date bookedon;

    private Date bookedfrom;

    private Date bookedto;

    private String username;

    private List<RoomAddon> addons;

    public String getHotelbookingid() {
        return hotelbookingid;
    }

    public void setHotelbookingid(String hotelbookingid) {
        this.hotelbookingid = hotelbookingid;
    }

    public int getHotelid() {
        return hotelid;
    }

    public void setHotelid(int hotelid) {
        this.hotelid = hotelid;
    }

    public Map<RoomType, Integer> getRooms() {
        return rooms;
    }

    public void setRooms(Map<RoomType, Integer> rooms) {
        this.rooms = rooms;
    }

    public Date getBookedon() {
        return bookedon;
    }

    public void setBookedon(Date bookedon) {
        this.bookedon = bookedon;
    }

    public Date getBookedfrom() {
        return bookedfrom;
    }

    public void setBookedfrom(Date bookedfrom) {
        this.bookedfrom = bookedfrom;
    }

    public Date getBookedto() {
        return bookedto;
    }

    public void setBookedto(Date bookedto) {
        this.bookedto = bookedto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoomAddon> getAddons() {
        return addons;
    }

    public void setAddons(List<RoomAddon> addons) {
        this.addons = addons;
    }

    @Override
    public String toString() {
        return "HotelBookingRequest{" +
                "hotelbookingid='" + hotelbookingid + '\'' +
                ", hotelid=" + hotelid +
                ", rooms=" + rooms +
                ", bookedon=" + bookedon +
                ", bookedfrom=" + bookedfrom +
                ", bookedto=" + bookedto +
                ", username='" + username + '\'' +
                ", addons=" + addons +
                '}';
    }
}
