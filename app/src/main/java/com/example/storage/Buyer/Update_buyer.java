package com.example.storage.Buyer;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storage.Bean.NewBuyer;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.tablemanager.Connector;

public class Update_buyer extends AppCompatActivity implements View.OnClickListener{

    private EditText buyer_SKU;
    private EditText buyer_name;
    private EditText address;
    private EditText tel;
    private EditText phone;
    private EditText email;
    private EditText weixin;
    private EditText qq;
    private EditText describe;
    private EditText webpage;

    private NewBuyer buyers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_update_buyer,null);
        DrawableColor.changeColor(view,R.id.person,R.drawable.person_256dp,R.color.checkout);
        DrawableColor.changeColor(view,R.id.image_photo,R.drawable.ic_image_24dp,R.color.checkout);
        setContentView(R.layout.activity_update_buyer);

        buyer_SKU = (EditText)findViewById(R.id.buyer_SKU);
        buyer_name = (EditText)findViewById(R.id.buyer_name);
        address = (EditText)findViewById(R.id.address);
        tel = (EditText)findViewById(R.id.tel);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        weixin = (EditText)findViewById(R.id.weixin);
        qq = (EditText)findViewById(R.id.qq);
        describe = (EditText)findViewById(R.id.describe);
        webpage = (EditText)findViewById(R.id.webpage);

        buyers = (NewBuyer) getIntent().getSerializableExtra("buyer");
        buyer_SKU.setText(buyers.getSKU());
        buyer_name.setText(buyers.getName());
        address.setText(buyers.getAddress());
        tel.setText(buyers.getTel());
        phone.setText(buyers.getPhone());
        email.setText(buyers.getEmail());
        weixin.setText(buyers.getWeixin());
        qq.setText(buyers.getQq());
        describe.setText(buyers.getDescribe());
        webpage.setText(buyers.getWebpage());

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.light_blue));
        toolbar.setTitleTextColor(getResources().getColor(R.color.checkout));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.checkout));
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.checkout), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

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
                Intent addphoto = new Intent(this,Buyer_photo.class);
                startActivity(addphoto);
                break;
            case R.id.person_background:
            case R.id.person:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.supplier_person, null);
                DrawableColor.changeColor(view,R.id.btn1,R.drawable.ic_photo_camera_black_24dp,R.color.gray);
                DrawableColor.changeColor(view,R.id.btn2,R.drawable.ic_folder_open_black_24dp,R.color.gray);
                DrawableColor.changeColor(view,R.id.btn3,R.drawable.ic_not_interested_black_24dp,R.color.gray);
                builder.setView(view);
                builder.create();
                builder.show();
                break;
            case R.id.ensure:
                ensure();
                finish();
                startActivity(new Intent(this, Select_buyer.class));
                break;
            default:break;
        }
    }

    private void ensure() {
        if (buyer_SKU.getText().toString().trim().equals("")||buyer_name.getText().toString().trim().equals("")) {
            Toast.makeText(this,"SKU或名称不能为空",Toast.LENGTH_SHORT).show();
        } else {
            Connector.getDatabase();
            NewBuyer buyer = new NewBuyer();
            buyer.setSKU(buyer_SKU.getText().toString());
            buyer.setName(buyer_name.getText().toString());
            buyer.setAddress(address.getText().toString());
            buyer.setTel(tel.getText().toString());
            buyer.setPhone(phone.getText().toString());
            buyer.setEmail(email.getText().toString());
            buyer.setWeixin(weixin.getText().toString());
            buyer.setQq(qq.getText().toString());
            buyer.setDescribe(describe.getText().toString());
            buyer.setWebpage(webpage.getText().toString());
            buyer.updateAll("SKU = ?",buyer.getSKU());
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
