package com.hotelReservation.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    private Integer hotelid;

    private String name;

    private String street;

    private String city;

    private String state;

    private Integer zipcode;

    private String country;

    public Hotel() {

    }

    public Hotel(Integer hotelId, String name, String street, String city,
                  String state, Integer zipCode, String country) {
        this.hotelid = hotelId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipCode;
        this.country = country;
    }

    public Integer getHotelId() {
        return hotelid;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelid = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipcode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipcode = zipCode;
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "hotelId=" + hotelid +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipcode +
                ", country='" + country + '\'' +
                '}';
    }

}
