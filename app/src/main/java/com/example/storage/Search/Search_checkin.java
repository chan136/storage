package com.example.storage.Search;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.storage.Data.Syn_analyse;
import com.example.storage.Device.Select_device;
import com.example.storage.Supplier.Select_supplier;
import com.example.storage.R;
import com.example.storage.Utils.ControlUpDown;
import com.example.storage.Utils.DateUtils;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.ShowTime;
import com.example.storage.Utils.StatusBarUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Search_checkin extends AppCompatActivity implements View.OnClickListener{

    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private ImageButton btn5;
    private ImageButton btn6;
    private ImageButton btn7;
    private boolean flag;

    private EditText name;
    private EditText SKU;
    private EditText equal;
    private EditText start;
    private EditText end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_search_checkin,null);
        DrawableColor.changeColor(view,R.id.list_cancel,R.drawable.ic_cancel_reverse_24dp,R.color.search_text);
        DrawableColor.changeColor(view,R.id.list,R.drawable.ic_list_white_24dp,R.color.checkin);
        DrawableColor.changeColor(view,R.id.more_if,R.drawable.ic_add_black_24dp,R.color.checkin);
        DrawableColor.changeColor(view,R.id.sort,R.drawable.ic_sort_white_24dp,R.color.checkin);

        setContentView(R.layout.activity_search_checkin);

        name = (EditText)findViewById(R.id.one_name);
        SKU = (EditText)findViewById(R.id.one_SKU);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                SKU.setText(data.getExtras().getString("SKU"));
                name.setText(data.getExtras().getString("name"));
                break;
            default:break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void equal(View v){
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                String s = equal.getText().toString();
                String s1 = s.replace(s.substring(s.length()-5),hourOfDay+":"+minute);
                equal.setText(s1);
            }
        },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE), true).show();
    }

    public void start(View v){
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                String s = start.getText().toString();
                String s1 = s.replace(s.substring(s.length()-5),hourOfDay+":"+minute);
                start.setText(s1);
            }
        },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE), true).show();
    }

    public void end(View v){
        Calendar c = Calendar.getInstance();
        new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                String s = end.getText().toString();
                String s1 = s.replace(s.substring(s.length()-5),hourOfDay+":"+minute);
                end.setText(s1);
            }
        },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE), true).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.device_ensure:
                Intent result_checkin = new Intent(this, Result_checkin.class);
                startActivity(result_checkin);
                break;
            case R.id.list:
                Intent select_device = new Intent(this, Select_device.class);
                startActivityForResult(select_device,1);
                break;
            case R.id.list_cancel:
                View layout = findViewById(R.id.layout);
                layout.setVisibility(View.GONE);
                break;
            case R.id.sort:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view1 = LayoutInflater.from(this).inflate(R.layout.select_checkin_sort, null);

                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn4,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn5,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn6,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn7,R.drawable.ic_arrow_up_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_arrow_down_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_arrow_down_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_arrow_down_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn4,R.drawable.ic_arrow_down_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn5,R.drawable.ic_arrow_down_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn6,R.drawable.ic_arrow_down_24dp,R.color.checkin);
                DrawableColor.changeColor(view1,R.id.btn7,R.drawable.ic_arrow_down_24dp,R.color.checkin);

                builder1.setView(view1);
                builder1.create();
                final AlertDialog dialog1 = builder1.show();
                TextView cancel1 = (TextView) view1.findViewById(R.id.search_checkin_sort_cencel);
                TextView ensure1 = (TextView) view1.findViewById(R.id.search_checkin_sort_ensure);
                final TextView tv1 = (TextView)findViewById(R.id.tv1);
                final TextView tv2 = (TextView)findViewById(R.id.tv2);
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
                        flag = ControlUpDown.control(btn1,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn2,btn3,btn4,btn5,btn6,btn7);
                        tv1.setText("货品名称");
                        if (flag) {
                            tv2.setText("从低到高排列");
                        } else {
                            tv2.setText("从高到低排列");
                        }
                    }
                });
                view1.findViewById(R.id.two).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn2,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn6,btn7);
                        tv1.setText("货品SKU");
                        if (flag) {
                            tv2.setText("从低到高排列");
                        } else {
                            tv2.setText("从高到低排列");
                        }
                    }
                });
                view1.findViewById(R.id.three).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn3,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn2,btn4,btn5,btn6,btn7);
                        tv1.setText("数量");
                        if (flag) {
                            tv2.setText("从低到高排列");
                        } else {
                            tv2.setText("从高到低排列");
                        }
                    }
                });
                view1.findViewById(R.id.four).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn4,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn2,btn5,btn6,btn7);
                        tv1.setText("日期-时间");
                        if (flag) {
                            tv2.setText("从旧到新排列");
                        } else {
                            tv2.setText("从新到旧排列");
                        }
                    }
                });
                view1.findViewById(R.id.five).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn5,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn2,btn6,btn7);
                        tv1.setText("价格 ￥");
                        if (flag) {
                            tv2.setText("从低到高排列");
                        } else {
                            tv2.setText("从高到低排列");
                        }
                    }
                });
                view1.findViewById(R.id.six).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn6,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn2,btn7);
                        tv1.setText("供货商SKU");
                        if (flag) {
                            tv2.setText("从低到高排列");
                        } else {
                            tv2.setText("从高到低排列");
                        }
                    }
                });
                view1.findViewById(R.id.seven).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = ControlUpDown.control(btn7,R.drawable.ic_arrow_up_24dp,R.drawable.ic_arrow_down_24dp,btn1,btn3,btn4,btn5,btn2,btn6);
                        tv1.setText("供货商名称");
                        if (flag) {
                            tv2.setText("从低到高排列");
                        } else {
                            tv2.setText("从高到低排列");
                        }
                    }
                });
                break;
            case R.id.more_if:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.select_prop, null);
                builder.setView(view);
                builder.create();
                final AlertDialog dialog = builder.show();

                TextView cancel = (TextView) view.findViewById(R.id.cancel);
                TextView device = (TextView) view.findViewById(R.id.device);
                TextView supplier = (TextView) view.findViewById(R.id.supplier);
                TextView count = (TextView) view.findViewById(R.id.supplier_count);
                TextView price = (TextView) view.findViewById(R.id.supplier_price);
                TextView date = (TextView) view.findViewById(R.id.date);
                TextView describe = (TextView) view.findViewById(R.id.describe);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                device.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Search_checkin.this);
                        View view = LayoutInflater.from(Search_checkin.this).inflate(R.layout.point_device, null);
                        builder.setView(view);
                        builder.create();

                        DrawableColor.changeColor(view,R.id.select_device,R.drawable.ic_list_white_24dp,R.color.checkin);

                        final AlertDialog dialog = builder.show();
                        final TextView SKU = (TextView)view.findViewById(R.id.supplier_SKU);
                        final TextView name = (TextView)view.findViewById(R.id.supplier_name);
                        ImageButton select_device = (ImageButton)view.findViewById(R.id.select_device);
                        select_device.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent device_intent = new Intent(Search_checkin.this,Select_device.class);
                                startActivity(device_intent);
                            }
                        });
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
                                LinearLayout linear = (LinearLayout)findViewById(R.id.add);
                                View view = getLayoutInflater().inflate( R.layout.item_device, null ) ;
                                linear.addView(view);

                                TextView show_device = (TextView) view.findViewById(R.id.show_device);
                                if (SKU.length() != 0) {
                                    if (name.length() == 0){
                                        show_device.setText(SKU.getText());
                                    }else {
                                        show_device.setText("-"+SKU.getText());
                                    }
                                } else {
                                    show_device.setText(name.getText());
                                }

                                ImageButton device_cancel = (ImageButton)view.findViewById(R.id.device_cancel);
                                final View device_layout = view.findViewById(R.id.device_layout);
                                device_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        device_layout.setVisibility(View.GONE);
                                    }
                                });
                                dialog.dismiss();
                            }
                        });

                    }
                });
                supplier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Search_checkin.this);
                        View view = LayoutInflater.from(Search_checkin.this).inflate(R.layout.point_supplier, null);
                        builder.setView(view);
                        builder.create();

                        DrawableColor.changeColor(view,R.id.select_device,R.drawable.ic_list_white_24dp,R.color.checkin);

                        final AlertDialog dialog = builder.show();
                        final TextView SKU = (TextView)view.findViewById(R.id.supplier_SKU);
                        final TextView name = (TextView)view.findViewById(R.id.supplier_name);
                        ImageButton select_device = (ImageButton)view.findViewById(R.id.select_device);
                        select_device.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent device_intent = new Intent(Search_checkin.this,Select_supplier.class);
                                startActivity(device_intent);
                            }
                        });
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
                                LinearLayout linear = (LinearLayout)findViewById(R.id.add);
                                View view = getLayoutInflater().inflate( R.layout.item_supplier, null ) ;
                                linear.addView(view);

                                TextView show_device = (TextView) view.findViewById(R.id.show_device);
                                if (SKU.length() != 0) {
                                    if (name.length() == 0){
                                        show_device.setText(SKU.getText());
                                    }else {
                                        show_device.setText("-"+SKU.getText());
                                    }
                                } else {
                                    show_device.setText(name.getText());
                                }

                                ImageButton device_cancel = (ImageButton)view.findViewById(R.id.device_cancel);
                                final View device_layout = view.findViewById(R.id.device_layout);
                                device_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        device_layout.setVisibility(View.GONE);
                                    }
                                });
                                dialog.dismiss();
                            }
                        });
                    }
                });
                count.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Search_checkin.this);
                        View view = LayoutInflater.from(Search_checkin.this).inflate(R.layout.point_count, null);
                        builder.setView(view);
                        builder.create();
                        final AlertDialog dialog = builder.show();

                        final EditText equal = (EditText)view.findViewById(R.id.equal);
                        final EditText start = (EditText)view.findViewById(R.id.start);
                        final EditText end = (EditText)view.findViewById(R.id.end);
                        final TextView range = (TextView)view.findViewById(R.id.range);
                        final TextView point = (TextView)view.findViewById(R.id.point);
                        TextView cancel = (TextView)view.findViewById(R.id.cancel);
                        TextView ensure = (TextView)view.findViewById(R.id.ensure);
                        final RelativeLayout point_equal = (RelativeLayout)view.findViewById(R.id.point_equal);
                        final RelativeLayout range_start = (RelativeLayout)view.findViewById(R.id.range_start);
                        final RelativeLayout range_end = (RelativeLayout)view.findViewById(R.id.range_end);

                        point.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                point.setTextColor(getResources().getColor(R.color.checkin));
                                point.setBackground(getResources().getDrawable(R.drawable.tv_green_border));
                                range.setTextColor(getResources().getColor(R.color.gray));
                                range.setBackground(getResources().getDrawable(R.drawable.tv_black_border));
                                point_equal.setVisibility(View.VISIBLE);
                                range_start.setVisibility(View.GONE);
                                range_end.setVisibility(View.GONE);
                            }
                        });
                        range.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                point.setTextColor(getResources().getColor(R.color.gray));
                                point.setBackground(getResources().getDrawable(R.drawable.tv_black_border));
                                range.setTextColor(getResources().getColor(R.color.checkin));
                                range.setBackground(getResources().getDrawable(R.drawable.tv_green_border));
                                point_equal.setVisibility(View.GONE);
                                range_start.setVisibility(View.VISIBLE);
                                range_end.setVisibility(View.VISIBLE);
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        ensure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LinearLayout linear = (LinearLayout)findViewById(R.id.add);
                                View view = getLayoutInflater().inflate( R.layout.item_count, null ) ;

                                TextView show = (TextView) view.findViewById(R.id.show_device);
                                if (point_equal.getVisibility() == View.VISIBLE) {
                                    linear.addView(view);
                                    show.setText(" "+equal.getText());
                                    dialog.dismiss();
                                } else {
                                    if (Integer.parseInt(end.getText().toString()) > Integer.parseInt(start.getText().toString())) {
                                        linear.addView(view);
                                        show.setText(" "+start.getText()+" ~ "+end.getText());
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(Search_checkin.this,"不能设置空条件",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                ImageButton device_cancel = (ImageButton)view.findViewById(R.id.device_cancel);
                                final View device_layout = view.findViewById(R.id.device_layout);
                                device_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        device_layout.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                });
                price.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Search_checkin.this);
                        View view = LayoutInflater.from(Search_checkin.this).inflate(R.layout.point_count, null);
                        builder.setView(view);
                        builder.create();
                        final AlertDialog dialog = builder.show();

                        final EditText equal = (EditText)view.findViewById(R.id.equal);
                        final EditText start = (EditText)view.findViewById(R.id.start);
                        final EditText end = (EditText)view.findViewById(R.id.end);
                        final TextView range = (TextView)view.findViewById(R.id.range);
                        final TextView point = (TextView)view.findViewById(R.id.point);
                        TextView cancel = (TextView)view.findViewById(R.id.cancel);
                        TextView ensure = (TextView)view.findViewById(R.id.ensure);
                        final RelativeLayout point_equal = (RelativeLayout)view.findViewById(R.id.point_equal);
                        final RelativeLayout range_start = (RelativeLayout)view.findViewById(R.id.range_start);
                        final RelativeLayout range_end = (RelativeLayout)view.findViewById(R.id.range_end);

                        point.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                point.setTextColor(getResources().getColor(R.color.checkin));
                                point.setBackground(getResources().getDrawable(R.drawable.tv_green_border));
                                range.setTextColor(getResources().getColor(R.color.gray));
                                range.setBackground(getResources().getDrawable(R.drawable.tv_black_border));
                                point_equal.setVisibility(View.VISIBLE);
                                range_start.setVisibility(View.GONE);
                                range_end.setVisibility(View.GONE);
                            }
                        });
                        range.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                point.setTextColor(getResources().getColor(R.color.gray));
                                point.setBackground(getResources().getDrawable(R.drawable.tv_black_border));
                                range.setTextColor(getResources().getColor(R.color.checkin));
                                range.setBackground(getResources().getDrawable(R.drawable.tv_green_border));
                                point_equal.setVisibility(View.GONE);
                                range_start.setVisibility(View.VISIBLE);
                                range_end.setVisibility(View.VISIBLE);
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        ensure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LinearLayout linear = (LinearLayout)findViewById(R.id.add);
                                View view = getLayoutInflater().inflate( R.layout.item_price, null ) ;

                                TextView show = (TextView) view.findViewById(R.id.show_device);
                                if (point_equal.getVisibility() == View.VISIBLE) {
                                    linear.addView(view);
                                    show.setText("= "+equal.getText()+".0");
                                    dialog.dismiss();
                                } else {
                                    if (Integer.parseInt(end.getText().toString()) > Integer.parseInt(start.getText().toString())) {
                                        linear.addView(view);
                                        show.setText(": "+start.getText()+".0"+" ~ "+end.getText()+".0");
                                        dialog.dismiss();
                                    } else {
                                        Toast.makeText(Search_checkin.this,"不能设置空条件",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                ImageButton device_cancel = (ImageButton)view.findViewById(R.id.device_cancel);
                                final View device_layout = view.findViewById(R.id.device_layout);
                                device_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        device_layout.setVisibility(View.GONE);
                                    }
                                });
                            }
                        });
                    }
                });
                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Search_checkin.this);
                        View view = LayoutInflater.from(Search_checkin.this).inflate(R.layout.point_date, null);
                        builder.setView(view);
                        builder.create();
                        final AlertDialog dialog = builder.show();

                        equal = (EditText)view.findViewById(R.id.equal);
                        start = (EditText)view.findViewById(R.id.start);
                        end = (EditText)view.findViewById(R.id.end);
                        final TextView range = (TextView)view.findViewById(R.id.range);
                        final TextView point = (TextView)view.findViewById(R.id.point);
                        TextView cancel = (TextView)view.findViewById(R.id.cancel);
                        TextView ensure = (TextView)view.findViewById(R.id.ensure);
                        final RelativeLayout point_equal = (RelativeLayout)view.findViewById(R.id.point_equal);
                        final RelativeLayout range_start = (RelativeLayout)view.findViewById(R.id.range_start);
                        final RelativeLayout range_end = (RelativeLayout)view.findViewById(R.id.range_end);

                        ShowTime.staticShow(equal,new SimpleDateFormat("yyyy MM月dd日   HH:mm"));
                        ShowTime.staticShow(start,new SimpleDateFormat("yyyy MM月dd日   00:00"),1);
                        ShowTime.staticShow(end,new SimpleDateFormat("yyyy MM月dd日   HH:mm"));

                        point.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                point.setTextColor(getResources().getColor(R.color.checkin));
                                point.setBackground(getResources().getDrawable(R.drawable.tv_green_border));
                                range.setTextColor(getResources().getColor(R.color.gray));
                                range.setBackground(getResources().getDrawable(R.drawable.tv_black_border));
                                point_equal.setVisibility(View.VISIBLE);
                                range_start.setVisibility(View.GONE);
                                range_end.setVisibility(View.GONE);
                            }
                        });
                        range.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                point.setTextColor(getResources().getColor(R.color.gray));
                                point.setBackground(getResources().getDrawable(R.drawable.tv_black_border));
                                range.setTextColor(getResources().getColor(R.color.checkin));
                                range.setBackground(getResources().getDrawable(R.drawable.tv_green_border));
                                point_equal.setVisibility(View.GONE);
                                range_start.setVisibility(View.VISIBLE);
                                range_end.setVisibility(View.VISIBLE);
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        ensure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                LinearLayout linear = (LinearLayout)findViewById(R.id.add);
                                View view = getLayoutInflater().inflate( R.layout.item_date, null ) ;
                                linear.addView(view);

                                TextView show = (TextView) view.findViewById(R.id.show_device);
                                if (point_equal.getVisibility() == View.VISIBLE) {
                                    show.setText("   = "+equal.getText());
                                    dialog.dismiss();
                                } else {
                                    show.setText("      "+start.getText()+" ~"+"\n"+"      "+end.getText());
                                    dialog.dismiss();
                                }
                                ImageButton device_cancel = (ImageButton)view.findViewById(R.id.device_cancel);
                                final View device_layout = view.findViewById(R.id.device_layout);
                                device_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        device_layout.setVisibility(View.GONE);
                                    }
                                });
                    }
                });

                            }
                        });
                describe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Search_checkin.this);
                        View view = LayoutInflater.from(Search_checkin.this).inflate(R.layout.point_describe, null);
                        builder.setView(view);
                        builder.create();
                        final AlertDialog dialog = builder.show();

                        final EditText describe = (EditText)view.findViewById(R.id.describe);
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
                                LinearLayout linear = (LinearLayout)findViewById(R.id.add);
                                View view = getLayoutInflater().inflate( R.layout.item_describe, null ) ;
                                linear.addView(view);

                                TextView show_device = (TextView) view.findViewById(R.id.show_device);
                                show_device.setText(describe.getText());
                                ImageButton device_cancel = (ImageButton)view.findViewById(R.id.device_cancel);
                                final View device_layout = view.findViewById(R.id.device_layout);
                                device_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        device_layout.setVisibility(View.GONE);
                                    }
                                });
                                dialog.dismiss();
                            }
                        });
                    }
                });
                break;
            default:break;
        }
    }
}
