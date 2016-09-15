package com.example.nicolas.test_foursquare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nicolas on 15/09/2016.
 */
public class PointOfInterestAdapter extends ArrayAdapter<PointOfInterest>
{

    public PointOfInterestAdapter(Context context, int resource, List<PointOfInterest> InterestPoint) {
        super(context, resource, InterestPoint);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_interest_point,parent, false);
        }

        PointOfInterestViewHolder viewHolder = (PointOfInterestViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PointOfInterestViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.address= (TextView) convertView.findViewById(R.id.address);
            convertView.setTag( viewHolder);
        }

        PointOfInterest interestPoint = getItem(position);

        viewHolder.name.setText(interestPoint.getName());
        //viewHolder.address.setText(interestPoint.getAddress());
        viewHolder.address.setText("Home");

        return convertView;
    }
}
