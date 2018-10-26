package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AccountCreationAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation_admin);
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

            MyDatabaseHelper myDBHelper = new MyDatabaseHelper(this);

            myDBHelper.incrementPrimaryAccount();
            myDBHelper.incrementPrimaryCredentials();
            int idPA = myDBHelper.getPrimaryAccount();
            int idPC = myDBHelper.getPrimaryCredentials();

            myDBHelper.addUserPersonnelInfo(firstName, lastName, idPA, 1);
            myDBHelper.addUserCredential(username, password, idPC);

            if (myDBHelper.usernameExist(username)) {
                ((TextView)findViewById(R.id.feedbackTextView)).setText("Username and/or password already exist. Try again.");
                myDBHelper.deleteUserCredential(idPC);
                myDBHelper.deleteUserAccount(idPA);
            }
            else {
                Intent i = new Intent(AccountCreationAdmin.this, WelcomeAdmin.class);
                i.putExtra("firstName", firstName);
                startActivity(i);
            }
        }
    }
}
