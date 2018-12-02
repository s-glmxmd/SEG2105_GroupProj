package com.ahhhh.deliv1;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Availability implements Comparable<Availability>, Serializable {
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

    @Override
    //Returns negative if this object is less than o
    //Returns 0 if this object and o are equal
    //returns positive if this object is greater than o
    public int compareTo(Availability o) {
        //compare day of the week
        if (translateDay()==o.translateDay()){
            //compare start hour
            if(getStart_hour()==o.getStart_hour()){
                //compare start minute
                if(getStart_min()==o.getStart_min()){
                    //they begin simultaneously
                    return 0;
                }
                //if the start mins are not equal, the one that starts later is the greater avail
                if(getStart_min()>o.getStart_min()){
                    return 1;
                } else{
                    return -1;
                }
            }
            //if the start hours are not equal, the one that starts later is the greater avail
            if(getStart_hour()>o.getStart_hour()){
                return 1;
            } else{
                return -1;
            }

        }
        //if they don't have equal days of the week, the later day is the greater avail
        if(translateDay()>o.translateDay()){
            return 1;
        } else{
            return -1;
        }

    }
    //this is used to compare days of the week
    public int translateDay(){
        if(this.day.equals("Monday")){
            return 1;
        }
        if(this.day.equals("Tuesday")){
            return 2;
        }
        if(this.day.equals("Wednesday")){
            return 3;
        }
        if(this.day.equals("Thursday")){
            return 4;
        }
        if(this.day.equals("Friday")){
            return 5;
        }
        if(this.day.equals("Saturday")){
            return 6;
        }
        if(this.day.equals("Sunday")){
            return 0;
        }
        //should actually return an error, but this method is only temporary until
        //a better implementation of availability is created using simple dates
        return -1;
    }


    public int getStart_hour() {
        return start_hour;
    }

    public int getStart_min() {
        return start_min;
    }

    public String getDay() {
        return day;
    }

    public boolean hasPassed(){
        Date current = new Date();
        Calendar c= Calendar.getInstance();
        c.setTime(current);
        int weekday = c.get(Calendar.DAY_OF_WEEK)-1;
        int hour = current.getHours();
        int minute = current.getMinutes();
        //if the today's day comes after the availability day, return true, otherwise investigate further
        if(weekday==translateDay()){
            if(hour==end_hour){
                if(minute==end_min){
                    //if the current time is the minute at which the SP stops working, the availability has passed
                    return true;
                } else{
                    return minute>end_min;
                }
            } else{
                return hour>end_hour;
            }
        } else{
            return weekday>translateDay();
        }
    }


    @Override
    public String toString() {
        String[] times={""+start_hour, ""+start_min, ""+end_hour, ""+end_min};

        for(int i=0;i<times.length;i++){
            if(times[i].length()<2){
                times[i]="0"+times[i];
            }
        }
        return day+", "+times[0]+":"+times[1]+" to "+times[2]+":"+times[3];

    }
}
