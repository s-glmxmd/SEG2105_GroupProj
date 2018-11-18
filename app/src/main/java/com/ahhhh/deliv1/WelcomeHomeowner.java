package com.ahhhh.deliv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeHomeowner extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_homeowner);
        Bundle passedVals = getIntent().getExtras();
        String username=passedVals.getString("username");
        ((TextView)findViewById(R.id.textViewWelcome)).setText("Welcome "+username+". You are signed in as an Homeowner.");
    }
}
