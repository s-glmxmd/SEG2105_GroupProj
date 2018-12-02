package com.ahhhh.deliv1;

import java.util.ArrayList;

public class Homeowner extends UserAccount {
    ArrayList<Booking> bookings;
    public Homeowner(String username, String password, String firstName, String lastName){
        super(username,password,firstName,lastName);
        this.bookings=new ArrayList<Booking>();
    }
    public Homeowner(String username, String password, String firstName, String lastName, ArrayList<Booking> bookings){
        super(username,password,firstName,lastName);
        this.bookings=bookings;
    }

}
