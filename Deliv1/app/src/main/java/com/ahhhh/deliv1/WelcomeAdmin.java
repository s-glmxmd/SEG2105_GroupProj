package com.ahhhh.deliv1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_admin);
        Bundle passedVals = getIntent().getExtras();
        String firstName=passedVals.getString("firstName");
        ((TextView)findViewById(R.id.textViewWelcome)).setText("Welcome "+firstName+". You are signed in as an Admin.");
    }
}
