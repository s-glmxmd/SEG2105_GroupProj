package com.ahhhh.deliverable1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnterAccountCredentials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_account_credentials);
    }

    public void onSubmitClick(View v){
        String firstName = ((EditText)findViewById(R.id.firstNameEditText)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.lastNameEditText)).getText().toString();
        String username = ((EditText)findViewById(R.id.usernameEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();
        TextView feedbackTextView = (TextView)findViewById(R.id.feedbackTextView);

        String regx = "^[\\p{L} .'-]+$";

        Pattern p = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        //Matcher m = p.matcher(firstName);

        if (firstName.equals("") || lastName.equals("") || username.equals("") ||
                feedbackTextView.equals("")){
            feedbackTextView.setText("Text inputs cannot be empty!");
        } else if (!p.matcher(firstName).find()){
            feedbackTextView.setText("Invalid First name \"" + firstName + "\"");
        } else if (!p.matcher(lastName).find()){
            feedbackTextView.setText("Invalid Last name \"" + lastName + "\"");
        } else if (password.equals("") || password.length() <= 4 ){
            feedbackTextView.setText("Password size must be a greater than 4 characters, ");
        }

    }
}
