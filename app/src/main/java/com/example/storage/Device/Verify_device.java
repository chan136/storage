package com.example.storage.Device;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.ChooseCheckinAdapter;
import com.example.storage.Bean.ChooseCheckoutAdapter;
import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewCheckout;
import com.example.storage.Bean.NewDevice;
import com.example.storage.R;
import com.example.storage.Search.Choose_buyer;
import com.example.storage.Search.Choose_checkin;
import com.example.storage.Search.Choose_checkout;
import com.example.storage.Search.Choose_supplier;
import com.example.storage.Supplier.Supplier_photo;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.EditTextPic;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Verify_device extends AppCompatActivity implements View.OnClickListener,GestureDetector.OnGestureListener{

    private GestureDetector detector;

    private TextView device_SKU;
    private TextView device_name;
    private EditText brand;
    private EditText type;
    private EditText size;
    private EditText color;
    private EditText specification;
    private EditText describe;
    private ImageButton more;
    private ImageButton image;
    private NewDevice device;

    private TextView param;
    private TextView activity;
    private TextView analyse;
    private TextView statistics;

    private View param_layout;

    private View activity_layout;
    private TextView now;
    private TextView checkin_count;

    private View analyse_layout;
    private TextView week;
    private TextView month;
    private TextView one_year;
    private TextView five_year;
    private TextView supplierCount;
    private TextView supplier_money;
    private TextView supplier_price;
    private TextView checkin_head;
    private TextView count_checkin;
    private TextView checkin_percent;
    private TextView supplier_all;
    private TextView buyer_count;
    private TextView buyer_money;
    private TextView buyer_price;
    private TextView checkout_head;
    private TextView count_checkout;
    private TextView checkout_percent;
    private TextView buyer_all;

    private List<NewDevice> list = new ArrayList<>();
    private List<NewCheckin> checkinList = new ArrayList<>();
    private List<NewCheckout> checkoutList = new ArrayList<>();
    private List<NewCheckin> checkin_List = new ArrayList<>();
    private List<NewCheckout> checkout_List = new ArrayList<>();
    private ChooseCheckinAdapter checkinAdapter;
    private ChooseCheckoutAdapter checkoutAdapter;

    private String[] colorName = {"未确定","红","澄","粉红","深蓝","蓝","青","绿","黄","紫","棕","黑","灰","银","白","透明"};
    private int[] colorImage = {R.drawable.z0,R.drawable.z1,R.drawable.z2,R.drawable.z3,R.drawable.z4,R.drawable.z5,R.drawable.z6,R.drawable.z7,R.drawable.z8,R.drawable.z9,R.drawable.z10,R.drawable.z11,R.drawable.z12,R.drawable.z13,R.drawable.z14,R.drawable.z15};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_verify_device,null);
        DrawableColor.changeColor(view,R.id.device,R.drawable.ic_devices_other_black_36dp,R.color.light_white);
        DrawableColor.changeColor(view,R.id.image_photo,R.drawable.ic_image_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.more,R.drawable.ic_more_horiz_white_24dp,R.color.select_device);
        DrawableColor.changeColor(view,R.id.refresh,R.drawable.ic_refresh_white_24dp,R.color.select_device);
        setContentView(R.layout.activity_verify_device);

        //创建手势检测器
        detector = new GestureDetector(this,this);
        list = DataSupport.findAll(NewDevice.class);

        device_SKU = (TextView)findViewById(R.id.supplier_SKU);
        device_name = (TextView)findViewById(R.id.supplier_name);
        brand = (EditText) findViewById(R.id.brand);
        type = (EditText) findViewById(R.id.type);
        size = (EditText) findViewById(R.id.size);
        color = (EditText) findViewById(R.id.color);
        specification = (EditText) findViewById(R.id.specification);
        describe = (EditText) findViewById(R.id.describe);
        more = (ImageButton)findViewById(R.id.more);
        image = (ImageButton)findViewById(R.id.image_photo);

        param = (TextView) findViewById(R.id.param);
        activity = (TextView) findViewById(R.id.activity);
        analyse = (TextView) findViewById(R.id.analyse);
        statistics = (TextView) findViewById(R.id.statistics);

        param_layout = findViewById(R.id.param_layout);
        activity_layout = findViewById(R.id.activity_layout);
        analyse_layout = findViewById(R.id.analyse_layout);

        week = (TextView)findViewById(R.id.week);
        month = (TextView)findViewById(R.id.month);
        one_year = (TextView)findViewById(R.id.one_year);
        five_year = (TextView)findViewById(R.id.five_year);

        supplierCount = (TextView)findViewById(R.id.supplierCount);
        supplier_money = (TextView)findViewById(R.id.supplier_money);
        supplier_price = (TextView)findViewById(R.id.supplier_price);
        checkin_head = (TextView)findViewById(R.id.checkin_head);
        count_checkin = (TextView)findViewById(R.id.count_checkin);
        checkin_percent = (TextView)findViewById(R.id.checkin_percent);
        supplier_all = (TextView)findViewById(R.id.supplier_all);

        buyer_count = (TextView)findViewById(R.id.buyer_count);
        buyer_money = (TextView)findViewById(R.id.buyer_money);
        buyer_price = (TextView)findViewById(R.id.buyer_price);
        checkout_head = (TextView)findViewById(R.id.checkout_head);
        count_checkout = (TextView)findViewById(R.id.count_checkout);
        checkout_percent = (TextView)findViewById(R.id.checkout_percent);
        buyer_all = (TextView)findViewById(R.id.buyer_all);

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

        device = (NewDevice) getIntent().getSerializableExtra("device");
        if (device != null) {
            device_SKU.setText(device.getSKU());
            device_name.setText(device.getName());
            brand.setText(device.getBrand());
            type.setText(device.getType());
            size.setText(device.getSize());
            specification.setText(device.getSpecification());
            describe.setText(device.getDescribe());

            for (int i = 0;i < colorName.length; i++) {
                if (colorName[i].equals(device.getColor().trim())) {
                    EditTextPic.setDrawable(view,color,colorImage[i],colorName[i]);
                }
            }

        }

        checkin_init();
        checkinAdapter = new ChooseCheckinAdapter(this,R.layout.checkin_listview_item,checkinList);
        ListView listView = (ListView)findViewById(R.id.checkin_listview);
        listView.setAdapter(checkinAdapter);

        now = (TextView)findViewById(R.id.now);
        now.setText(new SimpleDateFormat("HH:mm").format(new Date()));

        checkin_count = (TextView)findViewById(R.id.device_count);
        checkin_count.setText("0");
        for (NewCheckin checkin : checkinList) {
            if (checkin.getDevice_SKU().equals(device_SKU.getText().toString())) {
                checkin_count.setText(checkin.getCount());
            }
        }

        checkout_init();
        checkoutAdapter = new ChooseCheckoutAdapter(this,R.layout.checkout_listview_item,checkoutList);
        ListView listView1 = (ListView)findViewById(R.id.checkout_listview);
        listView1.setAdapter(checkoutAdapter);

        checkin_List = DataSupport.where("device_SKU = ?",device_SKU.getText().toString()).find(NewCheckin.class);
        if (checkin_List.size() == 0) {
            checkin_head.setHeight(60);
            count_checkin.setVisibility(View.GONE);
            checkin_percent.setVisibility(View.GONE);
            supplier_all.setVisibility(View.GONE);
        } else {
            count_checkin.setVisibility(View.VISIBLE);
            checkin_percent.setVisibility(View.VISIBLE);
            supplier_all.setVisibility(View.VISIBLE);
            for (NewCheckin checkin : checkin_List) {
                checkin_head.setText("1供货商/入库总量"+checkin.getCount());
                count_checkin.setText(checkin.getCount());
            }
        }

        checkout_List = DataSupport.where("device_SKU = ?",device_SKU.getText().toString()).find(NewCheckout.class);
        if (checkout_List.size() == 0) {
            checkout_head.setHeight(60);
            count_checkout.setVisibility(View.GONE);
            checkout_percent.setVisibility(View.GONE);
            buyer_all.setVisibility(View.GONE);
        } else {
            count_checkout.setVisibility(View.VISIBLE);
            checkout_percent.setVisibility(View.VISIBLE);
            buyer_all.setVisibility(View.VISIBLE);
            for (NewCheckout checkout : checkout_List) {
                checkout_head.setText("1买家/出库总量"+checkout.getCount());
                count_checkout.setText(checkout.getCount());
            }
        }

    }

    private void checkin_init() {
        List<NewCheckin> checkins = DataSupport.where("device_SKU = ?",device_SKU.getText().toString()).find(NewCheckin.class);
        checkinList.clear();
        for (NewCheckin checkin:checkins) {
            checkinList.add(checkin);
        }
    }

    private void checkout_init() {
        List<NewCheckout> checkouts = DataSupport.where("device_SKU = ?",device_SKU.getText().toString()).find(NewCheckout.class);
        checkoutList.clear();
        for (NewCheckout checkout:checkouts) {
            checkoutList.add(checkout);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_photo:
                startActivity(new Intent(this, Supplier_photo.class));
                break;
            case R.id.param:
                param_layout.setVisibility(View.VISIBLE);
                activity_layout.setVisibility(View.GONE);
                analyse_layout.setVisibility(View.GONE);
                param.setTextColor(getResources().getColor(R.color.textColorPrimary));
                activity.setTextColor(getResources().getColor(R.color.light_white));
                analyse.setTextColor(getResources().getColor(R.color.light_white));
                statistics.setTextColor(getResources().getColor(R.color.light_white));
                break;
            case R.id.activity:
                param_layout.setVisibility(View.GONE);
                activity_layout.setVisibility(View.VISIBLE);
                analyse_layout.setVisibility(View.GONE);
                param.setTextColor(getResources().getColor(R.color.light_white));
                activity.setTextColor(getResources().getColor(R.color.textColorPrimary));
                analyse.setTextColor(getResources().getColor(R.color.light_white));
                statistics.setTextColor(getResources().getColor(R.color.light_white));
                break;
            case R.id.analyse:
                param_layout.setVisibility(View.GONE);
                activity_layout.setVisibility(View.GONE);
                analyse_layout.setVisibility(View.VISIBLE);
                param.setTextColor(getResources().getColor(R.color.light_white));
                activity.setTextColor(getResources().getColor(R.color.light_white));
                analyse.setTextColor(getResources().getColor(R.color.textColorPrimary));
                statistics.setTextColor(getResources().getColor(R.color.light_white));
                break;
            case R.id.statistics:
                param.setTextColor(getResources().getColor(R.color.light_white));
                activity.setTextColor(getResources().getColor(R.color.light_white));
                analyse.setTextColor(getResources().getColor(R.color.light_white));
                statistics.setTextColor(getResources().getColor(R.color.textColorPrimary));
                break;
            case R.id.more:
                Intent intent = new Intent(this,Update_default.class);
                intent.putExtra("device_SKU",device_SKU.getText());
                intent.putExtra("device_name",device_name.getText());
                startActivity(intent);
                break;
            case R.id.refresh:
                checkinAdapter.notifyDataSetChanged();
                checkoutAdapter.notifyDataSetChanged();
                Toast.makeText(this,"刷新成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkin_all:
                startActivity(new Intent(this, Choose_checkin.class));
                break;
            case R.id.checkout_all:
                startActivity(new Intent(this, Choose_checkout.class));
                break;
            case R.id.week:
                week.setTextColor(getResources().getColor(R.color.textColorPrimary));
                week.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                month.setTextColor(getResources().getColor(R.color.defaultColor));
                month.setBackgroundColor(getResources().getColor(R.color.list_view));
                one_year.setTextColor(getResources().getColor(R.color.defaultColor));
                one_year.setBackgroundColor(getResources().getColor(R.color.list_view));
                five_year.setTextColor(getResources().getColor(R.color.defaultColor));
                five_year.setBackgroundColor(getResources().getColor(R.color.list_view));
                break;
            case R.id.month:
                week.setTextColor(getResources().getColor(R.color.defaultColor));
                week.setBackgroundColor(getResources().getColor(R.color.list_view));
                month.setTextColor(getResources().getColor(R.color.textColorPrimary));
                month.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                one_year.setTextColor(getResources().getColor(R.color.defaultColor));
                one_year.setBackgroundColor(getResources().getColor(R.color.list_view));
                five_year.setTextColor(getResources().getColor(R.color.defaultColor));
                five_year.setBackgroundColor(getResources().getColor(R.color.list_view));
                break;
            case R.id.one_year:
                week.setTextColor(getResources().getColor(R.color.defaultColor));
                week.setBackgroundColor(getResources().getColor(R.color.list_view));
                month.setTextColor(getResources().getColor(R.color.defaultColor));
                month.setBackgroundColor(getResources().getColor(R.color.list_view));
                one_year.setTextColor(getResources().getColor(R.color.textColorPrimary));
                one_year.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                five_year.setTextColor(getResources().getColor(R.color.defaultColor));
                five_year.setBackgroundColor(getResources().getColor(R.color.list_view));
                break;
            case R.id.five_year:
                week.setTextColor(getResources().getColor(R.color.defaultColor));
                week.setBackgroundColor(getResources().getColor(R.color.list_view));
                month.setTextColor(getResources().getColor(R.color.defaultColor));
                month.setBackgroundColor(getResources().getColor(R.color.list_view));
                one_year.setTextColor(getResources().getColor(R.color.defaultColor));
                one_year.setBackgroundColor(getResources().getColor(R.color.list_view));
                five_year.setTextColor(getResources().getColor(R.color.textColorPrimary));
                five_year.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.supplierCount:
                supplierCount.setBackgroundColor(getResources().getColor(R.color.list_view));
                supplier_money.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                supplier_price.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                if (checkin_List.size() == 0) {
                    checkin_head.setHeight(60);
                    count_checkin.setVisibility(View.GONE);
                    checkin_percent.setVisibility(View.GONE);
                    supplier_all.setVisibility(View.GONE);
                } else {
                    count_checkin.setVisibility(View.VISIBLE);
                    checkin_percent.setVisibility(View.VISIBLE);
                    supplier_all.setVisibility(View.VISIBLE);
                    for (NewCheckin checkin : checkin_List) {
                        checkin_head.setText("1供货商/入库总量"+checkin.getCount());
                        count_checkin.setText(checkin.getCount());
                    }
                }
                break;
            case R.id.supplier_money:
                supplierCount.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                supplier_money.setBackgroundColor(getResources().getColor(R.color.list_view));
                supplier_price.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                if (checkin_List.size() == 0) {
                    checkin_head.setHeight(60);
                    checkin_head.setText("0供货商/入库总额0￥");
                    count_checkin.setVisibility(View.GONE);
                    checkin_percent.setVisibility(View.GONE);
                    supplier_all.setVisibility(View.GONE);
                } else {
                    count_checkin.setVisibility(View.VISIBLE);
                    checkin_percent.setVisibility(View.VISIBLE);
                    supplier_all.setVisibility(View.VISIBLE);
                    for (NewCheckin checkin : checkin_List) {
                        checkin_head.setText("1供货商/入库总额"+Integer.parseInt(checkin.getCount())*Integer.parseInt(checkin.getPrice())+"￥");
                        count_checkin.setText(checkin.getPrice());
                    }
                }
                break;
            case R.id.supplier_price:
                supplierCount.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                supplier_money.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                supplier_price.setBackgroundColor(getResources().getColor(R.color.list_view));
                if (checkin_List.size() == 0) {
                    checkin_head.setHeight(60);
                    checkin_head.setText("0供货商");
                    count_checkin.setVisibility(View.GONE);
                    checkin_percent.setVisibility(View.GONE);
                    supplier_all.setVisibility(View.GONE);
                } else {
                    count_checkin.setVisibility(View.VISIBLE);
                    checkin_percent.setVisibility(View.VISIBLE);
                    supplier_all.setVisibility(View.VISIBLE);
                    for (NewCheckin checkin : checkin_List) {
                        checkin_head.setText("1供货商");
                        count_checkin.setText(checkin.getPrice());
                    }
                }
                break;
            case R.id.buyer_count:
                buyer_count.setBackgroundColor(getResources().getColor(R.color.list_view));
                buyer_money.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                buyer_price.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                if (checkout_List.size() == 0) {
                    checkout_head.setHeight(60);
                    count_checkout.setVisibility(View.GONE);
                    checkout_percent.setVisibility(View.GONE);
                    buyer_all.setVisibility(View.GONE);
                } else {
                    count_checkout.setVisibility(View.VISIBLE);
                    checkout_percent.setVisibility(View.VISIBLE);
                    buyer_all.setVisibility(View.VISIBLE);
                    for (NewCheckout checkout : checkout_List) {
                        checkout_head.setText("1买家/出库总量"+checkout.getCount());
                        count_checkout.setText(checkout.getCount());
                    }
                }
                break;
            case R.id.buyer_money:
                buyer_count.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                buyer_money.setBackgroundColor(getResources().getColor(R.color.list_view));
                buyer_price.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                if (checkout_List.size() == 0) {
                    checkout_head.setHeight(60);
                    checkout_head.setText("0买家/出库总额0￥");
                    count_checkout.setVisibility(View.GONE);
                    checkout_percent.setVisibility(View.GONE);
                    buyer_all.setVisibility(View.GONE);
                } else {
                    count_checkout.setVisibility(View.VISIBLE);
                    checkout_percent.setVisibility(View.VISIBLE);
                    buyer_all.setVisibility(View.VISIBLE);
                    for (NewCheckout checkout : checkout_List) {
                        checkout_head.setText("1买家/出库总额"+Integer.parseInt(checkout.getCount())*Integer.parseInt(checkout.getPrice())+"￥");
                        count_checkout.setText(checkout.getPrice());
                    }
                }
                break;
            case R.id.buyer_price:
                buyer_count.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                buyer_money.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
                buyer_price.setBackgroundColor(getResources().getColor(R.color.list_view));
                if (checkout_List.size() == 0) {
                    checkout_head.setHeight(60);
                    checkout_head.setText("0供货商");
                    count_checkout.setVisibility(View.GONE);
                    checkout_percent.setVisibility(View.GONE);
                    buyer_all.setVisibility(View.GONE);
                } else {
                    count_checkout.setVisibility(View.VISIBLE);
                    checkout_percent.setVisibility(View.VISIBLE);
                    buyer_all.setVisibility(View.VISIBLE);
                    for (NewCheckout checkout : checkout_List) {
                        checkout_head.setText("1买家");
                        count_checkout.setText(checkout.getPrice());
                    }
                }
                break;
            case R.id.supplier_all:
                startActivity(new Intent(this, Choose_supplier.class));
                break;
            case R.id.buyer_all:
                startActivity(new Intent(this, Choose_buyer.class));
                break;
            default:break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.keep_log,menu);
        getMenuInflater().inflate(R.menu.mode_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mode:
                Intent intent = new Intent(this, Update_device.class);
                intent.putExtra("device",device);
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
                        DataSupport.deleteAll(NewDevice.class,"SKU = ?",device.getSKU());
                        Toast.makeText(Verify_device.this,"删除成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent intent = new Intent(Verify_device.this, Select_device.class);
                        intent.putExtra("device",device);
                        startActivity(intent);
                    }
                });
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        detector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    //将该activity上的触碰事件交给GestureDetector处理
    public boolean onTouchEvent(MotionEvent me){
        return detector.onTouchEvent(me);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        return false;
    }

    /**
     * 滑屏监测
     *
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        float minMove = 20;         //最小滑动距离
        float minVelocity = 0;      //最小滑动速度
        float beginX = e1.getX();
        float endX = e2.getX();
        int index = -1;

        if(beginX-endX>minMove&&Math.abs(velocityX)>minVelocity){   //左滑
            for (int i = 0;i < list.size();i ++) {
                if (list.get(i).getSKU().equals(device.getSKU())) {
                    index = i;
                }
            }
            if (index - 1 < 0) {
                Toast.makeText(this,"已滑动至表头",Toast.LENGTH_SHORT).show();
            } else {
                finish();
                NewDevice last = list.get(index - 1);
                Intent verify = new Intent(this, Verify_device.class);
                verify.putExtra("device",last);
                startActivity(verify);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            }
        }else if(endX-beginX>minMove&&Math.abs(velocityX)>minVelocity){   //右滑
            for (int i = 0;i < list.size();i ++) {
                if (list.get(i).getSKU().equals(device.getSKU())) {
                    index = i;
                }
            }
            if (index + 2 > list.size()) {
                Toast.makeText(this,"已滑动至表尾",Toast.LENGTH_SHORT).show();
            } else {
                finish();
                NewDevice next = list.get(index + 1);
                Intent verify = new Intent(this, Verify_device.class);
                verify.putExtra("device",next);
                startActivity(verify);
                overridePendingTransition(R.anim.in_from_left, R.anim.out_from_right);
            }
        }

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float velocityX,
                            float velocityY) {

        return false;
    }

}
