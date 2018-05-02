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
public class ChooseCheckoutAdapter extends ArrayAdapter<NewCheckout> {

    private int resoureId;
    private int[] color = {R.color.red,R.color.yellow,R.color.orange,R.color.pink,R.color.green,R.color.blue};

    public ChooseCheckoutAdapter(Context context, int textViewResourceId, List<NewCheckout> objects) {
        super(context,textViewResourceId,objects);
        resoureId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NewCheckout checkout = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView device_SKU = (TextView)view.findViewById(R.id.SKU);
        TextView device_name = (TextView)view.findViewById(R.id.name);
        TextView count = (TextView)view.findViewById(R.id.supplier_count);
        TextView date = (TextView)view.findViewById(R.id.date);
        TextView first_name = (TextView)view.findViewById(R.id.first_name);
        if (device_SKU != null) {
            device_SKU.setText(checkout.getDevice_SKU());
        }
        if (device_name != null) {
            device_name.setText(checkout.getDevice_name());
        }
        count.setText(checkout.getCount());

        SimpleDateFormat sdf = new SimpleDateFormat("MM月 dd");
        Date now = new Date();
        String str = sdf.format(now);
        if (str.equals(checkout.getDate().substring(0,6))) {
            date.setText(checkout.getDate().substring(7));
        } else {
            date.setText(checkout.getDate());
        }
        if (first_name != null) {
            first_name.setText(checkout.getDevice_name().substring(0,1));
            int num = new Random().nextInt(6);
            first_name.setBackgroundResource(color[num]);
        }
        return view;
    }

}
