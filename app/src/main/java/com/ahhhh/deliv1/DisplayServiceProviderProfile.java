package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayServiceProviderProfile extends AppCompatActivity {

    private String username;
    private ServiceProvider sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_service_provider_profile);
        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");
        String [] name = myDBHelper.getName(username);
        String password = myDBHelper.findPassword(username);

        String [] info = myDBHelper.getSPInfo(username);


        ((TextView)findViewById(R.id.txtUsername)).setText(username);
        ((TextView)findViewById(R.id.txtFirstName)).setText(name[0]);
        ((TextView)findViewById(R.id.txtLastName)).setText(name[1]);
        ((TextView)findViewById(R.id.txtStreetAddress)).setText(info[1]);
        ((TextView)findViewById(R.id.txtProvince)).setText(info[2]);
        ((TextView)findViewById(R.id.txtCountry)).setText(info[3]);
        ((TextView)findViewById(R.id.txtPostal)).setText(info[4]);
        ((TextView)findViewById(R.id.txtCompany)).setText(info[0]);
        ((TextView)findViewById(R.id.txtPhoneNumber)).setText(info[6]);
        ((TextView)findViewById(R.id.txtLisenced)).setText(info[5]);

    }



    public void btnAddOrRemoveServiceClick (View view){
        Intent i = new Intent(DisplayServiceProviderProfile.this, ServiceSelector.class);
        i.putExtra("username", username);
        startActivity(i);

    }

    public void btnEditProfileClick(View view){
        Intent i = new Intent(DisplayServiceProviderProfile.this, CreateSpProfile.class);
        i.putExtra("username", username);
        startActivity(i);
    }

    public void btnEditAvailabilities(View view) {
        //waiting for push from adam
        Intent i = new Intent(DisplayServiceProviderProfile.this, ServiceProviderAvailability.class);
        i.putExtra("username", username);
        startActivity(i);
    }

}
