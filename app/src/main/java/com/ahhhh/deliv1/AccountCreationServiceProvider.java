package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AccountCreationServiceProvider extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_service_provider);
    }
    public void btnSubmitClick(View view){
        String firstName = ((EditText)findViewById(R.id.firstNameEditText)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.lastNameEditText)).getText().toString();
        String username = ((EditText)findViewById(R.id.usernameEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();

        UserAccount account = new UserAccount(username,password,firstName,lastName);

        if(!account.validateEnteredInfo().equals("")){
            ((TextView)findViewById(R.id.feedbackTextView)).setText(account.validateEnteredInfo());
        } else{
            //SAVE INFO TO DATABASE HERE FEMALE, AS LONG AS THE USERNAME or PASSWORD DON'T EXIST ALREADY
            MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);
            if (myDBHelper.usernameExist(username)) {
                ((TextView)findViewById(R.id.feedbackTextView)).setText("Username already exists. Please enter a different one.");
            }
            else {
                myDBHelper.addUserAccount(firstName, lastName, username, password, 3);
                Intent i =new Intent(AccountCreationServiceProvider.this, WelcomeServiceProvider.class);
                i.putExtra("username", firstName);
                i.putExtra("lastname", lastName);
                startActivity(i);
            }

        }
    }
}
