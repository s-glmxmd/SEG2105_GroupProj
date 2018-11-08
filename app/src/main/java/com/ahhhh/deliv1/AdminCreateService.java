package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AdminCreateService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_service);
    }

    public void btnSubmitServiceClick(View view){
        String serviceName=((EditText)findViewById(R.id.editTextServiceName)).getText().toString();
        String hourlyRate=((EditText)findViewById(R.id.editTextHourlyRate)).getText().toString();
        Service createdService = new Service(serviceName,hourlyRate);
        // call validation method, fill a TextView if returned string !=""
        String errorMessage=createdService.validateEnteredInfo();

        if(errorMessage!=null){
            ((TextView)findViewById(R.id.editTextServiceName)).setText(errorMessage);
        } else{
            //save service to database
            startActivity(new Intent(AdminCreateService.this, AdminFunctionality.class));
        }
    }



}
