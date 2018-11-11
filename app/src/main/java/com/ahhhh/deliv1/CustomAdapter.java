
package com.ahhhh.deliv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahhhh.deliv1.R;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    private final Context context;
    private final ArrayList<Service> myServices;

    public CustomAdapter(Context context, ArrayList myServices){
        super(context, R.layout.service_item_layout, myServices);
        this.context = context;
        this.myServices = myServices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.service_item_layout, parent, false);

        TextView serviceName = (TextView) rowView.findViewById(R.id.textView9);
        TextView serviceRate = (TextView) rowView.findViewById(R.id.textView10);

        serviceName.setText(myServices.get(position).getServiceName());
        serviceRate.setText(Double.toString(myServices.get(position).getHourlyRate()));
        return rowView;

    }

}
