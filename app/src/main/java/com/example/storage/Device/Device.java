package com.example.storage.Device;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.NewDevice;
import com.example.storage.Supplier.Select_supplier;
import com.example.storage.Buyer.Select_buyer;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.EditTextPic;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.tablemanager.Connector;

import java.util.Date;


public class Device extends AppCompatActivity implements View.OnClickListener {

    private boolean isClick;
    private RadioButton[] btns;
    private String[] str = {"产品","原料","设备","服务","零售"};
    private int[] layout = {R.id.a,R.id.b,R.id.c,R.id.d,R.id.e,R.id.f,R.id.m,R.id.i,R.id.j,R.id.k,R.id.l,R.id.h,R.id.g,R.id.o,R.id.p,R.id.n};
    private String[] colorName = {"未确定","红","澄","粉红","深蓝","蓝","青","绿","黄","紫","棕","黑","灰","银","白","透明"};
    private int[] colorImage = {R.drawable.z0,R.drawable.z1,R.drawable.z2,R.drawable.z3,R.drawable.z4,R.drawable.z5,R.drawable.z6,R.drawable.z7,R.drawable.z8,R.drawable.z9,R.drawable.z10,R.drawable.z11,R.drawable.z12,R.drawable.z13,R.drawable.z14,R.drawable.z15};
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

    private EditText SKU;
    private EditText name;
    private EditText brand;
    private EditText type;
    private EditText size;
    private EditText color;
    private EditText specification;
    private EditText describe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_device,null);

        DrawableColor.changeColor(view,R.id.checkin_button,R.drawable.ic_radio_button_unchecked_black_24dp,R.color.textColorPrimary);
        DrawableColor.changeColor(view,R.id.device,R.drawable.ic_devices_other_black_36dp,R.color.light_orange);
        DrawableColor.changeColor(view,R.id.image_photo,R.drawable.ic_image_24dp,R.color.select_device);

        Drawable icon = view.getResources().getDrawable(R.drawable.ic_check_white_24dp);
        Drawable tintIcon = DrawableCompat.wrap(icon);
        DrawableCompat.setTintList(tintIcon, view.getResources().getColorStateList(R.color.textColorPrimary));

        setContentView(R.layout.activity_device);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.select_device));
        toolbar.setTitleTextColor(getResources().getColor(R.color.textColorPrimary));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.select_device));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btns = new RadioButton[5];
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

        SKU = (EditText)findViewById(R.id.supplier_SKU);
        name = (EditText)findViewById(R.id.supplier_name);
        brand = (EditText)findViewById(R.id.brand);
        type = (EditText)findViewById(R.id.type);
        size = (EditText)findViewById(R.id.size);
        color = (EditText)findViewById(R.id.color);
        specification = (EditText)findViewById(R.id.specification);
        describe = (EditText)findViewById(R.id.describe);

