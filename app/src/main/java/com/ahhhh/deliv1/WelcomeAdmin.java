package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WelcomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_admin);
        Bundle passedVals = getIntent().getExtras();
        String firstName=passedVals.getString("username");
        ((TextView)findViewById(R.id.textViewWelcome)).setText("Welcome "+firstName+". You are signed in as an Admin.");
    }

    public void btnProceedClick(View view){
        startActivity(new Intent(WelcomeAdmin.this, ServiceInfoActivity.class));
    }
}
