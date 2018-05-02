package com.example.storage.Device;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Supplier.Select_supplier;
import com.example.storage.Buyer.Select_buyer;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

public class Update_default extends AppCompatActivity implements View.OnClickListener{

    private ImageButton back;
    private TextView device_SKU;
    private TextView device_name;
    private View checkin_layout;
    private View checkout_layout;
    private ImageButton checkin_button;
    private ImageButton checkout_button;
    private ImageButton checkin_not;
    private ImageButton checkout_not;
    private ImageButton checkin_list;
    private ImageButton checkout_list;
    private TextView default_checkin;
    private TextView default_checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_update_default,null);
        DrawableColor.changeColor(view,R.id.checkin_button,R.drawable.ic_radio_button_unchecked_black_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.back,R.drawable.ic_chevron_right_white_24dp,R.color.colorPrimary);
        setContentView(R.layout.activity_update_default);

        back = (ImageButton)findViewById(R.id.back);
        device_SKU = (TextView)findViewById(R.id.supplier_SKU);
        device_name = (TextView)findViewById(R.id.supplier_name);
        checkin_layout = findViewById(R.id.checkin_layout);
        checkout_layout = findViewById(R.id.checkout_layout);
        checkin_button = (ImageButton)findViewById(R.id.checkin_button);
        checkout_button = (ImageButton)findViewById(R.id.checkout_button);
        checkin_not = (ImageButton)findViewById(R.id.checkin_not);
        checkout_not = (ImageButton)findViewById(R.id.checkout_not);
        checkin_list = (ImageButton)findViewById(R.id.checkin_list);
        checkout_list = (ImageButton)findViewById(R.id.checkout_list);
        default_checkin = (TextView)findViewById(R.id.default_checkin);
        default_checkout = (TextView)findViewById(R.id.default_checkout);

        Intent intent = getIntent();
        device_SKU.setText(intent.getStringExtra("device_SKU"));
        device_name.setText(intent.getStringExtra("device_name"));

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.select_device));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.select_device));
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkin_button:
                View checkin_view = LayoutInflater.from(this).inflate(R.layout.activity_device,null);
                if (checkin_layout.getVisibility() == View.GONE) {
                    DrawableColor.changeColor(checkin_view,R.id.checkin_button,R.drawable.ic_radio_button_checked_black_24dp,R.color.checkin);
                    DrawableColor.changeColor(checkin_view,R.id.checkin_not,R.drawable.ic_not_interested_black_24dp,R.color.checkin);
                    DrawableColor.changeColor(checkin_view,R.id.checkin_list,R.drawable.ic_list_white_24dp,R.color.checkin);
                    checkin_button.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                    checkin_not.setBackground(getResources().getDrawable(R.drawable.ic_not_interested_black_24dp));
                    checkin_list.setBackground(getResources().getDrawable(R.drawable.ic_list_white_24dp));
                    default_checkin.setText("默认入库参数-使用中");
                    checkin_layout.setVisibility(View.VISIBLE);
                } else {
                    checkin_button.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                    default_checkin.setText("默认入库参数-禁止");
                    checkin_layout.setVisibility(View.GONE);
                }
                break;
            case R.id.checkout_button:
                View checkout_view = LayoutInflater.from(this).inflate(R.layout.activity_device,null);
                if (checkout_layout.getVisibility() == View.GONE) {
                    DrawableColor.changeColor(checkout_view,R.id.checkout_button,R.drawable.ic_radio_button_checked_black_24dp,R.color.checkout);
                    DrawableColor.changeColor(checkout_view,R.id.checkout_not,R.drawable.ic_not_interested_black_24dp,R.color.checkout);
                    DrawableColor.changeColor(checkout_view,R.id.checkout_list,R.drawable.ic_list_white_24dp,R.color.checkout);
                    checkout_button.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_checked_black_24dp));
                    checkout_not.setBackground(getResources().getDrawable(R.drawable.ic_not_interested_black_24dp));
                    checkout_list.setBackground(getResources().getDrawable(R.drawable.ic_list_white_24dp));
                    default_checkout.setText("默认出库参数-使用中");
                    checkout_layout.setVisibility(View.VISIBLE);
                } else {
                    checkout_button.setBackground(getResources().getDrawable(R.drawable.ic_radio_button_unchecked_black_24dp));
                    default_checkout.setText("默认出库参数-禁止");
                    checkout_layout.setVisibility(View.GONE);
                }
                break;
            case R.id.checkin_not:
                break;
            case R.id.checkin_list:
                Intent select_supplier = new Intent(this, Select_supplier.class);
                startActivity(select_supplier);
                break;
            case R.id.checkout_not:
                break;
            case R.id.checkout_list:
                Intent select_buyer = new Intent(this, Select_buyer.class);
                startActivity(select_buyer);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.device_ensure:
                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:break;
        }
    }
}
