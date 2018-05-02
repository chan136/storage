package com.example.storage.Data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.ChooseCheckinAdapter;
import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Bean.NewSupplier;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Search.Choose_checkin;
import com.example.storage.Supplier.Verify_supplier;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Supplier_activity extends AppCompatActivity implements View.OnClickListener{

    private TextView supplier_name;
    private TextView supplier_SKU;
    private TextView device_name;
    private TextView device_SKU;
    private TextView date;
    private ChooseCheckinAdapter checkinAdapter;
    private List<NewCheckin> checkinList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_supplier_activity,null);
        DrawableColor.changeColor(view,R.id.refresh,R.drawable.ic_refresh_white_24dp,R.color.checkin);
        DrawableColor.changeColor(view,R.id.supplier_back,R.drawable.ic_chevron_right_white_24dp,R.color.list_view);
        setContentView(R.layout.activity_supplier_activity);

        supplier_name = (TextView) findViewById(R.id.supplier_name);
        supplier_SKU = (TextView)findViewById(R.id.supplier_SKU);
        device_name = (TextView)findViewById(R.id.device_name);
        device_SKU = (TextView)findViewById(R.id.device_SKU);
        date = (TextView)findViewById(R.id.date);

        supplier_name.setText(getIntent().getStringExtra("supplier_name"));
        supplier_SKU.setText(getIntent().getStringExtra("supplier_SKU"));
        device_name.setText(getIntent().getStringExtra("point_name"));
        device_SKU.setText(getIntent().getStringExtra("point_SKU"));
        date.setText(new SimpleDateFormat("HH:mm").format(new Date()));

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.checkin));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.checkin));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkin_init();
        checkinAdapter = new ChooseCheckinAdapter(this,R.layout.checkin_activity_item,checkinList);
        ListView listView = (ListView)findViewById(R.id.checkin_listview);
        listView.setAdapter(checkinAdapter);
    }

    private void checkin_init() {
        List<NewCheckin> checkins = DataSupport.where("device_SKU = ? and supplier_SKU = ?",device_SKU.getText().toString(),supplier_SKU.getText().toString()).find(NewCheckin.class);
        checkinList.clear();
        for (NewCheckin checkin:checkins) {
            checkinList.add(checkin);
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
            case R.id.supplier_back:
                Intent supplier_back = new Intent(this, Verify_supplier.class);
                List<NewSupplier> supplierList = DataSupport.where("SKU = ?",supplier_SKU.getText().toString()).find(NewSupplier.class);
                for (NewSupplier supplier : supplierList) {
                    supplier_back.putExtra("supplier",supplier);
                    startActivity(supplier_back);
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
                checkinAdapter.notifyDataSetChanged();
                Toast.makeText(this,"刷新成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkin_all:
                startActivity(new Intent(this, Choose_checkin.class));
                break;
            default:
                break;
        }
    }
}
