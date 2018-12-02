package com.ahhhh.deliv1;

import java.io.Serializable;

public class Booking implements Serializable {
    private Rating homeownerRating;
    private String weekday;
    private Service service;

    //used if reconstructing an instance of Booking from stored data
    public Booking(String weekday, Service service, Rating homeownerRating){
        this.weekday=weekday;
        this.homeownerRating=homeownerRating;
        this.service=service;
    }

    //used when Homeowner first creates the booking
    public Booking(String weekday, Service service){
        this.weekday=weekday;
        this.service=service;
    }

    public Service getService() {
        return service;
    }

    public Rating getHomeownerRating() {
        return homeownerRating;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setHomeownerRating(Rating homeownerRating) {
        this.homeownerRating = homeownerRating;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }


    @Override
    public String toString() {
        return service.getServiceName()+" on "+weekday;
    }
}
