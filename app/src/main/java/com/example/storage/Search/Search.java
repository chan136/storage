package com.example.storage.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

public class Search extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_search,null);
        DrawableColor.changeColor(view,R.id.checkin,R.drawable.ic_checkin_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.checkout,R.drawable.ic_checkout_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.device,R.drawable.ic_devices_other_black_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.supplier,R.drawable.ic_supplier_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.buyer,R.drawable.ic_buyer_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.checklist,R.drawable.ic_checklist_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.checklist_list,R.drawable.ic_list_white_24dp,R.color.search_text);
        DrawableColor.changeColor(view,R.id.checklist_search,R.drawable.ic_chevron_right_white_24dp,R.color.search_text);

        setContentView(R.layout.activity_search);

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
        getMenuInflater().inflate(R.menu.bluetooth_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkin_list:
                startActivity(new Intent(this,Choose_checkin.class));
                break;
            case R.id.checkin_search:
                startActivity(new Intent(this,Search_checkin.class));
                break;
            case R.id.checkout_list:
                startActivity(new Intent(this,Choose_checkout.class));
                break;
            case R.id.checkout_search:
                startActivity(new Intent(this,Search_checkin.class));
                break;
            case R.id.device_list:
                startActivity(new Intent(this,Choose_device.class));
                break;
            case R.id.device_search:
                startActivity(new Intent(this,Search_checkin.class));
                break;
            case R.id.supplier_list:
                startActivity(new Intent(this,Choose_supplier.class));
                break;
            case R.id.supplier_search:
                startActivity(new Intent(this,Search_checkin.class));
                break;
            case R.id.buyer_list:
                startActivity(new Intent(this,Choose_buyer.class));
                break;
            case R.id.buyer_search:
                startActivity(new Intent(this,Search_checkin.class));
                break;
            case R.id.checklist_list:
                startActivity(new Intent(this,Choose_checklist.class));
                break;
            case R.id.checklist_search:
                startActivity(new Intent(this,Search_checkin.class));
                break;
            default:break;
        }
    }
}
