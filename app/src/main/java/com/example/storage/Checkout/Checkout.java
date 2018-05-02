package com.example.storage.Checkout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.NewBuyer;
import com.example.storage.Bean.NewBuyerAdapter;
import com.example.storage.Bean.NewCheckout;
import com.example.storage.Bean.NewDevice;
import com.example.storage.Bean.NewDeviceAdapter;
import com.example.storage.Buyer.Buyer;
import com.example.storage.Buyer.Select_buyer;
import com.example.storage.Buyer.Verify_buyer;
import com.example.storage.Device.Select_device;
import com.example.storage.Device.Device;
import com.example.storage.Device.Verify_device;
import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.ShowTime;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Checkout extends AppCompatActivity implements View.OnClickListener {

    private TextView date;
    private boolean isClick;
    private boolean flag;

    private EditText device_SKU;
    private EditText device_name;
    private EditText price;
    private EditText count;
    private EditText describe;
    private EditText buyer_SKU;
    private EditText buyer_name;
    private ImageButton device_syn;
    private ImageButton buyer_syn;
    private ImageButton device_verify;
    private ImageButton buyer_verify;
    private TextView item_name;
    private TextView item_SKU;
    private TextView add_device;
    private TextView add_buyer;
    private TextView cancel;

    private List<NewDevice> deviceList = new ArrayList<>();
    private List<NewBuyer> buyerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_checkout,null);
        DrawableColor.changeColor(view,R.id.image,R.drawable.ic_image_24dp,R.color.checkout);
        DrawableColor.changeColor(view,R.id.device_list,R.drawable.ic_list_white_24dp,R.color.checkout);
        DrawableColor.changeColor(view,R.id.device_syn,R.drawable.ic_sync_black_24dp,R.color.checkout);
        DrawableColor.changeColor(view,R.id.device_verify,R.drawable.ic_verified_24dp,R.color.checkout);

        Drawable icon = view.getResources().getDrawable(R.drawable.ic_check_white_24dp);
        Drawable tintIcon = DrawableCompat.wrap(icon);
        DrawableCompat.setTintList(tintIcon, view.getResources().getColorStateList(R.color.textColorPrimary));

        setContentView(R.layout.activity_checkout);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.checkout));
        StatusBarUtils.setStatusBar(this,getResources().getColor(R.color.checkout));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        device_SKU = (EditText)findViewById(R.id.supplier_SKU);
        device_name = (EditText)findViewById(R.id.supplier_name);
        price = (EditText)findViewById(R.id.supplier_count);
        count = (EditText)findViewById(R.id.change);
        describe = (EditText)findViewById(R.id.errorDescribe);
        buyer_SKU = (EditText)findViewById(R.id.buyer_SKU);
        buyer_name = (EditText)findViewById(R.id.buyer_name);
        device_syn = (ImageButton)findViewById(R.id.device_syn);
        buyer_syn = (ImageButton)findViewById(R.id.buyer_syn);
        device_verify = (ImageButton)findViewById(R.id.device_verify);
        buyer_verify = (ImageButton)findViewById(R.id.buyer_verify);

        Intent intent = getIntent();
        device_SKU.setText(intent.getStringExtra("SKU"));
        device_name.setText(intent.getStringExtra("name"));
        buyer_SKU.setText(intent.getStringExtra("buyer_SKU"));
        buyer_name.setText(intent.getStringExtra("buyer_name"));

        if (intent.getStringExtra("SKU") != null && intent.getStringExtra("name") != null) {
            device_syn.setVisibility(View.GONE);
            device_verify.setVisibility(View.VISIBLE);
        }
        if (intent.getStringExtra("buyer_SKU") != null && intent.getStringExtra("buyer_name") != null) {
            buyer_syn.setVisibility(View.GONE);
            buyer_verify.setVisibility(View.VISIBLE);
        }

        date=(TextView) findViewById(R.id.date);
        ShowTime.show(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:
                device_SKU.setText(data.getExtras().getString("SKU"));
                device_name.setText(data.getExtras().getString("name"));
                device_syn.setVisibility(View.GONE);
                device_verify.setVisibility(View.VISIBLE);
                break;
            case 2:
                buyer_SKU.setText(data.getExtras().getString("buyer_SKU"));
                buyer_name.setText(data.getExtras().getString("buyer_name"));
                buyer_syn.setVisibility(View.GONE);
                buyer_verify.setVisibility(View.VISIBLE);
                break;
            default:break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ensure() {
        if (device_SKU.getText().toString().trim().equals("")||device_name.getText().toString().trim().equals("")) {
            Toast.makeText(this,"货品不能为空",Toast.LENGTH_SHORT).show();
        } else {
            for (NewDevice device : deviceList) {
                if (device.getSKU().equals(device_SKU.getText().toString())) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                if (price.getText().toString().trim().equals("")||count.getText().toString().trim().equals("")) {
                    Toast.makeText(this,"价格或数量不能为空",Toast.LENGTH_SHORT).show();
                } else {
                    Connector.getDatabase();
                    NewCheckout checkout = new NewCheckout();
                    checkout.setDevice_SKU(device_SKU.getText().toString());
                    checkout.setDevice_name(device_name.getText().toString());
                    checkout.setPrice(price.getText().toString());
                    checkout.setCount(count.getText().toString());
                    checkout.setDescribe(describe.getText().toString());
                    checkout.setBuyer_SKU(buyer_SKU.getText().toString());
                    checkout.setBuyer_name(buyer_name.getText().toString());
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("MM月 dd HH:mm");
                    String str = sdf.format(date);
                    checkout.setDate(str);
                    checkout.save();
                    device_syn.setVisibility(View.VISIBLE);
                    device_verify.setVisibility(View.GONE);
                    buyer_syn.setVisibility(View.VISIBLE);
                    buyer_verify.setVisibility(View.GONE);
                    device_SKU.setText("");
                    device_name.setText("");
                    price.setText("");
                    count.setText("");
                    describe.setText("");
                    buyer_SKU.setText("");
                    buyer_name.setText("");
                    Toast.makeText(this,"创建成功",Toast.LENGTH_SHORT).show();
                }
            } else {
                createDeviceDialog();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.supplier_SKU:
            case R.id.supplier_name:
            case R.id.supplier_count:
            case R.id.change:
            case R.id.errorDescribe:
            case R.id.buyer_SKU:
            case R.id.buyer_name:
                invalidateOptionsMenu();
                isClick = true;
                break;
            case R.id.device_list:
                Intent select_device = new Intent(this,Select_device.class);
                startActivityForResult(select_device,1);
                break;
            case R.id.buyer_list:
                Intent intent1 = new Intent(this,Select_buyer.class);
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
            case R.id.buyer_verify:
                Intent verify2 = new Intent(this, Verify_buyer.class);
                buyerList = DataSupport.where("SKU = ?",buyer_SKU.getText().toString()).find(NewBuyer.class);
                for (NewBuyer buyer:buyerList) {
                    verify2.putExtra("buyer",buyer);
                }
                startActivity(verify2);
                break;
            case R.id.device_syn:
                if (!TextUtils.isEmpty(device_SKU.getText())) {
                    deviceList = DataSupport.where("SKU = ?",device_SKU.getText().toString()).find(NewDevice.class);
                    if (deviceList.size() != 0) {
                        for (NewDevice device:deviceList) {
                            device_name.setText(device.getName());
                        }
                        device_syn.setVisibility(View.GONE);
                        device_verify.setVisibility(View.VISIBLE);
                    } else {
                        createDeviceDialog();
                    }
                }else if(!TextUtils.isEmpty(device_name.getText())) {
                    deviceList = DataSupport.where("name = ?",device_name.getText().toString()).find(NewDevice.class);
                    if (deviceList.size() != 0) {
                        for (NewDevice device:deviceList) {
                            device_SKU.setText(device.getSKU());
                        }
                        device_syn.setVisibility(View.GONE);
                        device_verify.setVisibility(View.VISIBLE);
                    } else {
                        createDeviceDialog();
                    }
                }
                break;
            case R.id.buyer_syn:
                if (!TextUtils.isEmpty(buyer_SKU.getText())) {
                    buyerList = DataSupport.where("SKU = ?",buyer_SKU.getText().toString()).find(NewBuyer.class);
                    if (buyerList.size() != 0) {
                        for (NewBuyer buyer:buyerList) {
                            buyer_name.setText(buyer.getName());
                        }
                        buyer_syn.setVisibility(View.GONE);
                        buyer_verify.setVisibility(View.VISIBLE);
                    } else {
                        createBuyerDialog();
                    }
                }else if(!TextUtils.isEmpty(buyer_name.getText())) {
                    buyerList = DataSupport.where("name = ?",buyer_name.getText().toString()).find(NewBuyer.class);
                    if (buyerList.size() != 0) {
                        for (NewBuyer buyer:buyerList) {
                            buyer_SKU.setText(buyer.getSKU());
                        }
                        buyer_syn.setVisibility(View.GONE);
                        buyer_verify.setVisibility(View.VISIBLE);
                    } else {
                        createBuyerDialog();
                    }
                }
                break;
            case R.id.image:
                Intent checkin_photo = new Intent(this,Checkout_photo.class);
                startActivity(checkin_photo);
                break;
            case R.id.ensure:
                ensure();
                break;
            default:break;
        }
    }

    private void createDeviceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.add_device_similar, null);

        add_device = (TextView)view.findViewById(R.id.add_device);
        cancel = (TextView)view.findViewById(R.id.cancel);

        builder.setView(view);
        builder.create();
        final AlertDialog dialog = builder.show();

        add_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Checkout.this,Device.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        View item_view = getLayoutInflater().inflate( R.layout.add_similar_item, null );
        item_name = (TextView)item_view.findViewById(R.id.name);
        item_SKU = (TextView)item_view.findViewById(R.id.SKU);
        device_init();
        NewDeviceAdapter adapter = new NewDeviceAdapter(this,R.layout.add_similar_item,deviceList);
        ListView listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewDevice device = deviceList.get(position);
                device_SKU.setText(device.getSKU());
                device_name.setText(device.getName());
                device_syn.setVisibility(View.GONE);
                device_verify.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
    }

    private void createBuyerDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.add_buyer_similar, null);

        add_buyer = (TextView)view.findViewById(R.id.add_supplier);
        cancel = (TextView)view.findViewById(R.id.cancel);

        builder.setView(view);
        builder.create();
        final AlertDialog dialog = builder.show();

        add_buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Checkout.this,Buyer.class));
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        View item_view = getLayoutInflater().inflate( R.layout.add_similar_item, null );
        item_name = (TextView)item_view.findViewById(R.id.name);
        item_SKU = (TextView)item_view.findViewById(R.id.SKU);
        buyer_init();
        NewBuyerAdapter adapter = new NewBuyerAdapter(this,R.layout.add_similar_item,buyerList);
        ListView listView = (ListView)view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewBuyer buyer = buyerList.get(position);
                buyer_SKU.setText(buyer.getSKU());
                buyer_name.setText(buyer.getName());
                buyer_syn.setVisibility(View.GONE);
                buyer_verify.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
    }

    private void device_init() {
        List<NewDevice> devices = DataSupport.findAll(NewDevice.class);
        deviceList.clear();
        for (NewDevice device:devices) {
            deviceList.add(device);
        }
    }

    private void buyer_init() {
        List<NewBuyer> buyers = DataSupport.findAll(NewBuyer.class);
        buyerList.clear();
        for (NewBuyer buyer:buyers) {
            buyerList.add(buyer);
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
        getMenuInflater().inflate(R.menu.activity_checkin, menu);
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
