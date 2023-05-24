package com.hotelReservation.entities;

/*DECORATOR PATTERN: based on addons while booking new FoodRoom object will be created.*/

public class FoodRoom extends Room{

    private Room r;

    public FoodRoom() {
    }

    public FoodRoom(Room r) {
        super(r);
        this.r = r;
    }

    @Override
    public void calCalculatePrice()
    {
        this.r.calCalculatePrice();
        double d = this.r.getFinalPrice();
        this.setFinalPrice(this.price*this.getAddonPercentage()/100 + d);
    }
}
