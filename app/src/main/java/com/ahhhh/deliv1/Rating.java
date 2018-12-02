package com.ahhhh.deliv1;

import java.io.Serializable;

public class Rating implements Serializable {
    private String comment;
    private int numericRating;
    public Rating(int numericRating, String comment){
        this.comment=comment;
        this.numericRating=numericRating;
    }

    public int getNumericRating() {
        return numericRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNumericRating(int numericRating) {
        this.numericRating = numericRating;
    }

    public String validateRating(){
        if(numericRating>5||numericRating<1){
            return "Please rate the service between 1 and 5 (inclusive)";
        }
        return "";
    }
}
