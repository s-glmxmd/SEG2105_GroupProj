package com.ahhhh.deliv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeServiceProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_service_provider);
        Bundle passedVals = getIntent().getExtras();
        String firstName=passedVals.getString("username");
        ((TextView)findViewById(R.id.textViewWelcome)).setText("Welcome "+firstName+". You are signed in as a Service Provider.");
    }
}
