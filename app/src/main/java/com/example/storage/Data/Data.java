package com.example.storage.Data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.example.storage.Device.Select_device;
import com.example.storage.Supplier.Select_supplier;
import com.example.storage.Buyer.Select_buyer;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

public class Data extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_data,null);

        DrawableColor.changeColor(view,R.id.syn_img,R.drawable.ic_star_border_black_24dp,R.color.search);
        DrawableColor.changeColor(view,R.id.device_img,R.drawable.ic_devices_other_black_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.supplier_img,R.drawable.ic_supplier_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.buyer_img,R.drawable.ic_buyer_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.syn,R.drawable.ic_chevron_right_white_24dp,R.color.search_text);
        DrawableColor.changeColor(view,R.id.device,R.drawable.ic_list_white_24dp,R.color.search_text);

        setContentView(R.layout.activity_data);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.search));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.search));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.syn:
                Intent syn_analyse = new Intent(this,Syn_analyse.class);
                startActivity(syn_analyse);
                break;
            case R.id.device_list:
                Intent select_device = new Intent(this, Select_device.class);
                startActivity(select_device);
                break;
            case R.id.device:
                Intent device_analyse = new Intent(this, Device_analyse.class);
                startActivity(device_analyse);
                break;
            case R.id.supplier_list:
                Intent select_supplier = new Intent(this, Select_supplier.class);
                startActivity(select_supplier);
                break;
            case R.id.supplier:
                Intent supplier_analyse = new Intent(this, Supplier_analyse.class);
                startActivity(supplier_analyse);
                break;
            case R.id.buyer_list:
                Intent select_buyer = new Intent(this, Select_buyer.class);
                startActivity(select_buyer);
                break;
            case R.id.buyer:
                Intent buyer_analyse = new Intent(this, Buyer_analyse.class);
                startActivity(buyer_analyse);
                break;
            default:break;
        }
    }
}
