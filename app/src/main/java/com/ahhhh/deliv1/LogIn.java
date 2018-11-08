package com.ahhhh.deliv1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
    public void btnValidateLoginClick(View view){
        String username=((EditText) findViewById(R.id.editTextUsername)).getText().toString();
        String password=((EditText) findViewById(R.id.editTextUsername)).getText().toString();

/**
        //search database for username entered ***HERE FEMALE
        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);


        if (!password.equals(myDBHelper.findPassword(username))) {
            Context context = getApplicationContext();
            CharSequence incorrectPassword = "Your password is incorrect!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, incorrectPassword, duration);
            toast.show();
            //PASSWORD WRONG
            //check if password matches username ***HERE FEMALE
        }


        //find out what account type is associated with username, send user to the appropriate welcome page ***HERE FEMALE
        String accountType = myDBHelper.accountType(username);

        //add the following when the 3 welcome pages are done
        /**

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
