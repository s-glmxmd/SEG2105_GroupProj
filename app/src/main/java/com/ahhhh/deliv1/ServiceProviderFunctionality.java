package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ServiceProviderFunctionality extends AppCompatActivity {

    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_functionality);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");
        //fill profile fields on create
        //if mandatory fields have not been filled, make the only option available create profile
        //add instructions to the welcome page (create profile/fill in mandatory info, add services, edit availabilities)
    }

    public void btnAddOrRemoveServiceClick(View view){
        Intent i = new Intent(ServiceProviderFunctionality.this, ServiceSelector.class);
        i.putExtra("username", username);
        startActivity(i);
    }
}
