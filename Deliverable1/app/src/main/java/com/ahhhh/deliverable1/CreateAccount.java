package com.ahhhh.deliverable1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //check database if there are any Admin accounts    HERE FEMALE
        /**
         * if (there is admin account){
         * ((ViewGroup) ((Button) findViewById(R.id.btnCreateAdmin)).getParent()).removeView(((Button) findViewById(R.id.btnCreateAdmin)));
         * }
         */
    }

    public void btnCreateAdminClick(View view) {
        startActivity(new Intent(CreateAccount.this, EnterAccountCridentials.class));
    }
    public void btnCreateServiceProviderClick(View view) {
        //go to service provider cridentials page
        //startActivity(new Intent(CreateAccount.this, EnterAccountCridentials.class));
    }
    public void btnCreateHomeownerClick(View view) {
        Intent intent = new Intent(CreateAccount.this, HomeOwnerAccount.class);
        startActivity(intent);
    }
    public void btnLoginExistingClick(View view){
        //go to login page
        startActivity(new Intent(CreateAccount.this, EnterAccountCridentials.class));

    }
}