/*        type.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Device.this);
                    View view = LayoutInflater.from(Device.this).inflate(R.layout.type_select,null);
                    builder.setView(view);
                    builder.create();
                    final AlertDialog dialog = builder.show();
                    btns[0]=(RadioButton)view.findViewById(R.id.product);
                    btns[1]=(RadioButton)view.findViewById(R.id.material);
                    btns[2]=(RadioButton)view.findViewById(R.id.device);
                    btns[3]=(RadioButton)view.findViewById(R.id.service);
                    btns[4]=(RadioButton)view.findViewById(R.id.retail);
                    TextView cancel = (TextView)view.findViewById(R.id.type_cencel);
                    TextView ensure = (TextView)view.findViewById(R.id.type_ensure);
                    type = (EditText)findViewById(R.id.type);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    ensure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0;i < btns.length;i ++) {
                                if (btns[i].isChecked()) {
                                    type.setText(str[i]);
                                    type.setSelection(str[i].length());
                                    dialog.dismiss();
                                }
                            }
                        }
                    });
                }
            }
        });*/

        color.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(Device.this);
                    final View view2 = LayoutInflater.from(Device.this).inflate(R.layout.color_select,null);
                    builder2.setView(view2);
                    builder2.create();
                    final AlertDialog dialog2 = builder2.show();
                    color = (EditText)findViewById(R.id.color);
                    TextView et = (TextView)view2.findViewById(R.id.color_cancel);
                    et.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });
                    for (int i = 0;i <layout.length;i ++) {
                        final String name = colorName[i];
                        final int id = colorImage[i];
                        view2.findViewById(layout[i]).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditTextPic.setDrawable(view2,color,id,name);
                                dialog2.dismiss();
                            }
                        });
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplier_SKU:
            case R.id.supplier_name:
            case R.id.brand:
            case R.id.size:
            case R.id.specification:
            case R.id.describe:
                invalidateOptionsMenu();
                isClick = true;
                break;
            case R.id.type:
                AlertDialog.Builder builder = new AlertDialog.Builder(Device.this);
                View view = LayoutInflater.from(Device.this).inflate(R.layout.type_select,null);
                builder.setView(view);
                builder.create();
                final AlertDialog dialog = builder.show();
                btns[0]=(RadioButton)view.findViewById(R.id.product);
                btns[1]=(RadioButton)view.findViewById(R.id.material);
                btns[2]=(RadioButton)view.findViewById(R.id.device);
                btns[3]=(RadioButton)view.findViewById(R.id.service);
                btns[4]=(RadioButton)view.findViewById(R.id.retail);
                TextView cancel = (TextView)view.findViewById(R.id.type_cencel);
                TextView ensure = (TextView)view.findViewById(R.id.type_ensure);
                type = (EditText)findViewById(R.id.type);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0;i < btns.length;i ++) {
                            if (btns[i].isChecked()) {
                                type.setText(str[i]);
                                type.setSelection(str[i].length());
                                dialog.dismiss();
                            }
                        }
                    }
                });
                break;
            case R.id.color:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(Device.this);
                final View view2 = LayoutInflater.from(Device.this).inflate(R.layout.color_select,null);
                builder2.setView(view2);
                builder2.create();
                final AlertDialog dialog2 = builder2.show();
                color = (EditText)findViewById(R.id.color);
                TextView et = (TextView)view2.findViewById(R.id.color_cancel);
                et.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                for (int i = 0;i <layout.length;i ++) {
                    final String name = colorName[i];
                    final int id = colorImage[i];
                    view2.findViewById(layout[i]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditTextPic.setDrawable(view2,color,id,name);
                            dialog2.dismiss();
                        }
                    });
                }
                break;
            case R.id.image_photo:
                Intent Device_photo = new Intent(this,Device_photo.class);
                startActivity(Device_photo);
                break;
            case R.id.device_background:
            case R.id.device:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                View view1 = LayoutInflater.from(this).inflate(R.layout.supplier_person, null);
                DrawableColor.changeColor(view1,R.id.btn1,R.drawable.ic_photo_camera_black_24dp,R.color.gray);
                DrawableColor.changeColor(view1,R.id.btn2,R.drawable.ic_folder_open_black_24dp,R.color.gray);
                DrawableColor.changeColor(view1,R.id.btn3,R.drawable.ic_not_interested_black_24dp,R.color.gray);
                builder1.setView(view1);
                builder1.create();
                builder1.show();
                break;
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
            case R.id.device_ensure:
                ensure();
                break;
            default:break;
        }
    }

    private void ensure() {
        if (SKU.getText().toString().trim().equals("")||name.getText().toString().trim().equals("")) {
            Toast.makeText(this,"SKU或名称不能为空",Toast.LENGTH_SHORT).show();
        } else {
            Connector.getDatabase();
            NewDevice device = new NewDevice();
            device.setSKU(SKU.getText().toString());
            device.setName(name.getText().toString());
            device.setBrand(brand.getText().toString());
            device.setType(type.getText().toString());
            if (type.getText().toString().length() == 0) {
                device.setType("产品");
            }
            device.setSize(size.getText().toString());
            device.setColor(color.getText().toString());
            device.setSpecification(specification.getText().toString());
            device.setDescribe(describe.getText().toString());
            device.setDate(new Date());
            device.save();
            SKU.setText("");
            name.setText("");
            brand.setText("");
            type.setText("");
            size.setText("");
            color.setText("");
            specification.setText("");
            describe.setText("");
            Toast.makeText(this,"创建成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check:
                ensure();
                break;
            default:break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_menu,menu);
        getMenuInflater().inflate(R.menu.check, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isClick) {
            menu.findItem(R.id.check).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }
}
