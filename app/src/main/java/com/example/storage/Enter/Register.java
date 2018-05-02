package com.example.storage.Enter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.storage.Bean.User;
import com.example.storage.R;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText account;
    private EditText password;
    private EditText ensure_password;
    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        ensure_password = (EditText) findViewById(R.id.ensure_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if (TextUtils.isEmpty(account.getText())) {
                    Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().length() != 6) {
                    Toast.makeText(this,"请输入6位密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ensure_password.getText())) {
                    Toast.makeText(this,"请确认密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.getText().toString().equals(ensure_password.getText().toString())) {
                    Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }

                list = DataSupport.where("account = ?",account.getText().toString()).find(User.class);
                if (list.size() != 0) {
                    for (User user : list) {
                        if (user.getAccount().equals(account.getText().toString())) {
                            Toast.makeText(this, "该账号已存在", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                User user = new User();
                user.setAccount(account.getText().toString());
                user.setPassword(password.getText().toString());
                user.save();
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(this,Login.class));
                break;
            case R.id.back:
                finish();
                break;
        }
    }

}
