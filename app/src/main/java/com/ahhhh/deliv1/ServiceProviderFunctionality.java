package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ServiceProviderFunctionality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_functionality);
        //fill profile fields on create
        //if mandatory fields have not been filled, make the only option available create profile
        //add instructions to the welcome page (create profile/fill in mandatory info, add services, edit availabilities)
    }

    public void btnAddOrRemoveServiceClick(View view){
        startActivity(new Intent(ServiceProviderFunctionality.this, ServiceSelector.class));
    }
}
