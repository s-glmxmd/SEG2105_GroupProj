package com.ahhhh.deliv1;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ServiceInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info);
        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);



        ArrayList<Service> itemsList = myDBHelper.getServices();

        //you can add service like this from db
        //LOAD all FROM DB
        ListView listView = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this,itemsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),AddOrDeleteService.class);
                //intent.putExtra("name",classes[i]);
                startActivity(intent);

            }
        });


    }

/*
    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(listView, v, position, id);

        Intent intent = new Intent(this, AddOrDeleteService.class);
        startActivity(intent);


    }
*/

    }
