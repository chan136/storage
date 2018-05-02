package com.example.storage.Bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.storage.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jerry on 2017/12/5.
 */
public class ChooseBuyerAdapter extends ArrayAdapter<NewBuyer> {

    private int resoureId;
    private List<NewBuyer> buyerList = new ArrayList<>();
    private int[] color = {R.color.red,R.color.yellow,R.color.orange,R.color.pink,R.color.green,R.color.blue};

    public ChooseBuyerAdapter(Context context, int textViewResourceId, List<NewBuyer> objects) {
        super(context,textViewResourceId,objects);
        resoureId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewBuyer buyer = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView supplier_SKU = (TextView)view.findViewById(R.id.SKU);
        TextView supplier_name = (TextView)view.findViewById(R.id.name);
        TextView first_name = (TextView)view.findViewById(R.id.first_name);
        supplier_SKU.setText(buyer.getSKU());
        supplier_name.setText(buyer.getName());
        first_name.setText(buyer.getName().substring(0,1));
        int num = new Random().nextInt(6);
        first_name.setBackgroundResource(color[num]);
        return view;
    }

}
