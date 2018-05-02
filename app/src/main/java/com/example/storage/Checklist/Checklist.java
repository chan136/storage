package com.example.storage.Checklist;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.storage.Bean.ChooseDeviceAdapter;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Utils.ControlUpDown;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.SortListview;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Checklist extends AppCompatActivity {

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private ImageButton btn5;
    private ImageButton btn6;

    private ChooseDeviceAdapter adapter;
    private boolean flag;
    private int sort_id;
    private List<NewDevice> deviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
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

        init();
        adapter = new ChooseDeviceAdapter(this,R.layout.checklist_item,deviceList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        LinearLayout footer = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_footer, null);
        listView.addFooterView(footer);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewDevice device = deviceList.get(position);
                Intent verify = new Intent(Checklist.this, Verify_device.class);
                verify.putExtra("device",device);
                startActivity(verify);
            }
        });
    }

    private void init() {
        List<NewDevice> devices = DataSupport.findAll(NewDevice.class);
        deviceList.clear();
        for (NewDevice device:devices) {
            deviceList.add(device);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_device,menu);
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_checklist,null);
        DrawableColor.changeColor(view,menu.findItem(R.id.sort),R.drawable.ic_sort_white_24dp,R.color.textColorPrimary);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.skip:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.checklist_skip, null);
                builder.setView(view);
                builder.create();
                final AlertDialog dialog = builder.show();
                TextView cancel = (TextView) view.findViewById(R.id.skip_cancel);
                TextView ensure = (TextView) view.findViewById(R.id.ensure);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
            case R.id.sort:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view1 = LayoutInflater.from(this).inflate(R.layout.checklist_sort, null);
                builder1.setView(view1);
                builder1.create();

                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_down_24dp,R.color.select_device);
                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_up_24dp,R.color.select_device);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_arrow_down_24dp,R.color.select_device);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_arrow_up_24dp,R.color.select_device);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_arrow_down_24dp,R.color.select_device);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_arrow_up_24dp,R.color.select_device);

                final AlertDialog dialog1 = builder1.show();
                TextView cancel1 = (TextView) view1.findViewById(R.id.search_checkin_sort_cencel);
                TextView ensure1 = (TextView) view1.findViewById(R.id.device_sort_ensure);
                cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                ensure1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SortListview.deviceSort(adapter,deviceList,flag,sort_id);
                        sort_id = 0;
                        flag = true;
                        dialog1.dismiss();
                    }
                });
                btn1 = (ImageButton) view1.findViewById(R.id.btn1);
                btn2 = (ImageButton) view1.findViewById(R.id.btn2);
                btn3 = (ImageButton) view1.findViewById(R.id.btn3);
                btn4 = (ImageButton) view1.findViewById(R.id.btn4);
                btn5 = (ImageButton) view1.findViewById(R.id.btn5);
                btn6 = (ImageButton) view1.findViewById(R.id.btn6);
                view1.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn1,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn2,btn3,btn4,btn5,btn6);
                        sort_id = 1;
                    }
                });
                view1.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn2,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn6);
                        sort_id = 2;
                    }
                });
                view1.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn3,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn2,btn4,btn5,btn6);
                        sort_id = 3;
                    }
                });
                view1.findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn4,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn2,btn5,btn6);
                        sort_id = 4;
                    }
                });
                view1.findViewById(R.id.five).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn5,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn2,btn6);
                    }
                });
                view1.findViewById(R.id.six).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn6,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn2);
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }
}
