package com.ahhhh.deliv1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

public class ServiceProviderAvailability extends AppCompatActivity {
    private CheckBox Mon,Tues,Weds,Thurs,Fri,Sat,Sun;
    private String username;
    final MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
    //final ArrayList<Service> servicesAvailable = myDBHelper.getServices();
    Context c;
    int checkCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);
        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");
        c = this.getApplicationContext();
        /*final MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        final ArrayList<Service> servicesAvailable = myDBHelper.getServices();
        final MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        final ArrayList<Service> itemsList = new ArrayList<Service>(myDBHelper.getS);*/
    }
    public void btnSubmitAvailability(View view){
        try {
            Mon = findViewById(R.id.MondayCheck);
            Tues = findViewById(R.id.TuesdayCheck);
            Weds = findViewById(R.id.WednesdayCheck);
            Thurs = findViewById(R.id.ThursdayCheck);
            Fri = findViewById(R.id.FridayCheck);
            Sat = findViewById(R.id.SatCheck);
            Sun = findViewById(R.id.SunCheck);
            if (Mon.isChecked()) {
                int MonStartHR = Integer.parseInt(((EditText) findViewById(R.id.MondayHRStart)).getText().toString());
                int MonStartMIN = Integer.parseInt(((EditText) findViewById(R.id.MondayMINStart)).getText().toString());
                int MonEndHR = Integer.parseInt(((EditText) findViewById(R.id.MondayHREnd)).getText().toString());
                int MonEndMIN = Integer.parseInt(((EditText) findViewById(R.id.MondayMINEnd)).getText().toString());
                Availability MondayAvailability = new Availability("Monday", MonStartHR, MonStartMIN, MonEndHR, MonEndMIN);
                myDBHelper.addAvailbility(username,MondayAvailability,c);
                checkCount++;
            }
            if (Tues.isChecked()) {
                int TuesStartHR = Integer.parseInt(((EditText) findViewById(R.id.TuesHRStart)).getText().toString());
                int TuesStartMIN = Integer.parseInt(((EditText) findViewById(R.id.TuesMINStart)).getText().toString());
                int TuesEndHR = Integer.parseInt(((EditText) findViewById(R.id.TuesHREnd)).getText().toString());
                int TuesEndMIN = Integer.parseInt(((EditText) findViewById(R.id.TuesMINEnd)).getText().toString());
                Availability TuesdayAvailability = new Availability("Tuesday", TuesStartHR, TuesStartMIN, TuesEndHR, TuesEndMIN);
                myDBHelper.addAvailbility(username,TuesdayAvailability,c);
                checkCount++;
            }
            if (Weds.isChecked()) {
                int WedStartHR = Integer.parseInt(((EditText) findViewById(R.id.WedsHRStart)).getText().toString());
                int WedStartMIN = Integer.parseInt(((EditText) findViewById(R.id.WedsMINStart)).getText().toString());
                int WedEndHR = Integer.parseInt(((EditText) findViewById(R.id.WedsHREnd)).getText().toString());
                int WedEndMIN = Integer.parseInt(((EditText) findViewById(R.id.WedsMINEnd)).getText().toString());
                Availability WednesdayAvailability = new Availability("Wednesday", WedStartHR, WedStartMIN, WedEndHR, WedEndMIN);
                myDBHelper.addAvailbility(username,WednesdayAvailability,c);
                checkCount++;
            }
            if (Thurs.isChecked()) {
                int ThursStartHR = Integer.parseInt(((EditText) findViewById(R.id.ThursHRStart)).getText().toString());
                int ThursStartMIN = Integer.parseInt(((EditText) findViewById(R.id.ThursMINStart)).getText().toString());
                int ThursEndHR = Integer.parseInt(((EditText) findViewById(R.id.ThursHREnd)).getText().toString());
                int ThursEndMIN = Integer.parseInt(((EditText) findViewById(R.id.ThursMINEnd)).getText().toString());
                Availability ThursdayAvailability = new Availability("Thursday", ThursStartHR, ThursStartMIN, ThursEndHR, ThursEndMIN);
                myDBHelper.addAvailbility(username,ThursdayAvailability,c);
                checkCount++;
            }
            if (Fri.isChecked()) {
                int FriStartHR = Integer.parseInt(((EditText) findViewById(R.id.FridayHRStart)).getText().toString());
                int FriStartMIN = Integer.parseInt(((EditText) findViewById(R.id.FridayMINStart)).getText().toString());
                int FriEndHR = Integer.parseInt(((EditText) findViewById(R.id.FridayHREnd)).getText().toString());
                int FriEndMIN = Integer.parseInt(((EditText) findViewById(R.id.FridayMINEnd)).getText().toString());
                Availability FridayAvailability = new Availability("Friday", FriStartHR, FriStartMIN, FriEndHR, FriEndMIN);
                myDBHelper.addAvailbility(username,FridayAvailability,c);
                checkCount++;
            }
            if (Sat.isChecked()) {
                int SatStartHR = Integer.parseInt(((EditText) findViewById(R.id.SatHRStart)).getText().toString());
                int SatStartMIN = Integer.parseInt(((EditText) findViewById(R.id.SatMINStart)).getText().toString());
                int SatEndHR = Integer.parseInt(((EditText) findViewById(R.id.SatHREnd)).getText().toString());
                int SatEndMIN = Integer.parseInt(((EditText) findViewById(R.id.SatMINEnd)).getText().toString());
                Availability SatAvailability = new Availability("Saturday", SatStartHR, SatStartMIN, SatEndHR, SatEndMIN);
                myDBHelper.addAvailbility(username,SatAvailability,c);
                checkCount++;
            }
            if (Sun.isChecked()) {
                int SunStartHR = Integer.parseInt(((EditText) findViewById(R.id.SunHRStart)).getText().toString());
                int SunStartMIN = Integer.parseInt(((EditText) findViewById(R.id.SunMINStart)).getText().toString());
                int SunEndHR = Integer.parseInt(((EditText) findViewById(R.id.SunHREnd)).getText().toString());
                int SunEndMIN = Integer.parseInt(((EditText) findViewById(R.id.SunMINEnd)).getText().toString());
                Availability SunAvailability = new Availability("Sunday", SunStartHR, SunStartMIN, SunEndHR, SunEndMIN);
                myDBHelper.addAvailbility(username,SunAvailability,c);
                checkCount++;
            }
            if (checkCount >= 0) {
                Toast.makeText(this, "Your Schedule has been saved!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "At least One Day Must Be Checked!", Toast.LENGTH_LONG).show();
            }
        }
        catch(NumberFormatException e){
            Toast.makeText(this, "Start and End time fields cannot be blank!", Toast.LENGTH_LONG).show();
        }
    }
}
