package com.ahhhh.deliv1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddServiceToProfile extends AppCompatActivity {

    private String username;
    private Service serviceSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_to_profile);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");

        final MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        final ArrayList<Service> servicesAvailable = myDBHelper.getServices();

        ListView listView = findViewById(R.id.selectable_services);
        CustomAdapter adapter = new CustomAdapter(this,servicesAvailable);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                serviceSelected = servicesAvailable.get(i);
                Context c = getApplicationContext();
                if (myDBHelper.addService(username, serviceSelected, c)){
                    Intent intent = new Intent(getApplicationContext(), ServiceSelector.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    return;
                }
            }
        });


    }



}
