package com.ahhhh.deliv1;


import java.sql.Timestamp;
import java.util.ArrayList;

public class ServiceProvider extends UserAccount {

    private ArrayList<Service> services;
    private ArrayList<Timestamp> availabilities;
    private String companyName;
    private boolean isLicenced = false;

    public ServiceProvider(String username, String password, String firstName, String lastName){
        super(username,password,firstName,lastName);
        services = new ArrayList<>();
        availabilities = new ArrayList<>();

    }

    public void addServices(ArrayList<Service> servicesToAdd) {
        services.addAll(servicesToAdd);

    }

    public void addAvailability(Timestamp time) {
        availabilities.add(time);
    }

    public void addAvailability(ArrayList<Timestamp> time) {
        availabilities.addAll(time);
    }

    public void setLicenced(boolean val) {
        isLicenced = val;
    }

    public void setCompanyName(String cName) {
        companyName = cName;


    }




}
