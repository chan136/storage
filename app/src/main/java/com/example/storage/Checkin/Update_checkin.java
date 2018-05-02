package com.example.storage.Checkin;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.NewCheckin;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Bean.NewSupplier;
import com.example.storage.Device.Select_device;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Search.Result_checkin;
import com.example.storage.Supplier.Select_supplier;
import com.example.storage.Supplier.Verify_supplier;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.ShowTime;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Update_checkin extends AppCompatActivity implements View.OnClickListener{

    private boolean isClick;

    private EditText device_SKU;
    private EditText device_name;
    private EditText price;
    private EditText count;
    private EditText describe;
    private EditText supplier_SKU;
    private EditText supplier_name;
    private TextView date;
    private NewCheckin checkins;

    private List<NewDevice> deviceList = new ArrayList<>();
    private List<NewSupplier> supplierList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_update_checkin,null);
        DrawableColor.changeColor(view,R.id.device_list,R.drawable.ic_list_white_24dp,R.color.checkin);
        DrawableColor.changeColor(view,R.id.image,R.drawable.ic_image_24dp,R.color.checkin);
        DrawableColor.changeColor(view,R.id.device_verify,R.drawable.ic_verified_24dp,R.color.checkin);

        Drawable icon = view.getResources().getDrawable(R.drawable.ic_check_white_24dp);
        Drawable tintIcon = DrawableCompat.wrap(icon);
        DrawableCompat.setTintList(tintIcon, view.getResources().getColorStateList(R.color.checkin));

        setContentView(R.layout.activity_update_checkin);

        device_SKU = (EditText)findViewById(R.id.supplier_SKU);
        device_name = (EditText)findViewById(R.id.supplier_name);
        price = (EditText)findViewById(R.id.supplier_count);
        count = (EditText)findViewById(R.id.change);
        describe = (EditText)findViewById(R.id.errorDescribe);
        supplier_SKU = (EditText)findViewById(R.id.buyer_SKU);
        supplier_name = (EditText)findViewById(R.id.buyer_name);
        date = (TextView)findViewById(R.id.date);

        checkins = (NewCheckin)getIntent().getSerializableExtra("checkin");
        device_SKU.setText(checkins.getDevice_SKU());
        device_name.setText(checkins.getDevice_name());
        price.setText(checkins.getPrice());
        count.setText(checkins.getCount());
        describe.setText(checkins.getDescribe());
        supplier_SKU.setText(checkins.getSupplier_SKU());
        supplier_name.setText(checkins.getSupplier_name());

        ShowTime.show(date);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.supplier));
        toolbar.setTitleTextColor(getResources().getColor(R.color.checkin));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.checkin));
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

    private void ensure() {
        if (device_SKU.getText().toString().trim().equals("")||device_name.getText().toString().trim().equals("")) {
            Toast.makeText(this,"货品不能为空",Toast.LENGTH_SHORT).show();
        } else {
            if (price.getText().toString().trim().equals("")||count.getText().toString().trim().equals("")) {
                Toast.makeText(this,"价格或数量不能为空",Toast.LENGTH_SHORT).show();
            } else {
                Connector.getDatabase();
                NewCheckin checkin = new NewCheckin();
                checkin.setDevice_SKU(device_SKU.getText().toString());
                checkin.setDevice_name(device_name.getText().toString());
                checkin.setPrice(price.getText().toString());
                checkin.setBeforeCount(count.getText().toString());
                checkin.setCount(count.getText().toString());
                checkin.setDescribe(describe.getText().toString());
                checkin.setSupplier_SKU(supplier_SKU.getText().toString());
                checkin.setSupplier_name(supplier_name.getText().toString());
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("MM月 dd HH:mm");
                String str = sdf.format(date);
                checkin.setDate(str);
                checkin.updateAll("device_SKU = ?",checkins.getDevice_SKU());
                Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                device_SKU.setText(data.getExtras().getString("SKU"));
                device_name.setText(data.getExtras().getString("name"));
                break;
            case 2:
                supplier_SKU.setText(data.getExtras().getString("supplier_SKU"));
                supplier_name.setText(data.getExtras().getString("supplier_name"));
                break;
            default:break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.device_list:
                Intent select_device = new Intent(this,Select_device.class);
                startActivityForResult(select_device,1);
                break;
            case R.id.supplier_list:
                Intent intent1 = new Intent(this,Select_supplier.class);
                startActivityForResult(intent1,1);
                break;
            case R.id.device_verify:
                Intent verify1 = new Intent(this, Verify_device.class);
                deviceList = DataSupport.where("SKU = ?",device_SKU.getText().toString()).find(NewDevice.class);
                for (NewDevice device:deviceList) {
                    verify1.putExtra("device",device);
                }
                startActivity(verify1);
                break;
            case R.id.supplier_verify:
                Intent verify2 = new Intent(this, Verify_supplier.class);
                supplierList = DataSupport.where("SKU = ?",supplier_SKU.getText().toString()).find(NewSupplier.class);
                for (NewSupplier supplier:supplierList) {
                    verify2.putExtra("supplier",supplier);
                }
                startActivity(verify2);
                break;
            case R.id.image:
                startActivity(new Intent(this,Checkin_photo.class));
                break;
            case R.id.ensure:
                ensure();
                finish();
                startActivity(new Intent(this,Result_checkin.class));
            default:break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_update,menu);
        getMenuInflater().inflate(R.menu.check,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isClick) {
            menu.findItem(R.id.check).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check:
                ensure();
                finish();
                startActivity(new Intent(this,Result_checkin.class));
                break;
            default:break;
        }
        return true;
    }
}
