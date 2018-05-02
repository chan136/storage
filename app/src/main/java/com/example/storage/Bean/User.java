package com.example.storage.Bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Jerry on 2018/1/5.
 */

public class User extends DataSupport implements Serializable {

    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
