package com.example.storage.Data;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Syn_analyse extends AppCompatActivity implements View.OnClickListener{

    private View view;
    private View define_layout;
    private TextView start;
    private TextView end;
    private ImageButton standard;
    private ImageButton define;
    private ImageButton day_ib;
    private ImageButton month_ib;

    private int year;
    private int month;
    private int day;
    private int start_year;
    private int start_month;
    private int start_day;
    private int end_year;
    private int end_month;
    private int end_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(this).inflate(R.layout.activity_syn_analyse,null);
        DrawableColor.changeColor(view,R.id.standard,R.drawable.ic_check_box_black_24dp,R.color.search);
        DrawableColor.changeColor(view,R.id.standard,R.drawable.ic_uncheck_box_black_24dp,R.color.search);
        DrawableColor.changeColor(view,R.id.day,R.drawable.ic_radio_button_checked_black_24dp,R.color.search);
        DrawableColor.changeColor(view,R.id.month,R.drawable.ic_radio_button_unchecked_black_24dp,R.color.search);

        setContentView(R.layout.activity_syn_analyse);
        define_layout = findViewById(R.id.define_layout);
        start = (TextView)findViewById(R.id.start);
        end = (TextView)findViewById(R.id.end);
        standard = (ImageButton)findViewById(R.id.standard);
        define = (ImageButton)findViewById(R.id.define);
        day_ib = (ImageButton)findViewById(R.id.day);
        month_ib = (ImageButton)findViewById(R.id.month);

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
                Syn_analyse.this.year = year;
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
                Syn_analyse.this.year = year;
                month = monthOfYear;
                day = dayOfMonth;
                end.setText(year + " " + (month+1) + "月" + day + "日");

            }
        }, end_year, end_month, end_day).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                startActivity(new Intent(this,Syn_activity.class));
                break;
            default:break;
        }
    }
}
