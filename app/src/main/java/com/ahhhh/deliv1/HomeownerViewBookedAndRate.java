package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeownerViewBookedAndRate extends AppCompatActivity {
    String username;
    Booking selectedBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_view_booked_and_rate);
        Bundle passedVals = getIntent().getExtras();
        username=passedVals.getString("username");
        //pull all bookings for this homeowner from db FEMALE
        //the following are for test purposes, delete them after and populate the list with the actual instances

        final ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings.add(new Booking("Monday", new Service("Gardening", 25)));
        //bookings.add(new Booking("Tuesday", new Service("sniffin some more", 25)));

        if (bookings.size() != 0) {
            ListView listView = findViewById(R.id.listViewBookings);
            ArrayAdapter<Booking> adapter = new ArrayAdapter<Booking>(this, android.R.layout.simple_list_item_1, android.R.id.text1, bookings);
            listView.setAdapter(adapter);
            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedBooking = bookings.get(i);
                    Intent intent = new Intent(HomeownerViewBookedAndRate.this, RateBooking.class);
                    intent.putExtra("booking", selectedBooking);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            });

        }
    }
    public void btnReturn(View view) {
        Intent i = new Intent(HomeownerViewBookedAndRate.this, HomeownerFunctionality.class);
        i.putExtra("username", username);
        startActivity(i);
    }

}
