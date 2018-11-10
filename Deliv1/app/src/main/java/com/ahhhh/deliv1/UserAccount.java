package com.ahhhh.deliv1;

import java.util.regex.Pattern;

public class UserAccount {
    private String username, password, firstName, lastName;

    public UserAccount(String username, String password, String firstName, String lastName){
        this.username=username;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String validateEnteredInfo(){
        String regx = "^[\\p{L} .'-]+$";
        Pattern p = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        if (firstName.equals("") || lastName.equals("") || username.equals("") || password.equals("")){
            return "Text inputs cannot be empty!";
        } else if (!p.matcher(firstName).find()){
            return ("Invalid First name \"" + firstName + "\"");
        } else if (!p.matcher(lastName).find()){
            return ("Invalid Last name \"" + lastName + "\"");
        } else if (password.equals("") || password.length() <= 4 ){
            return ("Password size must be a greater than 4 characters, ");
        }

        return "";
    }
}
