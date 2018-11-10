package com.ahhhh.deliv1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }
    public void btnValidateLoginClick(View view){
        String username=((EditText) findViewById(R.id.editTextUsernameLogin)).getText().toString();
        String password=((EditText) findViewById(R.id.editTextPasswordLogin)).getText().toString();


        //search database for username entered ***HERE FEMALE
        MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
        if (!myDBHelper.usernameExist(username)) {
            Context context = getApplicationContext();
            CharSequence incorrectPassword = "There is no account with that username in our records. Please try again!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, incorrectPassword, duration);
            toast.show();

        }
        if (myDBHelper.usernameExist(username)) {
            String passwordFromDB = myDBHelper.findPassword(username);
            if (!password.equals(passwordFromDB)) {
                Context context = getApplicationContext();
                CharSequence incorrectPassword = "Your password is incorrect!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, incorrectPassword, duration);
                toast.show();


            }
            else {
                //find out what account type is associated with username, send user to the appropriate welcome page
                String accountType = myDBHelper.accountType(username);

                //add the following when the 3 welcome pages are done
                if(accountType.equals("Administrator")){
                    Intent i = new Intent(LogIn.this, WelcomeAdmin.class);
                    i.putExtra("username", username);
                    startActivity(i);

                    //startActivity(new Intent(LogIn.this, WelcomeAdmin.class));
                } else if(accountType.equals("Homeowner")){
                    startActivity(new Intent(LogIn.this, WelcomeHomeowner.class));
                } else if(accountType.equals("ServiceProvider")){
                    startActivity(new Intent(LogIn.this, WelcomeServiceProvider.class));
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence incorrectPassword = "Account type could not be found";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, incorrectPassword, duration);
                    toast.show();

                }

            }

        }
    }
}
