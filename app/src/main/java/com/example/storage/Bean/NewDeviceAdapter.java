package com.example.storage.Bean;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.storage.R;

import java.util.List;

/**
 * Created by Jerry on 2017/12/5.
 */
public class NewDeviceAdapter extends ArrayAdapter<NewDevice> {

    private int resoureId;

    public NewDeviceAdapter(Context context, int textViewResourceId, List<NewDevice> objects) {
        super(context,textViewResourceId,objects);
        resoureId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NewDevice device = getItem(position);
        final View view = LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        final TextView device_SKU = (TextView)view.findViewById(R.id.SKU);
        TextView device_name = (TextView)view.findViewById(R.id.name);
        ImageButton select = (ImageButton)view.findViewById(R.id.select);
        if (select != null) {
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Activity.class.isInstance(getContext())) {
                        Activity activity = (Activity)getContext();
                        Intent intent = new Intent();
                        intent.putExtra("name",device.getName());
                        intent.putExtra("SKU",device.getSKU());
                        activity.setResult(1,intent);
                        activity.finish();
                    }
                }
            });
        }
        device_SKU.setText(device.getSKU());
        device_name.setText(device.getName());
        return view;
    }

}
