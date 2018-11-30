package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeleteServiceFromProfile extends AppCompatActivity {


    private String username;
    private Service serviceToDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service_from_profile);

        Bundle passedVals = getIntent().getExtras();
        username = passedVals.getString("username");
        serviceToDelete = (Service) passedVals.get("service");

    }

    public void btnClickProceed(View view) {

        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        myDBHelper.removeService(username, serviceToDelete);

        Intent intent = new Intent(DeleteServiceFromProfile.this, ServiceSelector.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public void btnClickCancel(View view){

        Intent intent = new Intent(DeleteServiceFromProfile.this, ServiceSelector.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
