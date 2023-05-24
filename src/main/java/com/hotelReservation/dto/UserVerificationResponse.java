package com.hotelReservation.dto;

import com.hotelReservation.UserType;

public class UserVerificationResponse {

    private String message;

    private UserType userType;

    public UserVerificationResponse(String message, UserType userType) {
        this.message = message;
        this.userType = userType;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
