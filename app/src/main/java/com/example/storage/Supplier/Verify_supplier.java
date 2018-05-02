package com.example.storage.Supplier;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.NewDevice;
import com.example.storage.Bean.NewSupplier;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Verify_supplier extends AppCompatActivity implements View.OnClickListener,GestureDetector.OnGestureListener{

    private GestureDetector detector;
    private List<NewSupplier> list;

    private TextView param;
    private TextView activity;
    private TextView analyse;
    private TextView statistics;

    private TextView supplier_SKU;
    private TextView supplier_name;
    private EditText address;
    private EditText tel;
    private EditText phone;
    private EditText email;
    private EditText weixin;
    private EditText qq;
    private EditText describe;
    private EditText webpage;

    private NewSupplier supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_verify_supplier,null);
        DrawableColor.changeColor(view,R.id.image_photo,R.drawable.ic_image_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.person,R.drawable.person_256dp,R.color.textColorPrimary);

        setContentView(R.layout.activity_verify_supplier);

        //创建手势检测器
        detector = new GestureDetector(this,this);
        list = DataSupport.findAll(NewSupplier.class);

        param = (TextView) findViewById(R.id.param);
        activity = (TextView) findViewById(R.id.activity);
        analyse = (TextView) findViewById(R.id.analyse);
        statistics = (TextView) findViewById(R.id.statistics);

        supplier_SKU = (TextView)findViewById(R.id.supplier_SKU);
        supplier_name = (TextView)findViewById(R.id.supplier_name);
        address = (EditText)findViewById(R.id.address);
        tel = (EditText)findViewById(R.id.tel);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        weixin = (EditText)findViewById(R.id.weixin);
        qq = (EditText)findViewById(R.id.qq);
        describe = (EditText)findViewById(R.id.describe);
        webpage = (EditText)findViewById(R.id.webpage);

        supplier = (NewSupplier)getIntent().getSerializableExtra("supplier");
        supplier_SKU.setText(supplier.getSKU());
        supplier_name.setText(supplier.getName());
        address.setText(supplier.getAddress());
        tel.setText(supplier.getTel());
        phone.setText(supplier.getPhone());
        email.setText(supplier.getEmail());
        weixin.setText(supplier.getWeixin());
        qq.setText(supplier.getQq());
        describe.setText(supplier.getDescribe());
        webpage.setText(supplier.getWebpage());

        param.setText("账号");

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
            case R.id.image_photo:
                startActivity(new Intent(this,Supplier_photo.class));
                break;
            case R.id.param:
                param.setTextColor(getResources().getColor(R.color.textColorPrimary));
                activity.setTextColor(getResources().getColor(R.color.light_white));
                analyse.setTextColor(getResources().getColor(R.color.light_white));
                statistics.setTextColor(getResources().getColor(R.color.light_white));
                break;
            case R.id.activity:
                param.setTextColor(getResources().getColor(R.color.light_white));
                activity.setTextColor(getResources().getColor(R.color.textColorPrimary));
                analyse.setTextColor(getResources().getColor(R.color.light_white));
                statistics.setTextColor(getResources().getColor(R.color.light_white));
                break;
            case R.id.analyse:
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
                Intent intent = new Intent(this, Update_supplier.class);
                intent.putExtra("supplier",supplier);
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
                        DataSupport.deleteAll(NewSupplier.class,"SKU = ?",supplier.getSKU());
                        Toast.makeText(Verify_supplier.this,"删除成功",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        Intent intent = new Intent(Verify_supplier.this, Select_supplier.class);
                        intent.putExtra("supplier",supplier);
                        finish();
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
                if (list.get(i).getSKU().equals(supplier.getSKU())) {
                    index = i;
                }
            }
            if (index - 1 < 0) {
                Toast.makeText(this,"已滑动至表头",Toast.LENGTH_SHORT).show();
            } else {
                finish();
                NewSupplier last = list.get(index - 1);
                Intent verify = new Intent(this, Verify_supplier.class);
                verify.putExtra("supplier",last);
                startActivity(verify);
                overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
            }
        }else if(endX-beginX>minMove&&Math.abs(velocityX)>minVelocity){   //右滑
            for (int i = 0;i < list.size();i ++) {
                if (list.get(i).getSKU().equals(supplier.getSKU())) {
                    index = i;
                }
            }
            if (index + 2 > list.size()) {
                Toast.makeText(this,"已滑动至表尾",Toast.LENGTH_SHORT).show();
            } else {
                finish();
                NewSupplier next = list.get(index + 1);
                Intent verify = new Intent(this, Verify_supplier.class);
                verify.putExtra("supplier",next);
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
