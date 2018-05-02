package com.example.storage.Supplier;

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

import com.example.storage.Bean.NewSupplier;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.tablemanager.Connector;

public class Update_supplier extends AppCompatActivity implements View.OnClickListener{

    private EditText supplier_SKU;
    private EditText supplier_name;
    private EditText address;
    private EditText tel;
    private EditText phone;
    private EditText email;
    private EditText weixin;
    private EditText qq;
    private EditText describe;
    private EditText webpage;
    private NewSupplier suppliers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_update_supplier,null);
        DrawableColor.changeColor(view,R.id.person,R.drawable.person_256dp,R.color.supplier);
        DrawableColor.changeColor(view,R.id.image_photo,R.drawable.ic_image_24dp,R.color.checkin);
        setContentView(R.layout.activity_update_supplier);

        supplier_SKU = (EditText)findViewById(R.id.supplier_SKU);
        supplier_name = (EditText)findViewById(R.id.supplier_name);
        address = (EditText)findViewById(R.id.address);
        tel = (EditText)findViewById(R.id.tel);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        weixin = (EditText)findViewById(R.id.weixin);
        qq = (EditText)findViewById(R.id.qq);
        describe = (EditText)findViewById(R.id.describe);
        webpage = (EditText)findViewById(R.id.webpage);

        suppliers = (NewSupplier)getIntent().getSerializableExtra("supplier");
        supplier_SKU.setText(suppliers.getSKU());
        supplier_name.setText(suppliers.getName());
        address.setText(suppliers.getAddress());
        tel.setText(suppliers.getTel());
        phone.setText(suppliers.getPhone());
        email.setText(suppliers.getEmail());
        weixin.setText(suppliers.getWeixin());
        qq.setText(suppliers.getQq());
        describe.setText(suppliers.getDescribe());
        webpage.setText(suppliers.getWebpage());

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.supplier));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.checkin));
        toolbar.setTitleTextColor(getResources().getColor(R.color.checkin));
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.checkin), PorterDuff.Mode.SRC_ATOP);
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
                Intent addphoto = new Intent(this,Supplier_photo.class);
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
                startActivity(new Intent(this, Select_supplier.class));
                break;
            default:break;
        }
    }

    private void ensure() {
        if (supplier_SKU.getText().toString().trim().equals("")||supplier_name.getText().toString().trim().equals("")) {
            Toast.makeText(this,"SKU或名称不能为空",Toast.LENGTH_SHORT).show();
        } else {
            Connector.getDatabase();
            NewSupplier supplier = new NewSupplier();
            supplier.setSKU(supplier_SKU.getText().toString());
            supplier.setName(supplier_name.getText().toString());
            supplier.setAddress(address.getText().toString());
            supplier.setTel(tel.getText().toString());
            supplier.setPhone(phone.getText().toString());
            supplier.setEmail(email.getText().toString());
            supplier.setWeixin(weixin.getText().toString());
            supplier.setQq(qq.getText().toString());
            supplier.setDescribe(describe.getText().toString());
            supplier.setWebpage(webpage.getText().toString());
            supplier.updateAll("SKU = ?",suppliers.getSKU());
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
