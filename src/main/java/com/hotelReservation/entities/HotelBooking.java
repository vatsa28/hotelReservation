package com.hotelReservation.entities;

import com.hotelReservation.RoomAddon;
import com.hotelReservation.RoomType;
import com.hotelReservation.dto.HotelBookingRequest;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = HotelBooking.TABLE_NAME)
public class HotelBooking implements Serializable {

    public static final String TABLE_NAME = "hotelbooking";

    @Id
    private String hotelbookingid;

    private int hotelid;

    private String roomsbooked;

    private Date bookedon;

    public String getBookedroomsids() {
        return bookedroomsids;
    }

    public void setBookedroomsids(String bookedroomsids) {
        this.bookedroomsids = bookedroomsids;
    }

    private Date bookedfrom;

    public String getRoomsbooked() {
        return roomsbooked;
    }

    public void setRoomsbooked(String roomsbooked) {
        this.roomsbooked = roomsbooked;
    }

    private Date bookedto;

    private String username;

    private String addons;

    private String bookedroomsids;

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

    public String getAddons() {
        return addons;
    }

    public void setAddons(String addons) {
        this.addons = addons;
    }

    public HotelBooking() {
    }

    public HotelBooking(HotelBookingRequest hotelBookingRequest){
        this.hotelbookingid = hotelBookingRequest.getHotelbookingid();
        this.hotelid = hotelBookingRequest.getHotelid();
        this.roomsbooked = convertDictRoomsToString(hotelBookingRequest.getRooms());
        this.bookedon = hotelBookingRequest.getBookedon();
        this.bookedfrom = hotelBookingRequest.getBookedfrom();
        this.bookedto = hotelBookingRequest.getBookedto();
        this.username = hotelBookingRequest.getUsername();
        this.addons = convertListAddonsToString(hotelBookingRequest.getAddons());
    }

    private String convertListAddonsToString(List<RoomAddon> addons) {
        var words = new ArrayList<String>();
        for(var addon: addons){
            words.add(addon.toString());
        }

        return String.join(", ", words);
    }

    private String convertDictRoomsToString(Map<RoomType, Integer> rooms) {
        var words = new ArrayList<String>();
        System.out.println(rooms);
        for(var key: rooms.keySet()){
            int value = rooms.get(key);
            String s = key.toString() + ": "+ value;
            words.add(s);
        }

        return String.join(", ",words);
    }

    @Override
    public String toString() {
        return "HotelBooking{" +
                "hotelbookingid=" + hotelbookingid +
                ", hotelid=" + hotelid +
                ", roomids='" + roomsbooked + '\'' +
                ", bookedon=" + bookedon +
                ", bookedfrom=" + bookedfrom +
                ", bookedto=" + bookedto +
                ", username='" + username + '\'' +
                ", addons='" + addons + '\'' +
                '}';
    }
}
