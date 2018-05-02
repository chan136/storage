package com.example.storage.Main;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.storage.Account.Account;
import com.example.storage.Buyer.Buyer;
import com.example.storage.Checkin.Checkin;
import com.example.storage.Checklist.Checklist;
import com.example.storage.Checkout.Checkout;
import com.example.storage.Data.Data;
import com.example.storage.Device.Device;
import com.example.storage.R;
import com.example.storage.Search.Search;
import com.example.storage.Setting.Setting;
import com.example.storage.Supplier.Supplier;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main,null);

        DrawableColor.changeColor(view,R.id.checkin_ib,R.drawable.ic_checkin_36dp,R.color.checkin);
        DrawableColor.changeColor(view,R.id.supplier_ib,R.drawable.ic_supplier_36dp,R.color.checkin);
        DrawableColor.changeColor(view,R.id.checkout_ib,R.drawable.ic_checkout_36dp,R.color.checkout);
        DrawableColor.changeColor(view,R.id.buyer_ib,R.drawable.ic_buyer_36dp,R.color.checkout);
        DrawableColor.changeColor(view,R.id.device_ib,R.drawable.ic_devices_other_black_36dp,R.color.select_device);
        DrawableColor.changeColor(view,R.id.checkin_ib,R.drawable.ic_checklist_36dp,R.color.select_device);
        DrawableColor.changeColor(view,R.id.checkin_ib,R.drawable.ic_search_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.checkin_ib,R.drawable.ic_data_36dp,R.color.search);
        DrawableColor.changeColor(view,R.id.checkin_ib,R.drawable.ic_account_36dp,R.color.search_text);
        DrawableColor.changeColor(view,R.id.checkin_ib,R.drawable.ic_settings_black_36dp,R.color.search_text);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.colorPrimary));
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkin:
            case R.id.checkin_ib:
                Intent chekcin = new Intent(MainActivity.this,Checkin.class);
                startActivity(chekcin);
                break;
            case R.id.supplier:
            case R.id.supplier_ib:
                Intent supplier = new Intent(MainActivity.this,Supplier.class);
                startActivity(supplier);
                break;
            case R.id.checkout:
            case R.id.checkout_ib:
                Intent chekcout = new Intent(MainActivity.this,Checkout.class);
                startActivity(chekcout);
                break;
            case R.id.buyer:
            case R.id.buyer_ib:
                Intent buyer = new Intent(MainActivity.this,Buyer.class);
                startActivity(buyer);
                break;
            case R.id.device:
            case R.id.device_ib:
                Intent device = new Intent(MainActivity.this,Device.class);
                startActivity(device);
                break;
            case R.id.checklist:
            case R.id.checklist_ib:
                Intent checklist = new Intent(MainActivity.this,Checklist.class);
                startActivity(checklist);
                break;
            case R.id.search:
            case R.id.search_ib:
                Intent search = new Intent(MainActivity.this,Search.class);
                startActivity(search);
                break;
            case R.id.data:
            case R.id.data_ib:
                Intent data = new Intent(MainActivity.this,Data.class);
                startActivity(data);
                break;
            case R.id.account:
            case R.id.account_ib:
                Intent account = new Intent(MainActivity.this,Account.class);
                startActivity(account);
                break;
            case R.id.settings:
            case R.id.settings_ib:
                Intent setting = new Intent(MainActivity.this,Setting.class);
                startActivity(setting);
                break;
            default:break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                break;
            case R.id.menu_help:
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
