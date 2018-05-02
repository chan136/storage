package com.example.storage.Search;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.storage.Bean.ChooseCheckinAdapter;
import com.example.storage.Bean.NewCheckin;
import com.example.storage.Checkin.Verify_checkin;
import com.example.storage.R;
import com.example.storage.Utils.ControlUpDown;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Result_checkin extends AppCompatActivity {

    private Handler handler1;
    private Handler handler2;
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private ImageButton btn5;
    private ImageButton btn6;
    private ImageButton btn7;

    private List<NewCheckin> checkinList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_checkin);

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

        init();
        ChooseCheckinAdapter adapter = new ChooseCheckinAdapter(this,R.layout.choose_checkin_item,checkinList);
        ListView listView = (ListView)findViewById(R.id.list_view);
        LinearLayout footer = (LinearLayout)LayoutInflater.from(this).inflate(R.layout.list_footer, null);
        listView.addFooterView(footer);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewCheckin checkin = checkinList.get(position);
                Intent verify = new Intent(Result_checkin.this, Verify_checkin.class);
                verify.putExtra("checkin",checkin);
                startActivity(verify);
            }
        });
    }

    private void init() {
        List<NewCheckin> checkins = DataSupport.findAll(NewCheckin.class);
        checkinList.clear();
        for (NewCheckin checkin:checkins) {
            checkinList.add(checkin);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_device,menu);
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.skip:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.select_checkin_skip, null);
                builder.setView(view);
                builder.create();

                final EditText et1 = (EditText) view.findViewById(R.id.skip_date_text);
                final EditText et2 = (EditText) view.findViewById(R.id.skip_time_text);
                handler1 = new Handler() {
                    public void handleMessage(Message msg) {
                        et1.setText((String)msg.obj);
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(true){
                                SimpleDateFormat sdf=new SimpleDateFormat("yyyy MM月dd日");
                                String str=sdf.format(new Date());
                                handler1.sendMessage(handler1.obtainMessage(100,str));
                                Thread.sleep(2000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                handler2 = new Handler() {
                    public void handleMessage(Message msg) {
                        et2.setText((String)msg.obj);
                    }
                };
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            while(true){
                                SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm");
                                String str=sdf1.format(new Date());
                                handler2.sendMessage(handler2.obtainMessage(100,str));
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                et1.setFocusable(false);
                et2.setFocusable(false);

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
                View view1 = LayoutInflater.from(this).inflate(R.layout.select_checkin_sort, null);

                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_down_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn4,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn5,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn6,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn7,R.drawable.ic_arrow_up_24dp,R.color.checkin);

                builder1.setView(view1);
                builder1.create();
                final AlertDialog dialog1 = builder1.show();
                TextView cancel1 = (TextView) view1.findViewById(R.id.search_checkin_sort_cencel);
                TextView ensure1 = (TextView) view1.findViewById(R.id.search_checkin_sort_ensure);
                cancel1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                ensure1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });
                btn1 = (ImageButton) view1.findViewById(R.id.btn1);
                btn2 = (ImageButton) view1.findViewById(R.id.btn2);
                btn3 = (ImageButton) view1.findViewById(R.id.btn3);
                btn4 = (ImageButton) view1.findViewById(R.id.btn4);
                btn5 = (ImageButton) view1.findViewById(R.id.btn5);
                btn6 = (ImageButton) view1.findViewById(R.id.btn6);
                btn7 = (ImageButton) view1.findViewById(R.id.btn7);
                view1.findViewById(R.id.one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn1,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn2,btn3,btn4,btn5,btn6,btn7);
                    }
                });
                view1.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn2,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn6,btn7);
                    }
                });
                view1.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn3,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn2,btn4,btn5,btn6,btn7);
                    }
                });
                view1.findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn4,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn2,btn5,btn6,btn7);
                    }
                });
                view1.findViewById(R.id.five).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn5,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn2,btn6,btn7);
                    }
                });
                view1.findViewById(R.id.six).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn6,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn2,btn7);
                    }
                });
                view1.findViewById(R.id.seven).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ControlUpDown.control(btn7,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn2,btn6);
                    }
                });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_result_checkin,null);
        DrawableColor.changeColor(view,menu.findItem(R.id.sort),R.drawable.ic_sort_white_24dp,R.color.textColorPrimary);
        return super.onPrepareOptionsMenu(menu);
    }
}
