package com.ahhhh.deliv1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class AddOrDeleteService extends AppCompatActivity {

    public String oldDescription ;
    public Double oldRateForService;
    public String newDescription;
    public Double newHourlyRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_delete_service);
        Bundle passedVals = getIntent().getExtras();
        oldDescription = passedVals.getString("name");
        ((TextView)findViewById(R.id.name)).setText(oldDescription);
        oldRateForService = passedVals.getDouble("hourlyRate");
        ((TextView)findViewById(R.id.hourlyRate)).setText(oldRateForService.toString());
    }
    public void buttonEditService(View view){
        newDescription = ((EditText)findViewById(R.id.newText)).getText().toString();
        newHourlyRate = Double.parseDouble( ((EditText)findViewById(R.id.newRate)).getText().toString());


        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);

        myDBHelper.updateServiceInfo(oldDescription, oldRateForService, newDescription, newHourlyRate);
        Context context = getApplicationContext();
        CharSequence incorrectPassword = "Service info was updated!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, incorrectPassword, duration);
        toast.show();


        startActivity(new Intent(AddOrDeleteService.this, ServiceInfoActivity.class));


        //edit from db
    }
    public void buttonRemoveService(View view){
        //remove from DB

        oldDescription = ((EditText)findViewById(R.id.name)).getText().toString();
        oldRateForService = Double.parseDouble( ((EditText)findViewById(R.id.hourlyRate)).getText().toString());

        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);

        myDBHelper.removeService(oldDescription, oldRateForService);
        Context context = getApplicationContext();
        CharSequence incorrectPassword = "Service was deleted!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, incorrectPassword, duration);
        toast.show();
        startActivity(new Intent(AddOrDeleteService.this, ServiceInfoActivity.class));

    }
}
