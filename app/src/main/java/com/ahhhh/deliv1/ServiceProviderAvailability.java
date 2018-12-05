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
                if(MonStartHR < 0 || MonStartHR > 12 || MonEndHR < 0 || MonEndHR > 12){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(MonEndMIN < 0 || MonEndMIN > 59 || MonStartMIN < 0 || MonStartMIN > 59){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                Availability MondayAvailability = new Availability("Monday", MonStartHR, MonStartMIN, MonEndHR, MonEndMIN);
                myDBHelper.addAvailbility(username,MondayAvailability,c);
                checkCount++;
            }
            if (Tues.isChecked()) {
                int TuesStartHR = Integer.parseInt(((EditText) findViewById(R.id.TuesHRStart)).getText().toString());
                int TuesStartMIN = Integer.parseInt(((EditText) findViewById(R.id.TuesMINStart)).getText().toString());
                int TuesEndHR = Integer.parseInt(((EditText) findViewById(R.id.TuesHREnd)).getText().toString());
                int TuesEndMIN = Integer.parseInt(((EditText) findViewById(R.id.TuesMINEnd)).getText().toString());
                if(TuesStartHR < 0 || TuesStartHR > 12 || TuesEndHR < 0 || TuesEndHR > 12){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TuesEndMIN < 0 || TuesEndMIN > 59 || TuesStartMIN < 0 || TuesStartMIN > 59){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                Availability TuesdayAvailability = new Availability("Tuesday", TuesStartHR, TuesStartMIN, TuesEndHR, TuesEndMIN);
                myDBHelper.addAvailbility(username,TuesdayAvailability,c);
                checkCount++;
            }
            if (Weds.isChecked()) {
                int WedStartHR = Integer.parseInt(((EditText) findViewById(R.id.WedsHRStart)).getText().toString());
                int WedStartMIN = Integer.parseInt(((EditText) findViewById(R.id.WedsMINStart)).getText().toString());
                int WedEndHR = Integer.parseInt(((EditText) findViewById(R.id.WedsHREnd)).getText().toString());
                int WedEndMIN = Integer.parseInt(((EditText) findViewById(R.id.WedsMINEnd)).getText().toString());
                if(WedStartHR < 0 || WedStartHR > 12 || WedEndHR < 0 || WedEndHR > 12){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(WedEndMIN < 0 || WedEndMIN > 59 || WedStartMIN < 0 || WedStartMIN > 59){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                Availability WednesdayAvailability = new Availability("Wednesday", WedStartHR, WedStartMIN, WedEndHR, WedEndMIN);
                myDBHelper.addAvailbility(username,WednesdayAvailability,c);
                checkCount++;
            }
            if (Thurs.isChecked()) {
                int ThursStartHR = Integer.parseInt(((EditText) findViewById(R.id.ThursHRStart)).getText().toString());
                int ThursStartMIN = Integer.parseInt(((EditText) findViewById(R.id.ThursMINStart)).getText().toString());
                int ThursEndHR = Integer.parseInt(((EditText) findViewById(R.id.ThursHREnd)).getText().toString());
                int ThursEndMIN = Integer.parseInt(((EditText) findViewById(R.id.ThursMINEnd)).getText().toString());
                if(ThursStartHR < 0 || ThursStartHR > 12 || ThursEndHR < 0 || ThursEndHR > 12){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(ThursEndMIN < 0 || ThursEndMIN > 59 || ThursStartMIN < 0 || ThursStartMIN > 59){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                Availability ThursdayAvailability = new Availability("Thursday", ThursStartHR, ThursStartMIN, ThursEndHR, ThursEndMIN);
                myDBHelper.addAvailbility(username,ThursdayAvailability,c);
                checkCount++;
            }
            if (Fri.isChecked()) {
                int FriStartHR = Integer.parseInt(((EditText) findViewById(R.id.FridayHRStart)).getText().toString());
                int FriStartMIN = Integer.parseInt(((EditText) findViewById(R.id.FridayMINStart)).getText().toString());
                int FriEndHR = Integer.parseInt(((EditText) findViewById(R.id.FridayHREnd)).getText().toString());
                int FriEndMIN = Integer.parseInt(((EditText) findViewById(R.id.FridayMINEnd)).getText().toString());
                if(FriStartHR < 0 || FriStartHR > 12 || FriEndHR < 0 || FriEndHR > 12){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(FriEndMIN < 0 || FriEndMIN > 59 || FriStartMIN < 0 || FriStartMIN > 59){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                Availability FridayAvailability = new Availability("Friday", FriStartHR, FriStartMIN, FriEndHR, FriEndMIN);
                myDBHelper.addAvailbility(username,FridayAvailability,c);
                checkCount++;
            }
            if (Sat.isChecked()) {
                int SatStartHR = Integer.parseInt(((EditText) findViewById(R.id.SatHRStart)).getText().toString());
                int SatStartMIN = Integer.parseInt(((EditText) findViewById(R.id.SatMINStart)).getText().toString());
                int SatEndHR = Integer.parseInt(((EditText) findViewById(R.id.SatHREnd)).getText().toString());
                int SatEndMIN = Integer.parseInt(((EditText) findViewById(R.id.SatMINEnd)).getText().toString());
                if(SatStartHR < 0 || SatStartHR > 12 || SatEndHR < 0 || SatEndHR > 12){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(SatEndMIN < 0 || SatEndMIN > 59 || SatStartMIN < 0 || SatStartMIN > 59){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                Availability SatAvailability = new Availability("Saturday", SatStartHR, SatStartMIN, SatEndHR, SatEndMIN);
                myDBHelper.addAvailbility(username,SatAvailability,c);
                checkCount++;
            }
            if (Sun.isChecked()) {
                int SunStartHR = Integer.parseInt(((EditText) findViewById(R.id.SunHRStart)).getText().toString());
                int SunStartMIN = Integer.parseInt(((EditText) findViewById(R.id.SunMINStart)).getText().toString());
                int SunEndHR = Integer.parseInt(((EditText) findViewById(R.id.SunHREnd)).getText().toString());
                int SunEndMIN = Integer.parseInt(((EditText) findViewById(R.id.SunMINEnd)).getText().toString());
                if(SunStartHR < 0 || SunStartHR > 12 || SunEndHR < 0 || SunEndHR > 12){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(SunEndMIN < 0 || SunEndMIN > 59 || SunStartMIN < 0 || SunStartMIN > 59){
                    Toast.makeText(this, "Your times must fit the specified range!", Toast.LENGTH_LONG).show();
                    return;
                }
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
