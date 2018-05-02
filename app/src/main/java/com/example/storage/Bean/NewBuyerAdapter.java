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
public class NewBuyerAdapter extends ArrayAdapter<NewBuyer> {

    private int resoureId;

    public NewBuyerAdapter(Context context, int textViewResourceId, List<NewBuyer> objects) {
        super(context,textViewResourceId,objects);
        resoureId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final NewBuyer buyer = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView supplier_SKU = (TextView)view.findViewById(R.id.SKU);
        TextView supplier_name = (TextView)view.findViewById(R.id.name);
        ImageButton select = (ImageButton)view.findViewById(R.id.select);
        if (select != null) {
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Activity.class.isInstance(getContext())) {
                        Activity activity = (Activity)getContext();
                        Intent intent = new Intent();
                        intent.putExtra("buyer_name",buyer.getName());
                        intent.putExtra("buyer_SKU",buyer.getSKU());
                        activity.setResult(2,intent);
                        activity.finish();
                    }
                }
            });
        }
        supplier_SKU.setText(buyer.getSKU());
        supplier_name.setText(buyer.getName());
        return view;
    }

}
