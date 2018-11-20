package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ServiceProviderFunctionality extends AppCompatActivity {
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_functionality);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");

        //fill profile fields on create
        ServiceProvider tempSP=new ServiceProvider("test","test","test","test");
        //FEMALE ** SET tempSP EQUAL TO THE SERVICE PROVIDER ASSOCIATED WITH USERNAME (not the test instance i've made above)

        ((TextView)findViewById(R.id.txtUsername)).setText(tempSP.getUsername());
        ((TextView)findViewById(R.id.txtFirstName)).setText(tempSP.getFirstName());
        ((TextView)findViewById(R.id.txtLastName)).setText(tempSP.getLastName());

        //if mandatory fields have not been filled, make the only option available create profile
        if(tempSP.getWorkerAddress()==null){
            ViewGroup profileInfo = (ViewGroup) findViewById(R.id.layoutProfileInfo);
            profileInfo.removeAllViews();
        } else{
            Button createProfileBtn = findViewById(R.id.btnCreateProfile);
            ((ViewGroup) (createProfileBtn).getParent()).removeView(createProfileBtn);
        }
        ((TextView)findViewById(R.id.txtStreetAddress)).setText(tempSP.getWorkerAddress().getStreetAddress());
        ((TextView)findViewById(R.id.txtProvince)).setText(tempSP.getWorkerAddress().getProvince());
        ((TextView)findViewById(R.id.txtCountry)).setText(tempSP.getWorkerAddress().getCountry());
        ((TextView)findViewById(R.id.txtPostal)).setText(tempSP.getWorkerAddress().getPostalCode());
        ((TextView)findViewById(R.id.txtCompany)).setText(tempSP.getCompanyName());
        ((TextView)findViewById(R.id.txtPhoneNumber)).setText(tempSP.getPhoneNumber());

        if(tempSP.isLicenced()){
            ((TextView)findViewById(R.id.txtLisenced)).setText("Yes");
        } else{
            ((TextView)findViewById(R.id.txtLisenced)).setText("No");
        }
    }

    public void btnAddOrRemoveServiceClick(View view){
        Intent i = new Intent(ServiceProviderFunctionality.this, ServiceSelector.class);
        i.putExtra("username", username);
        startActivity(i);

    }

    public void btnCreateProfileClick(View view){
        Intent i = new Intent(ServiceProviderFunctionality.this, CreateSpProfile.class);
        i.putExtra("username", username);
        startActivity(i);
    }
    public void btnEditProfileClick(View view){
        btnCreateProfileClick(view);

    }
}
