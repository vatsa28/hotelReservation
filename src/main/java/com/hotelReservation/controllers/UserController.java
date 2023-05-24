package com.hotelReservation.controllers;

import com.hotelReservation.UserType;
import com.hotelReservation.dto.MyBookingDto;
import com.hotelReservation.dto.UserVerificationResponse;
import com.hotelReservation.entities.User;
import com.hotelReservation.services.UserBookingService;
import com.hotelReservation.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

@Controller
public class UserController
{
    @Autowired
    UserLoginService userservice;

    @Autowired
    UserBookingService userBookingService;

    @RequestMapping(method = RequestMethod.GET, value = "/verifyUser")
    public ResponseEntity<String> verifyUser(@RequestParam String userName, @RequestParam String password)
    {
        System.out.println(userName + password);
        UserType usertype = userservice.verifyUser(userName, password);
        if(usertype!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Gson().toJson(new UserVerificationResponse("Login Successful", usertype)));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username or password not matching");

    }

    @RequestMapping(method = RequestMethod.POST, value = "/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user)
    {
        System.out.println(user);
        if(!user.validateFields()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username and password length have to greater than 3");
        }
        else if(userservice.checkIfUserExist(user.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User already exist.");
        }

        userservice.insertUser(user);
        return ResponseEntity.status(HttpStatus.OK)
            .body("User registered succesfully");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/UserBooking/{userName}")
    public ResponseEntity<String> findPastBookings(@PathVariable String userName)
    {
        var bookings = userBookingService.findByUsername(userName);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Gson().toJson(bookings));
    }
}
