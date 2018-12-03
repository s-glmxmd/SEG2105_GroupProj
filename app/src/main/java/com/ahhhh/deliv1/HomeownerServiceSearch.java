package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeownerServiceSearch extends AppCompatActivity {
    private String username;
    private ArrayList<ServiceProvider> SPs;
    private ArrayList<FullServiceInfo> allServices;
    private FullServiceInfo infoSelected;
    private Spinner spinnerSorter;
    private ArrayAdapter<FullServiceInfo> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_service_search);
        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");
        spinnerSorter = (Spinner) findViewById(R.id.spinnerSorter);

        ArrayList<String> spinnerSorterOptions = new ArrayList<String>();
        spinnerSorterOptions.add("Service");
        spinnerSorterOptions.add("Time");
        spinnerSorterOptions.add("Average Rating");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerSorterOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSorter.setAdapter(spinnerAdapter);

        //pull all service providers from database FEMALE
        SPs = new ArrayList<ServiceProvider>();
        //Here i've done 2 test service providers,remove this when DB is finished
        ArrayList<Service> testServices = new ArrayList<Service>();
        testServices.add(new Service("gardening", "2.0"));
        //testServices.add(new Service("snorting coke", "3.0"));

        //Female, note that i've made availability objects comparable to sort service providers by time
        Availability sp1Avail = new Availability("Tuesday", 8, 12, 12, 8);
        //Availability sp2Avail = new Availability("Tuesday", 8, 15, 12, 8);

        ServiceProvider sp1 = new ServiceProvider("uname1", "pass1", "first1", "last1");
        //ServiceProvider sp2 = new ServiceProvider("uname2", "pass2", "first2", "last2");

        sp1.addServices(testServices);
        //sp2.addServices(testServices);
        sp1.addAvailability(sp1Avail);
        //sp2.addAvailability(sp2Avail);

        SPs.add(sp1);
        //SPs.add(sp2);

        //This needs to be populated based on the list of SPs

        //*** SERVICE PROVIDERS SHOULD HAVE A VALIDATION BEFORE THEY ARE ADDED TO LIST
        allServices = new ArrayList<FullServiceInfo>();
        for (ServiceProvider sp : SPs) {
            for (Service serv : sp.getServices()) {
                allServices.add(new FullServiceInfo(sp, serv));
            }
        }

        if (allServices.size() != 0) {
            ListView listView = findViewById(R.id.listServices);
            adapter = new ArrayAdapter<FullServiceInfo>(this, android.R.layout.simple_list_item_1, android.R.id.text1, allServices);
            listView.setAdapter(adapter);
            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    infoSelected = allServices.get(i);
                    Intent intent = new Intent(HomeownerServiceSearch.this, HomeownerBookService.class);
                    intent.putExtra("infoSelected", infoSelected);
                    intent.putExtra("username", username);
                    startActivity(intent);

                }
            });

        }
        spinnerSorter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sortInfo();
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                spinnerSorter.setSelected(true);
                sortInfo();
                adapter.notifyDataSetChanged();
            }

        });
    }

    private void sortInfo() {
        //from so
        if(spinnerSorter.getSelectedItem().toString().equals("Service")){
            Collections.sort(allServices, new Comparator<FullServiceInfo>() {
                public int compare(FullServiceInfo o1, FullServiceInfo o2) {
                    return o1.getServ().getServiceName().compareTo(o2.getServ().getServiceName());
                }
            });
        } else if(spinnerSorter.getSelectedItem().toString().equals("Time")){
            Collections.sort(allServices, new Comparator<FullServiceInfo>() {
                public int compare(FullServiceInfo o1, FullServiceInfo o2) {
                    return o1.getNextAvailability().compareTo(o2.getNextAvailability());
                }
            });
            //the list must now be sorted according to their chronological order after the current time
            ArrayList<FullServiceInfo> tempList = new ArrayList<FullServiceInfo>();
            for(int i=0;i<allServices.size();i++){
                if(!allServices.get(i).getNextAvailability().hasPassed()){
                    tempList.addAll(allServices.subList(i,allServices.size()));
                    tempList.addAll(allServices.subList(0,i));
                    allServices.clear();
                    allServices.addAll(tempList);
                    return;
                }
            }
        } else if(spinnerSorter.getSelectedItem().toString().equals("Average Rating")){
            Collections.sort(allServices, new Comparator<FullServiceInfo>() {
                public int compare(FullServiceInfo o1, FullServiceInfo o2) {
                    return o1.getAvgRating().compareTo(o2.getAvgRating());
                }
            });
        }

    }
}
