package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceSelector extends AppCompatActivity {

    public String toDelete;
    private String username;
    private Service serviceSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_selector);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");

        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        final ArrayList<Service> itemsList = myDBHelper.getServices(username);

        ListView listView = findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this,itemsList);
        listView.setAdapter(adapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                serviceSelected = itemsList.get(i);
                Intent intent = new Intent(getApplicationContext(), MyDialogDeleteService.class);
                startActivity(intent);

            }
        });

        if (toDelete.equals("Yes")){
            myDBHelper.removeService(username, serviceSelected);
        }
    }
}
