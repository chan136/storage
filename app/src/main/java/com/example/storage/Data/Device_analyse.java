package com.example.storage.Data;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.NewDevice;
import com.example.storage.Device.Select_device;
import com.example.storage.Supplier.Select_supplier;
import com.example.storage.Buyer.Select_buyer;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Device_analyse extends AppCompatActivity implements View.OnClickListener{

    private View point_layout;
    private View define_layout;
    private ImageButton point_supplier;
    private ImageButton point_buyer;
    private ImageButton standard;
    private ImageButton define;
    private ImageButton supplier_list;
    private ImageButton buyer_list;
    private ImageButton day_ib;
    private ImageButton month_ib;
    private TextView start;
    private TextView end;

    private int year;
    private int month;
    private int day;
    private int start_year;
    private int start_month;
    private int start_day;
    private int end_year;
    private int end_month;
    private int end_day;

    private EditText device_SKU;
    private EditText device_name;
    private EditText point_SKU;
    private EditText point_name;

    private List<NewDevice> deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_device_analyse,null);

        DrawableColor.changeColor(view,R.id.device_list,R.drawable.ic_list_white_24dp,R.color.select_device);
        DrawableColor.changeColor(view,R.id.point_supplier,R.drawable.ic_uncheck_box_black_24dp,R.color.select_device);
        DrawableColor.changeColor(view,R.id.standard,R.drawable.ic_check_box_black_24dp,R.color.select_device);
        DrawableColor.changeColor(view,R.id.day,R.drawable.ic_radio_button_checked_black_24dp,R.color.select_device);
        DrawableColor.changeColor(view,R.id.month,R.drawable.ic_radio_button_unchecked_black_24dp,R.color.select_device);

        setContentView(R.layout.activity_device_analyse);

        point_layout = findViewById(R.id.point_layout);
        define_layout = findViewById(R.id.define_layout);
        point_supplier = (ImageButton)findViewById(R.id.point_supplier);
        point_buyer = (ImageButton)findViewById(R.id.point_buyer);
        standard = (ImageButton)findViewById(R.id.standard);
        define = (ImageButton)findViewById(R.id.define);
        supplier_list = (ImageButton)findViewById(R.id.select_supplier);
        buyer_list = (ImageButton)findViewById(R.id.select_buyer);
        start = (TextView)findViewById(R.id.start);
        end = (TextView)findViewById(R.id.end);
        day_ib = (ImageButton)findViewById(R.id.day);
        month_ib = (ImageButton)findViewById(R.id.month);
        device_SKU = (EditText)findViewById(R.id.supplier_SKU);
        device_name = (EditText)findViewById(R.id.supplier_name);
        point_SKU = (EditText)findViewById(R.id.point_SKU);
        point_name = (EditText)findViewById(R.id.point_name);

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

        initStartDate();
        initEndDate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void initStartDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy MM月dd日");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -6);
        Date m = c.getTime();
        start.setText(format.format(m));
        start_year = c.get(Calendar.YEAR);
        start_month = c.get(Calendar.MONTH);
        start_day = c.get(Calendar.DATE);
    }

    public void initEndDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy MM月dd日");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date m = c.getTime();
        end.setText(format.format(m));
        end_year = c.get(Calendar.YEAR);
        end_month = c.get(Calendar.MONTH);
        end_day = c.get(Calendar.DATE);
    }

    public void getStartDate(View v) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                Device_analyse.this.year = year;
                month = monthOfYear;
                day = dayOfMonth;
                start.setText(year + " " + (month+1) + "月" + day + "日");

            }
        }, start_year, start_month, start_day).show();
    }

    public void getEndDate(View v) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                Device_analyse.this.year = year;
                month = monthOfYear;
                day = dayOfMonth;
                end.setText(year + " " + (month+1) + "月" + day + "日");

            }
        }, end_year, end_month, end_day).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_supplier:
                Intent select_supplier = new Intent(this, Select_supplier.class);
                startActivityForResult(select_supplier,2);
                break;
            case R.id.select_buyer:
                Intent select_buyer = new Intent(this, Select_buyer.class);
                startActivityForResult(select_buyer,3);
                break;
            case R.id.device_list:
                Intent select_device = new Intent(this, Select_device.class);
                startActivityForResult(select_device,1);
                break;
            case R.id.point_supplier:
                if (point_layout.getVisibility() == View.GONE) {
                    point_layout.setVisibility(View.VISIBLE);
                    point_supplier.setBackground(getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
                    supplier_list.setVisibility(View.VISIBLE);
                } else {
                    if (buyer_list.getVisibility() == View.VISIBLE) {
                        point_supplier.setBackground(getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
                        supplier_list.setVisibility(View.VISIBLE);
                    } else {
                        point_layout.setVisibility(View.GONE);
                        point_supplier.setBackground(getResources().getDrawable(R.drawable.ic_uncheck_box_black_24dp));
                        supplier_list.setVisibility(View.GONE);
                    }
                }
                point_buyer.setBackground(getResources().getDrawable(R.drawable.ic_uncheck_box_black_24dp));
                buyer_list.setVisibility(View.GONE);
                break;
            case R.id.point_buyer:
                if (point_layout.getVisibility() == View.GONE) {
                    point_layout.setVisibility(View.VISIBLE);
                    point_buyer.setBackground(getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
                    buyer_list.setVisibility(View.VISIBLE);
                } else {
                    if (supplier_list.getVisibility() == View.VISIBLE) {
                        point_buyer.setBackground(getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
                        buyer_list.setVisibility(View.VISIBLE);
                    } else {
                        point_layout.setVisibility(View.GONE);
                        point_buyer.setBackground(getResources().getDrawable(R.drawable.ic_uncheck_box_black_24dp));
                        buyer_list.setVisibility(View.GONE);
                    }
                }
                point_supplier.setBackground(getResources().getDrawable(R.drawable.ic_uncheck_box_black_24dp));
                supplier_list.setVisibility(View.GONE);
                break;
            case R.id.standard:
                standard.setBackground(getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
                define.setBackground(getResources().getDrawable(R.drawable.ic_uncheck_box_black_24dp));
                define_layout.setVisibility(View.GONE);
                break;
            case R.id.define:
                standard.setBackground(getResources().getDrawable(R.drawable.ic_uncheck_box_black_24dp));
                define.setBackground(getResources().getDrawable(R.drawable.ic_check_box_black_24dp));
                define_layout.setVisibility(View.VISIBLE);
                break;
            case R.id.day:
                day_ib.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                month_ib.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                break;
            case R.id.month:
                day_ib.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                month_ib.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                break;
            case R.id.activity:
                if (TextUtils.isEmpty(device_SKU.getText())||TextUtils.isEmpty(device_name.getText())) {
                    Toast.makeText(this,"货品不能为空",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, Device_activity.class);
                    deviceList = DataSupport.where("SKU = ?",device_SKU.getText().toString()).find(NewDevice.class);
                    for (NewDevice device : deviceList) {
                        intent.putExtra("device_SKU",device.getSKU());
                        startActivity(intent);
                    }
                }
            default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                device_SKU.setText(data.getExtras().getString("SKU"));
                device_name.setText(data.getExtras().getString("name"));
                break;
            case 2:
                point_SKU.setText(data.getExtras().getString("supplier_SKU"));
                point_name.setText(data.getExtras().getString("supplier_name"));
                break;
            case 3:
                point_SKU.setText(data.getExtras().getString("buyer_SKU"));
                point_name.setText(data.getExtras().getString("buyer_name"));
                break;
            default:break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
