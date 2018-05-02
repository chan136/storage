package com.example.storage.Setting;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;
import com.example.storage.Utils.StatusBarUtils;

public class Setting extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_CODE_BLUETOOTH_ON = 1313;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private TextView tv;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_setting,null);
        DrawableColor.changeColor(view,R.id.bluetooth,R.drawable.ic_bluetooth_black_24dp,R.color.colorPrimary);
        DrawableColor.changeColor(view,R.id.barcode,R.drawable.ic_barcode_24dp,R.color.colorPrimary);
        DrawableColor.changeColor(view,R.id.database,R.drawable.ic_database_maintance_24dp,R.color.colorPrimary);

        setContentView(R.layout.activity_setting);

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bluetooth_layout:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                View view = LayoutInflater.from(this).inflate(R.layout.bluetooth, null);
                builder.setView(view);
                builder.create();
                final AlertDialog dialog = builder.show();

                tv = (TextView)view.findViewById(R.id.textView);
                final TextView back = (TextView)view.findViewById(R.id.back);
                button = (ImageButton)view.findViewById(R.id.button);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (tv.getText().toString().equals("关闭")) {
                            if (isBluetoothEnabled()) {
                                Toast.makeText(Setting.this,"蓝牙已开启",Toast.LENGTH_SHORT).show();

                            } else {
                                Intent requestBluetoothOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                                Setting.this.startActivityForResult(requestBluetoothOn,REQUEST_CODE_BLUETOOTH_ON);
                            }
                        } else {
                            bluetoothAdapter.disable();
                            tv.setText("关闭");
                            button.setBackground(getResources().getDrawable(R.drawable.close));
                            Toast.makeText(Setting.this,"蓝牙已关闭",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.barcode_layout:
                break;
            case R.id.database_layout:
                startActivity(new Intent(this,Database_maintain.class));
                break;
        }
    }

    private boolean isBluetoothEnabled()
    {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter != null)
        {
            return bluetoothAdapter.isEnabled();
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CODE_BLUETOOTH_ON) {
            switch (resultCode)
            {
                case Activity.RESULT_OK:
                    tv.setText("已开启");
                    button.setBackground(getResources().getDrawable(R.drawable.open));
                    break;
                case Activity.RESULT_CANCELED:
                    tv.setText("关闭");
                    button.setBackground(getResources().getDrawable(R.drawable.close));
                    break;
                default:
                    break;
            }
        }
    }

}
