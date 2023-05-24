package com.hotelReservation.entities;

/*DECORATOR PATTERN: based on addons while booking new AcRoom object will be created.*/
public class AcRoom extends Room{

    private Room r;
    public AcRoom() {
    }

    public AcRoom(Room r) {
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
