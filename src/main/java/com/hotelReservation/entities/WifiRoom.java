package com.hotelReservation.entities;

/*DECORATOR PATTERN: based on addons while booking new WifiRoom object will be created.*/
public class WifiRoom extends Room{

    private Room r;

    public WifiRoom() {
    }

    public WifiRoom(Room r) {
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
