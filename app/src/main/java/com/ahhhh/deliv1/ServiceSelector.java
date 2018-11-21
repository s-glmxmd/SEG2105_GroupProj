package com.ahhhh.deliv1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Collections.addAll;

public class ServiceSelector extends AppCompatActivity {

    private String username;
    private Service serviceSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_selector);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");


        final MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        final ArrayList<Service> itemsList = new ArrayList<Service>(myDBHelper.getServices(username));



        if (itemsList.size() != 0){
            ListView listView = findViewById(R.id.selectable_services);
            CustomAdapter adapter = new CustomAdapter(this,itemsList);
            listView.setAdapter(adapter);
            listView.setClickable(true);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    serviceSelected = itemsList.get(i);
                    Intent intent = new Intent(getApplicationContext(), DeleteServiceFromProfile.class);
                    intent.putExtra("service", serviceSelected);
                    intent.putExtra("username", username);
                    startActivity(intent);

                }
            });

        }
        else {
            Context context = getApplicationContext();
            CharSequence incorrectPassword = "Please select services to add to profile";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, incorrectPassword, duration);
            toast.show();
        }
    }

    public void btnAddService(View view ){
        Intent intent = new Intent(ServiceSelector.this, AddServiceToProfile.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
