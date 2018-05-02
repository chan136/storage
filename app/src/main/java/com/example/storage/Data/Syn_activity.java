package com.example.storage.Data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.ChooseCheckinAdapter;
import com.example.storage.Bean.ChooseCheckoutAdapter;
import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewCheckout;
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

public class Syn_activity extends AppCompatActivity implements View.OnClickListener{

    private TextView date;
    private List<NewCheckin> checkinList = new ArrayList<>();
    private List<NewCheckout> checkoutList = new ArrayList<>();
    private ChooseCheckinAdapter checkinAdapter;
    private ChooseCheckoutAdapter checkoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_syn_activity,null);
        DrawableColor.changeColor(view,R.id.refresh,R.drawable.ic_refresh_white_24dp,R.color.search);
        setContentView(R.layout.activity_syn_activity);

        date = (TextView)findViewById(R.id.date);

        date.setText(new SimpleDateFormat("HH:mm").format(new Date()));

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

        checkin_init();
        checkinAdapter = new ChooseCheckinAdapter(this,R.layout.checkin_activity_item,checkinList);
        ListView listView = (ListView)findViewById(R.id.checkin_listview);
        listView.setAdapter(checkinAdapter);

        checkout_init();
        checkoutAdapter = new ChooseCheckoutAdapter(this,R.layout.checkout_activity_item,checkoutList);
        ListView listView1 = (ListView)findViewById(R.id.checkout_listview);
        listView1.setAdapter(checkoutAdapter);

    }

    private void checkin_init() {
        List<NewCheckin> checkins = DataSupport.findAll(NewCheckin.class);
        checkinList.clear();
        for (NewCheckin checkin:checkins) {
            checkinList.add(checkin);
        }
    }

    private void checkout_init() {
        List<NewCheckout> checkouts = DataSupport.findAll(NewCheckout.class);
        checkoutList.clear();
        for (NewCheckout checkout:checkouts) {
            checkoutList.add(checkout);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkin_all:
                startActivity(new Intent(this, Choose_checkin.class));
                break;
            case R.id.checkout_all:
                startActivity(new Intent(this, Choose_checkout.class));
                break;
            case R.id.refresh:
                checkinAdapter.notifyDataSetChanged();
                checkoutAdapter.notifyDataSetChanged();
                Toast.makeText(this,"刷新成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
