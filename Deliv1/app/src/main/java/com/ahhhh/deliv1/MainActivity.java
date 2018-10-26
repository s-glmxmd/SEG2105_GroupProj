package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void btnCreateAdminClick(View view){
        startActivity(new Intent(MainActivity.this, AccountCreationAdmin.class));
    }
    public void btnCreateServiceProviderClick(View view){
        startActivity(new Intent(MainActivity.this, AccountCreationServiceProvider.class));
    }
    public void btnCreateHomeownerClick(View view){
        startActivity(new Intent(MainActivity.this, AccountCreationHomeowner.class));
    }
    public void btnLoginExistingClick(View view){
        startActivity(new Intent(MainActivity.this, LogIn.class));
    }
}
