package com.example.storage.Account;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.MyAccount;
import com.example.storage.Bean.Photo;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Account extends AppCompatActivity implements View.OnClickListener{

    private TextView head;
    private TextView SKU;
    private TextView name;
    private EditText address;
    private EditText tel;
    private EditText phone;
    private EditText email;
    private EditText weixin;
    private EditText qq;
    private EditText describe;
    private EditText webpage;

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView picture;
    private ImageView person;
    private Uri imageUri;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_account,null);
        DrawableColor.changeColor(view,R.id.image_photo,R.drawable.ic_image_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.person,R.drawable.person_256dp,R.color.textColorPrimary);

        setContentView(R.layout.activity_account);

        head = (TextView) findViewById(R.id.head);
        picture = (ImageView)findViewById(R.id.person_background);
        person = (ImageView)findViewById(R.id.person);
        SKU = (TextView) findViewById(R.id.supplier_SKU);
        name = (TextView)findViewById(R.id.supplier_name);
        address = (EditText)findViewById(R.id.address);
        tel = (EditText)findViewById(R.id.tel);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        weixin = (EditText)findViewById(R.id.weixin);
        qq = (EditText)findViewById(R.id.qq);
        describe = (EditText)findViewById(R.id.describe);
        webpage = (EditText)findViewById(R.id.webpage);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showAccount();
    }

    private void showAccount() {
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
        switch (v.getId())  {
            case R.id.image_photo:
                Intent addphoto = new Intent(this,Account_photo.class);
                startActivity(addphoto);
                break;
            case R.id.person_background:
            case R.id.person:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.supplier_person, null);
                DrawableColor.changeColor(view,R.id.btn1,R.drawable.ic_photo_camera_black_24dp,R.color.gray);
                DrawableColor.changeColor(view,R.id.btn2,R.drawable.ic_folder_open_black_24dp,R.color.gray);
                DrawableColor.changeColor(view,R.id.btn3,R.drawable.ic_not_interested_black_24dp,R.color.gray);
                RelativeLayout take_photo = (RelativeLayout)view.findViewById(R.id.one);
                RelativeLayout open_album = (RelativeLayout)view.findViewById(R.id.two);
                final RelativeLayout none = (RelativeLayout)view.findViewById(R.id.three);
                builder.setView(view);
                builder.create();
                final AlertDialog dialog = builder.show();

                take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, TAKE_PHOTO);
                        }
                        dialog.dismiss();
                    }
                });
                open_album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, CHOOSE_PHOTO);
                        dialog.dismiss();
                    }
                });
                none.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        picture.setImageDrawable(null);
                        person.setVisibility(View.VISIBLE);
                        head.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }
                });
                break;
            default:break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    if(extras != null){
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        picture.setImageBitmap(imageBitmap);
                        person.setVisibility(View.GONE);
                        head.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(this,"gg",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumns = {MediaStore.Images.Media.DATA};
                    Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePathColumns[0]);
                    imagePath = c.getString(columnIndex);
                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                    picture.setImageBitmap(bitmap);
                    person.setVisibility(View.GONE);
                    head.setVisibility(View.GONE);
                    c.close();
                }
                break;
            default:break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.keep_log,menu);
        getMenuInflater().inflate(R.menu.update,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update:
                Intent update = new Intent(this,Update_MyAccount.class);
                startActivityForResult(update,1);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
