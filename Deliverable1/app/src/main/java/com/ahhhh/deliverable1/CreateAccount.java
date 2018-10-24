package com.ahhhh.deliverable1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void btnCreateAdminClick(View view) {
        startActivity(new Intent(CreateAccount.this, EnterAccountCridentials.class));
    }
    public void btnCreateServiceProviderClick(View view) {

    }
    public void btnCreateHomeownerClick(View view) {

    }
}
