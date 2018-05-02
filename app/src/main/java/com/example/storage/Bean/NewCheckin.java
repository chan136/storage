package com.example.storage.Bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Jerry on 2017/12/5.
 */
public class NewCheckin extends DataSupport implements Serializable {

    private String device_SKU;
    private String device_name;
    private String price;
    private String count;
    private String beforeCount;
    private String change;
    private String errorDescribe;
    private String describe;
    private String supplier_SKU;
    private String supplier_name;
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

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_SKU() {
        return supplier_SKU;
    }

    public void setSupplier_SKU(String supplier_SKU) {
        this.supplier_SKU = supplier_SKU;
    }

    public String getBeforeCount() {
        return beforeCount;
    }

    public void setBeforeCount(String beforeCount) {
        this.beforeCount = beforeCount;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getErrorDescribe() {
        return errorDescribe;
    }

    public void setErrorDescribe(String errorDescribe) {
        this.errorDescribe = errorDescribe;
    }
}
