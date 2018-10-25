package com.ahhhh.deliverable1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeOwnerAccount extends AppCompatActivity {

    EditText FirstName,Lastname,PhoneNumber,Email,PassWord,Address,UnitNumber,Country,State_Prov,Zip_PostCode;
    Button RegristrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_account);
        //Initialize all the edit text fields in the form
        FirstName = (EditText)findViewById(R.id.FirstName);
        Lastname = (EditText)findViewById(R.id.LastName);
        PhoneNumber = (EditText)findViewById(R.id.PhoneNumber);
        Email = (EditText)findViewById(R.id.Email);
        PassWord = (EditText)findViewById(R.id.PassWord);
        Address = (EditText)findViewById(R.id.Adress);
        UnitNumber = (EditText)findViewById(R.id.UnitNum);
        Country = (EditText)findViewById(R.id.country);
        State_Prov = (EditText)findViewById(R.id.Region);
        Zip_PostCode = (EditText)findViewById(R.id.PostalCode);
        RegristrationButton = (Button)findViewById(R.id.SubmitAccount);
        //create a listener for when the user clicks the submit button
        RegristrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String first_name = FirstName.getText().toString();
                final String last_name = Lastname.getText().toString();
                final String Phone_Number = PhoneNumber.getText().toString();
                final String Email_User = Email.getText().toString();
                final String Password_User = PassWord.getText().toString();
                final String Address_User = Address.getText().toString().trim();
                final String UnitNumber_User = UnitNumber.getText().toString();
                final String Country_User = Country.getText().toString();
                final String State_Prov_User = State_Prov.getText().toString();
                final String Zip_PostCode_User = Zip_PostCode.getText().toString();
                if(!isAlpha(first_name))

                {
                    FirstName.requestFocus();
                    FirstName.setError("FIELD MUST CONTAIN ONLY LETTERS");
                }
                if(first_name.length() == 0)

                {
                    FirstName.requestFocus();
                    FirstName.setError("FIELD CANNOT BE EMPTY");
                }
                if(!isAlpha(last_name))

                {
                    Lastname.requestFocus();
                    Lastname.setError("FIELD MUST CONTAIN ONLY LETTERS");
                }
                if(last_name.length() == 0)

                {
                    Lastname.requestFocus();
                    Lastname.setError("FIELD CANNOT BE EMPTY");
                }
                if(!isNumeric(Phone_Number)){
                    PhoneNumber.requestFocus();
                    PhoneNumber.setError("FIELD MUST ONLY CONTAIN DIGITS");

                }
                if(Phone_Number.length() != 10){
                    PhoneNumber.requestFocus();
                    PhoneNumber.setError("PHONE NUMBER MUST BE 10 DIGITS LONG");

                }
                if(Phone_Number.length() == 0)

                {
                    PhoneNumber.requestFocus();
                    PhoneNumber.setError("FIELD CANNOT BE EMPTY");
                }
                if(!isEmailValid(Email_User)){
                    Email.requestFocus();
                    Email.setError("INVALID EMAIL");
                }
                if(Password_User.length() == 0){
                    PassWord.requestFocus();
                    PassWord.setError("FIELD CANNOT BE EMPTY");
                }
                if(Address_User.length() == 0){
                    Address.requestFocus();
                    Address.setError("FIELD CANNOT BE EMPTY");
                }
                /*if(!(isAlpha(Address_User) && isNumeric(Address_User))){
                    Address.requestFocus();
                    System.out.println(Address_User);
                    Address.setError("Invalid Address");
                }*/
                if(Country_User.length() == 0){
                    Country.requestFocus();
                    Country.setError("FIELD CANNOT BE EMPTY");
                }
                if(!isAlpha(Country_User)){
                    Country.requestFocus();
                    Country.setError("FIELD MUST CONTAIN LETTERS ONLY");
                }
                if(State_Prov_User.length() == 0){
                    State_Prov.requestFocus();
                    State_Prov.setError("FIELD CANNOT BE EMPTY");
                }
                if(!isAlpha(State_Prov_User)){
                    State_Prov.requestFocus();
                    State_Prov.setError("FIELD MUST CONTAIN LETTERS ONLY");
                }
                /*if(!validAddress(Zip_PostCode_User)){
                    Zip_PostCode.requestFocus();
                    Zip_PostCode.setError("Invalid Zip/Post Code");
                }*/
                else{
                    Toast.makeText(HomeOwnerAccount.this, "Submission Successful",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HomeOwnerAccount.this, HomeOwnerWelcomeScreen.class);
                    intent.putExtra("user",first_name);
                    startActivity(intent);
                }
            }
        });
    }

    public boolean isAlpha(String text){
        return text.matches("[a-zA-Z]+");
    }

    public boolean isNumeric(String text){
        return text.matches("^[0-9]*$");
    }
    /*public boolean validAddress(String s) {
        String n = ".*[0-9].*";
        String a = ".*[A-Z].*";
        return s.matches(n) && s.matches(a);
    }*/
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
