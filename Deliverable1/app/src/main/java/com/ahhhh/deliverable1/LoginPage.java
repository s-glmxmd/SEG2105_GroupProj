package com.ahhhh.deliverable1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void btnValidateLoginClick(View view){
        String username=((EditText) findViewById(R.id.editTextUsername)).getText().toString();
        String password=((EditText) findViewById(R.id.editTextUsername)).getText().toString();

        //search database for username entered ***HERE FEMALE

        //check if password matches username ***HERE FEMALE

        //find out what account type is associated with username, send user to the appropriate welcome page ***HERE FEMALE

        //add the following when the 3 welcome pages are done
        /**
        String accountType = ***
         if(accountType.equals("Administrator")){
            startActivity(new Intent(CreateAccount.this, ***welcomePageForAdmin***.class));
         } else if(accountType.equals("Homeowner")){
         startActivity(new Intent(CreateAccount.this, ***welcomePageForHomeowner***.class));
         } else if(accountType.equals("ServiceProvider)){
         startActivity(new Intent(CreateAccount.this, ***welcomePageForServiceProvider***.class));
         }
         */
    }
}
