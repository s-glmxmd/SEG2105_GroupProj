package com.ahhhh.deliv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminFunctionality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_functionality);

        //on create should pull all services from the database, create a horizontal layout with
        //1. a checkbox that has the services name as the layout
        //2. a viewtext with the hourly rate
    }

    public void btnDeleteServiceClick(View view){

        LinearLayout layout = ((LinearLayout) findViewById(R.id.layoutServices));

        //get index of each child, iterate through children, check if first child is checked, delete that thi
        int numChildren=layout.getChildCount();

        for(int i=0;i<numChildren;i++){
            LinearLayout tempLayout = ((LinearLayout) layout.getChildAt(i));
            CheckBox tempCheckBox = (CheckBox) tempLayout.getChildAt(i);
            if(tempCheckBox.isChecked()){
                String tempCheckBoxText=tempCheckBox.getText().toString();
                String tempHourlyRate = ((TextView) tempLayout.getChildAt(1)).getText().toString();
                //remove the corresponding service from the db, may need to get the hourly rate selected
                //could have multiple services with same name but different hourly rates


                //reload page (data will be added to layout again, may have to remove it all first, test this
                startActivity(new Intent(AdminFunctionality.this, AdminFunctionality.class));
            }
        }
    }

    public void btnCreateServiceClick(View view){
        startActivity(new Intent(AdminFunctionality.this, AdminCreateService.class));
    }

}
