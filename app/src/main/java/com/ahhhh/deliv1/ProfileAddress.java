package com.ahhhh.deliv1;

import java.io.Serializable;

public class ProfileAddress implements Serializable {
    private String streetAddress, province, country, postalCode;
    public ProfileAddress(String streetAddress, String province, String country, String postalCode){
        this.streetAddress=streetAddress;
        this.province=province;
        this.country=country;
        this.postalCode=postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getProvince() {
        return province;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String validate(){
        String error="";
        if(streetAddress.trim().equals("")||province.trim().equals("")||country.trim().equals("")||postalCode.trim().equals("")){
            error = "Address fields cannot be empty.";
        } else if (province.matches(".*\\d+.*")||country.matches(".*\\d+.*")){
            error = "Provinces, states and countries cannot contain numbers.";
        }

        return error;
    }
}
