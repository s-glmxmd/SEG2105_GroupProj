package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateSpProfile extends AppCompatActivity {
    private String username;

    private String company, phone, streetAddress, province, country, postal, description;
    private boolean licensed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sp_profile);
        Bundle passedVals = getIntent().getExtras();
        username=passedVals.getString("username");
    }

    public void btnCompleteClick(View view){
        company=((EditText) findViewById(R.id.edtxtCompany)).getText().toString();
        phone=((EditText) findViewById(R.id.edtxtPhone)).getText().toString();
        streetAddress=((EditText) findViewById(R.id.edtxtStreetAddress)).getText().toString();
        province=((EditText) findViewById(R.id.edtxtProvince)).getText().toString();
        country=((EditText) findViewById(R.id.edtxtCountry)).getText().toString();
        postal=((EditText) findViewById(R.id.edtxtPostal)).getText().toString();
        description=((EditText) findViewById(R.id.edtxtDescription)).getText().toString();
        licensed=((CheckBox) findViewById(R.id.chkLicense)).isChecked();

        ServiceProvider tempSP=new ServiceProvider("test","test","test","test");
        tempSP.setCompanyName(company);
        tempSP.setPhoneNumber(phone);
        tempSP.setWorkerAddress(new ProfileAddress(streetAddress,province,country,postal));

        String validationError = tempSP.validateProfile();
        if(validationError.equals("")){
            //*** FEMALE put the following values in to the database
            //company, phone, streetAddress, province, country, postal, description, licensed

            Intent i = new Intent(CreateSpProfile.this, ServiceProviderFunctionality.class);
            i.putExtra("username", username);
            startActivity(i);
        } else {
            ((TextView) findViewById(R.id.txtError)).setText(validationError);
        }

    }
}
