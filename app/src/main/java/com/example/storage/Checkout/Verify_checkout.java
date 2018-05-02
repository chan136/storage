package com.example.storage.Checkout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.storage.Bean.NewBuyer;
import com.example.storage.Bean.NewCheckout;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Buyer.Verify_buyer;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Verify_checkout extends AppCompatActivity implements View.OnClickListener{

    private EditText device_SKU;
    private EditText device_name;
    private EditText price;
    private EditText count;
    private EditText describe;
    private EditText buyer_SKU;
    private EditText buyer_name;
    private TextView date;
    private Handler handler;

    private List<NewDevice> deviceList = new ArrayList<>();
    private List<NewBuyer> buyerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_verify_checkout,null);
        DrawableColor.changeColor(view,R.id.image,R.drawable.ic_image_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.device_verify,R.drawable.ic_verified_24dp,R.color.textColorPrimary);
        setContentView(R.layout.activity_verify_checkout);

        device_SKU = (EditText)findViewById(R.id.supplier_SKU);
        device_name = (EditText)findViewById(R.id.supplier_name);
        price = (EditText)findViewById(R.id.supplier_count);
        count = (EditText)findViewById(R.id.change);
        describe = (EditText)findViewById(R.id.errorDescribe);
        buyer_SKU = (EditText)findViewById(R.id.buyer_SKU);
        buyer_name = (EditText)findViewById(R.id.buyer_name);
        date = (TextView)findViewById(R.id.date);

        NewCheckout checkout = (NewCheckout) getIntent().getSerializableExtra("checkout");
        device_SKU.setText(checkout.getDevice_SKU());
        device_name.setText(checkout.getDevice_name());
        price.setText(checkout.getPrice());
        count.setText(checkout.getCount());
        describe.setText(checkout.getDescribe());
        buyer_SKU.setText(checkout.getBuyer_SKU());
        buyer_name.setText(checkout.getBuyer_name());

        handler = new Handler() {
            public void handleMessage(Message msg) {
                date.setText((String)msg.obj);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy MM月dd日   HH:mm");
                        String str=sdf.format(new Date());
                        handler.sendMessage(handler.obtainMessage(100,str));
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.device_verify:
                Intent verify1 = new Intent(this, Verify_device.class);
                deviceList = DataSupport.where("SKU = ?",device_SKU.getText().toString()).find(NewDevice.class);
                for (NewDevice device:deviceList) {
                    verify1.putExtra("device",device);
                }
                startActivity(verify1);
                break;
            case R.id.image:
                startActivity(new Intent(this,Checkout_photo.class));
                break;
            case R.id.buyer_verify:
                Intent verify2 = new Intent(this, Verify_buyer.class);
                buyerList = DataSupport.where("SKU = ?",buyer_SKU.getText().toString()).find(NewBuyer.class);
                for (NewBuyer buyer:buyerList) {
                    verify2.putExtra("buyer",buyer);
                }
                startActivity(verify2);
                break;
            default:break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.keep_log, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
