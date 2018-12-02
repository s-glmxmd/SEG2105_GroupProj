package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeownerBookService extends AppCompatActivity {
    private String username;
    private FullServiceInfo infoSelected;
    private ServiceProvider sP;
    private Service serv;
    private Spinner spinnerSorter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_book_service);
        Bundle passedVals = getIntent().getExtras();
        username=passedVals.getString("username");
        infoSelected = (FullServiceInfo) passedVals.get("infoSelected");
        sP=infoSelected.getSP();
        serv=infoSelected.getServ();

        ArrayList<String> spinnerOptions = new ArrayList<String>();
        for(Availability a:sP.getAvailabilities()){
            spinnerOptions.add(a.toString());
        }
        spinnerSorter = (Spinner) findViewById(R.id.spinnerAvailabilities);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSorter.setAdapter(spinnerAdapter);

        ((TextView) findViewById(R.id.txtServiceName)).setText(serv.getServiceName());
        ((TextView) findViewById(R.id.txtHourlyRate)).setText(serv.getHourlyRate()+"");
        ((TextView) findViewById(R.id.txtServiceProviderPersonalName)).setText(sP.getFirstName()+" "+sP.getLastName());
        ((TextView) findViewById(R.id.txtAvgRating)).setText(infoSelected.getAvgRating());
        ((TextView) findViewById(R.id.txtDescription)).setText(sP.getDescription());
    }

    public void btnBookServiceClick(View view){
        //FEMALE
        //creating a booking which must now be saved to the homeowner and the service provider
        Availability avail=null;
        for(Availability a:sP.getAvailabilities()){
            if(a.toString().equals(spinnerSorter.getSelectedItem().toString())){
                avail=a;
            }
        }
        Booking booking = new Booking(avail.getDay(), serv);
        //return to Homeowner Functionality
        Intent i = new Intent(HomeownerBookService.this, HomeownerFunctionality.class);


        i.putExtra("username", username);
        startActivity(i);
    }
}
