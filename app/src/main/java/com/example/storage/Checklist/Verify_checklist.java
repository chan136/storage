package com.example.storage.Checklist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.ShowTime;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Verify_checklist extends AppCompatActivity implements View.OnClickListener{

    private EditText device_SKU;
    private EditText device_name;
    private EditText count;
    private EditText change;
    private EditText beforeCount;
    private EditText errorDescribe;
    private TextView date;

    private List<NewDevice> deviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_verify_checkin,null);
        DrawableColor.changeColor(view,R.id.image,R.drawable.ic_image_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.device_verify,R.drawable.ic_verified_24dp,R.color.textColorPrimary);
        setContentView(R.layout.activity_verify_checklist);

        device_SKU = (EditText)findViewById(R.id.supplier_SKU);
        device_name = (EditText)findViewById(R.id.supplier_name);
        count = (EditText)findViewById(R.id.supplier_count);
        change = (EditText)findViewById(R.id.change);
        beforeCount = (EditText)findViewById(R.id.beforeCount);
        errorDescribe = (EditText)findViewById(R.id.errorDescribe);
        date = (TextView)findViewById(R.id.date);

        NewCheckin checkin = (NewCheckin)getIntent().getSerializableExtra("checkin");
        device_SKU.setText(checkin.getDevice_SKU());
        device_name.setText(checkin.getDevice_name());
        count.setText(checkin.getCount());
        change.setText(checkin.getChange());
        beforeCount.setText(checkin.getBeforeCount());
        errorDescribe.setText(checkin.getErrorDescribe());
        ShowTime.show(date);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.select_device));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.select_device));
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
                Intent verify = new Intent(this, Verify_device.class);
                deviceList = DataSupport.where("SKU = ?",device_SKU.getText().toString()).find(NewDevice.class);
                for (NewDevice device:deviceList) {
                    verify.putExtra("device",device);
                }
                startActivity(verify);
                break;
            case R.id.image:
                startActivity(new Intent(this,Checklist_photo.class));
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
