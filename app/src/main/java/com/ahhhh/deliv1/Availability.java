package com.ahhhh.deliv1;

public class Availability {
    String day;
    int start_hour;
    int start_min;
    int end_hour;
    int end_min;
    public Availability(String day, int start_hour, int start_min, int end_hour, int end_min){
        this.day = day;
        this.start_hour = start_hour;
        this.start_min = start_min;
        this.end_hour = end_hour;
        this.end_min = end_min;
    }
}
