package com.ahhhh.deliv1;

import android.view.View;
import android.widget.LinearLayout;

import java.util.regex.Pattern;

public class Service {
    String title;
    double hourlyRate;

    public Service(String title, String hourlyRateStr){
        //an hourly rate is accepted as a string, this class will attempt to store it as a double
        //the validate enteredInfo class will return an error string if this there were complications
        this.title=title;
        hourlyRate=0.0;
        try {
            if(hourlyRateStr.length()!=0) {
                hourlyRate = Double.parseDouble(hourlyRateStr);
            }
        } catch (NumberFormatException e){
            this.hourlyRate=-1.0;
        }
    }

    public String validateEnteredInfo(){
        if(title.equals("")){
            return "Name of service not entered";
        }
        if(hourlyRate==0.0){
            return "Hourly rate not entered";
        }
        if(hourlyRate==-1.0){
            return "Hourly rate entered is wrong format";
        }
        return null;
    }



}
