package com.ahhhh.deliv1;

import java.io.Serializable;

public class FullServiceInfo implements Serializable {
    private ServiceProvider sP;
    private Service serv;
    private String servName, sPName;
    private double avgRating;
    private Availability nextAvailability;

    public FullServiceInfo(ServiceProvider sP, Service serv){
        //necessary to store the sP and serv to create bookings
        this.sP=sP;
        this.serv=serv;
        servName=serv.getServiceName();
        sPName=sP.getUsername();
        avgRating=sP.calculateAverageServiceRating(serv);
        nextAvailability=sP.getNextAvailability();
    }


    @Override
    //consider not showing next available day since it will be unclear if that is this week or next week
    public String toString() {
        if(avgRating==-1.0){
            return (servName+", "+sPName+", "+"unrated"+", "+nextAvailability.getDay());
        }
        return (servName+", "+sPName+", "+avgRating+", "+nextAvailability.getDay());
    }

    public Service getServ() {
        return serv;
    }

    public ServiceProvider getSP() {
        return sP;
    }

    public String getAvgRating() {
        if(avgRating==-1){
            return "unrated";
        }
        return avgRating+"";
    }

    public Availability getNextAvailability() {
        return nextAvailability;
    }
}
