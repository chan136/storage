package com.example.storage.Bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Jerry on 2017/12/5.
 */
public class NewCheckout extends DataSupport implements Serializable {

    private String device_SKU;
    private String device_name;
    private String price;
    private String count;
    private String describe;
    private String buyer_SKU;
    private String buyer_name;
    private String date;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_SKU() {
        return device_SKU;
    }

    public void setDevice_SKU(String device_SKU) {
        this.device_SKU = device_SKU;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_SKU() {
        return buyer_SKU;
    }

    public void setBuyer_SKU(String buyer_SKU) {
        this.buyer_SKU = buyer_SKU;
    }
}
