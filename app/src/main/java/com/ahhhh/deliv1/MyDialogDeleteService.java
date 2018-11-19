package com.ahhhh.deliv1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyDialogDeleteService extends DialogFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete_service, container, false);
        TextView mActionCancel = view.findViewById(R.id.action_cancel);
        TextView mActionYes = view.findViewById(R.id.action_yes);



        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ServiceSelector)getActivity()).toDelete = "No";
                getDialog().dismiss();
            }
        });


        mActionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ServiceSelector)getActivity()).toDelete = "Yes";

                getDialog().dismiss();
            }
        });

        return view;
    }
}
