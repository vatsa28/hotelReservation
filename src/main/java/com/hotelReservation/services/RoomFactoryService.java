package com.hotelReservation.services;

import com.hotelReservation.Constants;
import com.hotelReservation.RoomAddon;
import com.hotelReservation.RoomType;
import com.hotelReservation.entities.*;
import org.springframework.stereotype.Service;

/*FACTORY PATTERN: The class that handles the creation of rooms.*/
@Service
public class RoomFactoryService {

    public Room createRoom(RoomType type, int roomid, int hotelid, boolean isavailable){
        var r =  new Room(roomid, hotelid, type, isavailable);
        r.setPrice(Constants.Roomprices.get(type));
        r.setFinalPrice(Constants.Roomprices.get(type));
        return r;
    }

    public Room createRoomWithAddon(Room r, RoomAddon addontype){
        Room t =null;
        switch (addontype){
            case TV:
                t = new TVRoom(r);
                t.setAddonPercentage(Constants.addonprices.get(RoomAddon.TV));
                break;
            case AirConditioning:
                t =  new AcRoom(r);
                t.setAddonPercentage(Constants.addonprices.get(RoomAddon.AirConditioning));
                break;
            case Food:
                t = new FoodRoom(r);
                t.setAddonPercentage(Constants.addonprices.get(RoomAddon.Food));
                break;
            case ExtraBed:
                t = new ExtraBed(r);
                t.setAddonPercentage(Constants.addonprices.get(RoomAddon.ExtraBed));
                break;
            case Wifi:
                t =  new WifiRoom(r);
                t.setAddonPercentage(Constants.addonprices.get(RoomAddon.Wifi));
                break;
        }

        return t;
    }
}
