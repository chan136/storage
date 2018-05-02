package com.example.storage.Data;

import android.content.Intent;
import android.nfc.tech.NfcB;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.ChooseCheckinAdapter;
import com.example.storage.Bean.ChooseCheckoutAdapter;
import com.example.storage.Bean.NewBuyer;
import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewCheckout;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Buyer.Verify_buyer;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Search.Choose_checkin;
import com.example.storage.Search.Choose_checkout;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Buyer_activity extends AppCompatActivity implements View.OnClickListener{

    private TextView buyer_name;
    private TextView buyer_SKU;
    private TextView device_name;
    private TextView device_SKU;
    private TextView date;

    private ChooseCheckoutAdapter checkoutAdapter;
    private List<NewCheckout> checkoutList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_buyer_activity,null);
        DrawableColor.changeColor(view,R.id.refresh,R.drawable.ic_refresh_white_24dp,R.color.checkout);
        DrawableColor.changeColor(view,R.id.buyer_back,R.drawable.ic_chevron_right_white_24dp,R.color.list_view);
        setContentView(R.layout.activity_buyer_activity);

        buyer_name = (TextView)findViewById(R.id.buyer_name);
        buyer_SKU = (TextView)findViewById(R.id.buyer_SKU);
        device_name = (TextView)findViewById(R.id.device_name);
        device_SKU = (TextView)findViewById(R.id.device_SKU);
        date = (TextView)findViewById(R.id.date);

        buyer_name.setText(getIntent().getStringExtra("buyer_name"));
        buyer_SKU.setText(getIntent().getStringExtra("buyer_SKU"));
        device_name.setText(getIntent().getStringExtra("device_name"));
        device_SKU.setText(getIntent().getStringExtra("device_SKU"));
        date.setText(new SimpleDateFormat("HH:mm").format(new Date()));

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.checkout));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.checkout));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkout_init();
        checkoutAdapter = new ChooseCheckoutAdapter(this,R.layout.checkout_activity_item,checkoutList);
        ListView listView = (ListView)findViewById(R.id.checkout_listview);
        listView.setAdapter(checkoutAdapter);

    }

    private void checkout_init() {
        List<NewCheckout> checkouts = DataSupport.where("device_SKU = ? and buyer_SKU = ?",device_SKU.getText().toString(),buyer_SKU.getText().toString()).find(NewCheckout.class);
        checkoutList.clear();
        for (NewCheckout checkout:checkouts) {
            checkoutList.add(checkout);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buyer_back:
                Intent buyer_back = new Intent(this, Verify_buyer.class);
                List<NewBuyer> buyerList = DataSupport.where("SKU = ?",buyer_SKU.getText().toString()).find(NewBuyer.class);
                for (NewBuyer buyer : buyerList) {
                    buyer_back.putExtra("buyer",buyer);
                    startActivity(buyer_back);
                }
                break;
            case R.id.device_back:
                Intent device_back = new Intent(this, Verify_device.class);
                List<NewDevice> deviceList = DataSupport.where("SKU = ?",device_SKU.getText().toString()).find(NewDevice.class);
                for (NewDevice device : deviceList) {
                    device_back.putExtra("device",device);
                    startActivity(device_back);
                }
                break;
            case R.id.refresh:
                checkoutAdapter.notifyDataSetChanged();
                Toast.makeText(this,"刷新成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkout_all:
                startActivity(new Intent(this, Choose_checkout.class));
                break;
            default:
                break;
        }
    }
}
