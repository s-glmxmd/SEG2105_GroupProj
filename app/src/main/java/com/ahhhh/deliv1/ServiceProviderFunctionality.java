package com.ahhhh.deliv1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceProviderFunctionality extends AppCompatActivity {
    private String username;
    public ServiceProvider tempSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_functionality);

        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");

        String [] name = myDBHelper.getName(username);
        String password = myDBHelper.findPassword(username);

        //fill profile fields
        tempSP = new ServiceProvider(username,password,name[0],name[1]);


        ((TextView)findViewById(R.id.txtUsername)).setText(tempSP.getUsername());
        ((TextView)findViewById(R.id.txtFirstName)).setText(tempSP.getFirstName());
        ((TextView)findViewById(R.id.txtLastName)).setText(tempSP.getLastName());

        Context context = getApplicationContext();
        CharSequence message = "Please complete your profile information!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();

    }


    public void btnCreateProfileClick(View view){
        Intent i = new Intent(ServiceProviderFunctionality.this, CreateSpProfile.class);
        i.putExtra("username", username);
        startActivity(i);
    }




}
