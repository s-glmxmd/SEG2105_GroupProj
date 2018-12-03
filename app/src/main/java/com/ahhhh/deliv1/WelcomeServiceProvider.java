package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeServiceProvider extends AppCompatActivity {
    private String username;
    private String lastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_service_provider);
        Bundle passedVals = getIntent().getExtras();
        username=passedVals.getString("username");
        lastname = passedVals.getString("lastname");
        ((TextView)findViewById(R.id.textViewWelcome)).setText("Welcome "+username+". You are signed in as a Service Provider.");
    }

    public void btnProceedClick(View view){
        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        Intent i;
        if (myDBHelper.infoProvided(username)) {
            i = new Intent(WelcomeServiceProvider.this, DisplayServiceProviderProfile.class);
        }
        else {
            i = new Intent(WelcomeServiceProvider.this, ServiceProviderFunctionality.class);
        }
        i.putExtra("username", username);
        i.putExtra("lastname", lastname);
        startActivity(i);
    }
}
