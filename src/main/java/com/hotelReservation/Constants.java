package com.hotelReservation;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.EnumMap;
import java.util.HashMap;

public class Constants {

    public final static HashMap<RoomType, Integer> Roomprices = new HashMap<>() {{
        put(RoomType.Delux, 100);
        put(RoomType.Standard, 60);
        put(RoomType.Suit, 150);
        put(RoomType.Executive, 200);
    }};

    public final static HashMap<RoomAddon, Integer> addonprices = new HashMap<>() {{
        put(RoomAddon.AirConditioning, 15);
        put(RoomAddon.TV, 15);
        put(RoomAddon.ExtraBed, 10);
        put(RoomAddon.Food, 20);
        put(RoomAddon.Wifi, 10);
    }};
}
