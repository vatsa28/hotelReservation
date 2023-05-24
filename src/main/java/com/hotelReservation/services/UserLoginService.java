package com.hotelReservation.services;

import com.hotelReservation.UserType;
import com.hotelReservation.entities.User;
import com.hotelReservation.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private UserRepository userRepository;

    public UserType verifyUser(String userName, String password){

        UserType usertype = null;
        var userOpt = userRepository.findById(userName);
        System.out.println();
        if(!userOpt.isEmpty() && userOpt.get().getPassword().equals(password)){
            usertype = userOpt.get().getUsertype();
        }

        System.out.println(usertype);
        return usertype;
    }

    public String getEmail(String userName){
        var userOpt = userRepository.findById(userName);
        return userOpt.get().getEmail();
    }

    public boolean checkIfUserExist(String userName){
        var userOpt = userRepository.findById(userName);
        return !userOpt.isEmpty();
    }

    public void insertUser(User user){
        userRepository.save(user);
    }
}
