package com.example.storage.Checkin;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Bean.NewSupplier;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Search.Result_checkin;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.ShowTime;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Verify_checkin extends AppCompatActivity implements View.OnClickListener{

    private EditText device_SKU;
    private EditText device_name;
    private EditText price;
    private EditText count;
    private EditText describe;
    private EditText supplier_SKU;
    private EditText supplier_name;
    private TextView date;
    private NewCheckin checkin;

    private List<NewDevice> deviceList = new ArrayList<>();
    private List<NewSupplier> supplierList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_verify_checkin,null);
        DrawableColor.changeColor(view,R.id.image,R.drawable.ic_image_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.device_verify,R.drawable.ic_verified_24dp,R.color.textColorPrimary);
        setContentView(R.layout.activity_verify_checkin);

        device_SKU = (EditText)findViewById(R.id.supplier_SKU);
        device_name = (EditText)findViewById(R.id.supplier_name);
        price = (EditText)findViewById(R.id.supplier_count);
        count = (EditText)findViewById(R.id.change);
        describe = (EditText)findViewById(R.id.errorDescribe);
        supplier_SKU = (EditText)findViewById(R.id.buyer_SKU);
        supplier_name = (EditText)findViewById(R.id.buyer_name);
        date = (TextView)findViewById(R.id.date);

        checkin = (NewCheckin)getIntent().getSerializableExtra("checkin");
        device_SKU.setText(checkin.getDevice_SKU());
        device_name.setText(checkin.getDevice_name());
        price.setText(checkin.getPrice());
        count.setText(checkin.getCount());
        describe.setText(checkin.getDescribe());
        supplier_SKU.setText(checkin.getSupplier_SKU());
        supplier_name.setText(checkin.getSupplier_name());

        ShowTime.show(date);

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
                startActivity(new Intent(this,Checkin_photo.class));
                break;
            case R.id.supplier_verify:
                Intent verify2 = new Intent(this, Verify_device.class);
                supplierList = DataSupport.where("SKU = ?",supplier_SKU.getText().toString()).find(NewSupplier.class);
                for (NewSupplier supplier:supplierList) {
                    verify2.putExtra("supplier",supplier);
                }
                startActivity(verify2);
                break;
            default:break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.keep_log, menu);
        getMenuInflater().inflate(R.menu.mode_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mode:
                Intent intent = new Intent(this, Update_checkin.class);
                intent.putExtra("checkin",checkin);
                startActivity(intent);
                break;
            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.delete, null);
                builder.setView(view);
                builder.create();
                final AlertDialog dialog = builder.show();
                TextView cancel = (TextView)view.findViewById(R.id.cancel);
                TextView ensure = (TextView)view.findViewById(R.id.ensure);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        DataSupport.deleteAll(NewCheckin.class,"device_SKU = ?",checkin.getDevice_SKU());
                        Toast.makeText(Verify_checkin.this,"删除成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent intent = new Intent(Verify_checkin.this, Result_checkin.class);
                        intent.putExtra("checkin",checkin);
                        startActivity(intent);
                    }
                });
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
