package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ServiceProviderFunctionality extends AppCompatActivity {
    private String username;
    public ServiceProvider tempSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_functionality);

        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");

        String [] name = myDBHelper.getName(username);
        String password = myDBHelper.findPassword(username);

        //fill profile fields on create
        tempSP = new ServiceProvider(username,password,name[0],name[1]);
        //FEMALE ** SET tempSP EQUAL TO THE SERVICE PROVIDER ASSOCIATED WITH USERNAME (not the test instance i've made above)

        ((TextView)findViewById(R.id.txtUsername)).setText(tempSP.getUsername());
        ((TextView)findViewById(R.id.txtFirstName)).setText(tempSP.getFirstName());
        ((TextView)findViewById(R.id.txtLastName)).setText(tempSP.getLastName());

<<<<<<< HEAD
=======
        //if mandatory fields have not been filled, make the only option available create profile
        if(tempSP.getWorkerAddress()==null){
            ViewGroup profileInfo = findViewById(R.id.layoutProfileInfo);
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
>>>>>>> master


    }


    public void btnCreateProfileClick(View view){
        Intent i = new Intent(ServiceProviderFunctionality.this, CreateSpProfile.class);
        i.putExtra("username", username);
        startActivity(i);
    }
<<<<<<< HEAD


=======
    public void btnEditProfileClick(View view){
        btnCreateProfileClick(view);
    }

    public void setAvailibilityButton(View view){
        Intent i =new Intent(ServiceProviderFunctionality.this, ServiceProviderAvailability.class);
        startActivity(i);

    }
>>>>>>> master
}
