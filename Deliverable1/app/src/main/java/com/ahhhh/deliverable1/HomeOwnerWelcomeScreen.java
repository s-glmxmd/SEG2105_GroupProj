package com.ahhhh.deliverable1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HomeOwnerWelcomeScreen extends AppCompatActivity {
    TextView welcome_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_welcome_screen);
        Bundle extras = getIntent().getExtras();
        String user = extras.getString("user");
        welcome_message = (TextView)findViewById(R.id.textView2);
        String message = "Welcome " + user + " you're logged in as homeowner!";
        welcome_message.setText(message);
    }
}
