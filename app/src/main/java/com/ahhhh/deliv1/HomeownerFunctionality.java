package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeownerFunctionality extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner_functionality);
        Bundle passedVals = getIntent().getExtras();
        username=passedVals.getString("username");
    }

    public void btnServiceSearch(View view){
        Intent i = new Intent(HomeownerFunctionality.this, HomeownerServiceSearch.class);
        i.putExtra("username", username);
        startActivity(i);
    }

    public void btnViewBookedAndRate(View view){
        Intent i = new Intent(HomeownerFunctionality.this, HomeownerViewBookedAndRate.class);
        i.putExtra("username", username);
        startActivity(i);
    }

    public void btnLogoutClick(View view) {
        Intent i = new Intent(HomeownerFunctionality.this, MainActivity.class);
        startActivity(i);
    }
}
