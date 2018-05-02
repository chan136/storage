package com.example.storage.Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.storage.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Jerry on 2017/12/5.
 */
public class ChooseChecklistAdapter extends ArrayAdapter<NewCheckin> {

    private int resoureId;
    private int[] color = {R.color.red,R.color.yellow,R.color.orange,R.color.pink,R.color.green,R.color.blue};

    public ChooseChecklistAdapter(Context context, int textViewResourceId, List<NewCheckin> objects) {
        super(context,textViewResourceId,objects);
        resoureId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NewCheckin checkin = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView device_SKU = (TextView)view.findViewById(R.id.SKU);
        TextView device_name = (TextView)view.findViewById(R.id.name);
        TextView count = (TextView)view.findViewById(R.id.supplier_count);
        TextView date = (TextView)view.findViewById(R.id.date);
        TextView first_name = (TextView)view.findViewById(R.id.first_name);
        TextView change = (TextView)view.findViewById(R.id.change);

        device_SKU.setText(checkin.getDevice_SKU());
        device_name.setText(checkin.getDevice_name());
        count.setText(checkin.getCount());
        if (checkin.getChange() == null) {
            change.setText("生"+checkin.getCount());
        } else {
            change.setText(checkin.getChange());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM月 dd");
        Date now = new Date();
        String str = sdf.format(now);
        if (str.equals(checkin.getDate().substring(0,6))) {
            date.setText(checkin.getDate().substring(7));
        } else {
            date.setText(checkin.getDate());
        }

        first_name.setText(checkin.getDevice_name().substring(0,1));
        int num = new Random().nextInt(6);
        first_name.setBackgroundResource(color[num]);
        if (change.getText().toString().substring(0,1).equals("生")) {
            change.setTextColor(getContext().getResources().getColor(R.color.checkin));
        } else {
            change.setTextColor(getContext().getResources().getColor(R.color.checkout));
        }
        return view;
    }

}
