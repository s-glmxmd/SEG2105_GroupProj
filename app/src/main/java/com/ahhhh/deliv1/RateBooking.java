package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class RateBooking extends AppCompatActivity {
    private String username;
    private Booking selectedBooking;
    private Spinner spinnerRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_booking);
        Bundle passedVals = getIntent().getExtras();
        username=passedVals.getString("username");
        selectedBooking=(Booking) passedVals.get("booking");
        //update textviews
        ((TextView) findViewById(R.id.txtServiceName)).setText(selectedBooking.getService().getServiceName());
        ((TextView) findViewById(R.id.txtHourlyRate)).setText(selectedBooking.getService().getHourlyRate()+" $/hr");
        ((TextView) findViewById(R.id.txtDay)).setText("Scheduled for: "+selectedBooking.getWeekday());

        spinnerRating=((Spinner) findViewById(R.id.spinnerRating));
        ArrayList<Integer> spinnerOptions = new ArrayList<Integer>();
        spinnerOptions.add(1);
        spinnerOptions.add(2);
        spinnerOptions.add(3);
        spinnerOptions.add(4);
        spinnerOptions.add(5);

        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRating.setAdapter(spinnerAdapter);
    }

    //listener for btnsubmit rating
    public void btnSubmitRating(View view){
        Rating rating = new Rating(Integer.parseInt(spinnerRating.getSelectedItem().toString()),((TextView) findViewById(R.id.editTextComments)).getText().toString());

        selectedBooking.setHomeownerRating(rating);
        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);

        //FEMALE save this booking to the homeowner and to the SP

        Intent i = new Intent(RateBooking.this, HomeownerFunctionality.class);
        i.putExtra("username", username);
        startActivity(i);
    }
}
