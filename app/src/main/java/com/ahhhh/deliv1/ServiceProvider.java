package com.ahhhh.deliv1;


import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Pattern;

public class ServiceProvider extends UserAccount implements Serializable {

    private ArrayList<Service> services;
    private ArrayList<Availability> availabilities;
    private ArrayList<Booking> bookings;

    private String companyName;
    private ProfileAddress workerAddress;
    private String phoneNumber;
    private boolean isLicenced = false;
    private String description;


    public ServiceProvider(String username, String password, String firstName, String lastName){
        super(username,password,firstName,lastName);
        services = new ArrayList<Service>();
        availabilities = new ArrayList<Availability>();
         bookings=new ArrayList<Booking>();

    }
    public ServiceProvider(String username, String password, String firstName, String lastName, ArrayList<Booking> bookings){
        super(username,password,firstName,lastName);
        services = new ArrayList<Service>();
        availabilities = new ArrayList<Availability>();
        Collections.sort(availabilities);
        this.bookings=bookings;
    }

    public void addBooking(Booking appointment){
        bookings.add(appointment);
    }

    public void setWorkerAddress(ProfileAddress newAddress){
        workerAddress=newAddress;
    }
    public ProfileAddress getWorkerAddress(){
        return workerAddress;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setPhoneNumber(String newPhoneNumber){
        phoneNumber=newPhoneNumber;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void addServices(ArrayList<Service> servicesToAdd) {
        services.addAll(servicesToAdd);
    }

    public void addAvailability(Availability availability) {
        availabilities.add(availability);
        Collections.sort(availabilities);
    }

    public void addAvailabilities(ArrayList<Availability> availabilities) {
        this.availabilities.addAll(availabilities);
        Collections.sort(this.availabilities);
    }


    public void setLicenced(boolean val) {
        isLicenced = val;
    }

    public boolean isLicenced() {
        return isLicenced;
    }

    public void setCompanyName(String cName) {
        companyName = cName;


    }

    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public String validateProfile(){
        String error = workerAddress.validate();

        if (companyName.equals("") || phoneNumber.equals("")){
            error = "Mandatory inputs cannot be empty!";
        }
        if (phoneNumber.matches(".*[a-zA-Z]+.*")){
            error = "Phone number cannot contain letters";
        }

        return error;
    }
    public double calculateAverageServiceRating(Service service){

        double avg=0.0;
        double numRatedBookings=0.0;
        for(Booking b:bookings){
            if((b.getHomeownerRating()!=null)&&b.getService().getServiceName().equals(service.getServiceName())){
                numRatedBookings+=1.0;
                avg+=b.getHomeownerRating().getNumericRating();
            }
        }
        if(numRatedBookings==0.0){
            return -1.0;
        }
        avg=avg/numRatedBookings;
        return avg;
    }
    public Availability getNextAvailability(){
        for(Availability a:availabilities){
            //if the first date in availilities hasn't passed, it's the next availability
            //otherwise search through availabilities until one is found that hasn't passed
            if(!a.hasPassed()){
                return a;
            }
        }
        //if all the dates have passed, return the first date in availabilities (the first availability of the next week)
        if (availabilities.size()!=0) {
            return availabilities.get(0);
        } else{
            return null;
        }
    }

    public ArrayList<Availability> getAvailabilities() {
        return availabilities;
    }

    public String getDescription() {
        return description;
    }
}
