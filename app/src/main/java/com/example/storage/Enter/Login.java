package com.example.storage.Enter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storage.Bean.NewDevice;
import com.example.storage.Bean.RecentUser;
import com.example.storage.Bean.User;
import com.example.storage.Main.MainActivity;
import com.example.storage.R;
import com.example.storage.Utils.StatusBarUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText account;
    private EditText password;
    private ImageView pwd_delete;
    private List<User> list;

    private PopupWindow mPopup; // 点击图片弹出popupwindow
    private ListView mPopView;//popupwindow内容
    private List<RecentUser> recentUserses;
    private BaseAdapter baseAdapter;
    private EditText etUserId;
    private ImageView more;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);

        etUserId = (EditText)findViewById(R.id.account);
        more = (ImageView) findViewById(R.id.more);
        pwd_delete = (ImageView) findViewById(R.id.pwd_delete);

        recentUserses = DataSupport.findAll(RecentUser.class);

        baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return recentUserses.size();
            }

            @Override
            public Object getItem(int position) {
                return recentUserses.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                UserList userlist = null;
                if (convertView == null) {
                    userlist = new UserList();
                    convertView = LayoutInflater.from(Login.this).inflate(R.layout.login_userlist_item, parent, false);
                    userlist.tv_userid = (TextView) convertView.findViewById(R.id.login_userlist_item_tv_userid);
                    userlist.iv_userid_delete = (ImageView) convertView.findViewById(R.id.login_userlist_item_iv_delete);

                    convertView.setTag(userlist);

                } else {
                    userlist = (UserList) convertView.getTag();
                }
                userlist.tv_userid.setText(recentUserses.get(position).getAccount());
                int width = etUserId.getWidth();
                userlist.tv_userid.setWidth(width - 30);
                userlist.tv_userid.setTextSize(24);

                userlist.iv_userid_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recentUserses.remove(position);
                        DataSupport.deleteAll(RecentUser.class,"account = ?",recentUserses.get(position).getAccount());
                        mPopup.dismiss();
                        int[] location = new int[2];
                        etUserId.getLocationOnScreen(location);
                        mPopup.showAsDropDown(findViewById(R.id.account_layout), 0, 0);

                    }
                });
                return convertView;
            }

            class UserList {
                TextView tv_userid;
                ImageView iv_userid_delete;
            }
        };
        mPopView = new ListView(this);
        mPopView.setAdapter(baseAdapter);
        mPopView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etUserId.setText(recentUserses.get(position).getAccount());
                mPopup.dismiss();
            }
        });
        mPopup = new PopupWindow(mPopView, 720, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopup.setFocusable(true); // 让popwin获取焦点
        mPopup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopup.isShowing()) {
                    mPopup.dismiss();
                    return;
                }
                mPopup.showAsDropDown(findViewById(R.id.account_layout), 0, 0);
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() == 0) {
                    pwd_delete.setVisibility(View.INVISIBLE);
                } else {
                    pwd_delete.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                if (TextUtils.isEmpty(account.getText())) {
                    Toast.makeText(this,"账号不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                list = DataSupport.where("account = ?",account.getText().toString()).find(User.class);
                if (list.size() == 0) {
                    Toast.makeText(this,"账号不存在",Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    for (User user : list) {
                        if (!user.getPassword().equals(password.getText().toString())) {
                            Toast.makeText(this,"密码错误",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                for (RecentUser recentUser : recentUserses) {
                    if (recentUser.getAccount().equals(account.getText().toString())) {
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    RecentUser recentUser = new RecentUser();
                    recentUser.setAccount(account.getText().toString());
                    recentUser.setPassword(password.getText().toString());
                    recentUser.save();
                }

                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register:
                startActivity(new Intent(this,Register.class));
                break;
            case R.id.back:
                finish();
                break;
            case R.id.pwd_delete:
                password.setText("");
                break;
            default:
                break;
        }
    }
}
