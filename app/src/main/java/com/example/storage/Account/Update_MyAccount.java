package com.example.storage.Account;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storage.Bean.MyAccount;
import com.example.storage.R;
import com.example.storage.Supplier.Supplier_photo;
import com.example.storage.Utils.DrawableColor;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class Update_MyAccount extends AppCompatActivity implements View.OnClickListener{

    private boolean isClick;
    private EditText SKU;
    private EditText name;
    private EditText address;
    private EditText tel;
    private EditText phone;
    private EditText email;
    private EditText weixin;
    private EditText qq;
    private EditText describe;
    private EditText webpage;
    private MyAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_update__my_account,null);
        DrawableColor.changeColor(view,R.id.image_photo,R.drawable.ic_image_24dp,R.color.colorPrimary);
        DrawableColor.changeColor(view,R.id.person,R.drawable.person_256dp,R.color.colorPrimary);

        setContentView(R.layout.activity_update__my_account);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.textColorPrimary));
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SKU = (EditText)findViewById(R.id.supplier_SKU);
        SKU.setSelection(SKU.getText().length());
        name = (EditText)findViewById(R.id.supplier_name);
        address = (EditText)findViewById(R.id.address);
        tel = (EditText)findViewById(R.id.tel);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        weixin = (EditText)findViewById(R.id.weixin);
        qq = (EditText)findViewById(R.id.qq);
        describe = (EditText)findViewById(R.id.describe);
        webpage = (EditText)findViewById(R.id.webpage);

        List<MyAccount> accountList = DataSupport.findAll(MyAccount.class);
        for (MyAccount account : accountList) {
            if (account == null) {
                SKU.setText("我的序列号");
                SKU.setText("我的账号");
            } else {
                SKU.setText(account.getSKU());
                name.setText(account.getName());
                address.setText(account.getAddress());
                tel.setText(account.getTel());
                phone.setText(account.getPhone());
                email.setText(account.getEmail());
                weixin.setText(account.getWeixin());
                qq.setText(account.getQq());
                describe.setText(account.getDescribe());
                webpage.setText(account.getWebpage());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplier_SKU:
            case R.id.supplier_name:
            case R.id.address:
            case R.id.tel:
            case R.id.phone:
            case R.id.email:
            case R.id.weixin:
            case R.id.qq:
            case R.id.describe:
            case R.id.webpage:
                invalidateOptionsMenu();
                isClick = true;
                break;
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
                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }
    }

    private void ensure() {
        if (TextUtils.isEmpty(SKU.getText()) || TextUtils.isEmpty(name.getText())) {
            Toast.makeText(this,"SKU或名称不能为空",Toast.LENGTH_SHORT).show();
        } else {
            Connector.getDatabase();
            account = new MyAccount();
            account.setSKU(SKU.getText().toString());
            account.setName(name.getText().toString());
            account.setAddress(address.getText().toString());
            account.setTel(tel.getText().toString());
            account.setPhone(phone.getText().toString());
            account.setEmail(email.getText().toString());
            account.setWeixin(weixin.getText().toString());
            account.setQq(qq.getText().toString());
            account.setDescribe(describe.getText().toString());
            account.setWebpage(webpage.getText().toString());
            account.save();
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Account.class);
            intent.putExtra("account",account);
            setResult(1,intent);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_update,menu);
        getMenuInflater().inflate(R.menu.check_gray, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check:
                ensure();
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isClick) {
            menu.findItem(R.id.check).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
