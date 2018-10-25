package com.ahhhh.deliverable1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EnterAccountCridentials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_account_cridentials);
    }
    public void onSubmitClick(View view){
        String firstName = ((EditText)findViewById(R.id.firstNameEditText)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.lastNameEditText)).getText().toString();
        String username = ((EditText)findViewById(R.id.usernameEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();

        UserAccount account = new UserAccount(username,password,firstName,lastName);

        if(!account.validateEnteredInfo().equals("")){
            ((EditText)findViewById(R.id.feedbackTextView)).setText(account.validateEnteredInfo());
        } else{
            //go to welcome page
            //
        }
    }
}
