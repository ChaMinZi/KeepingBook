package com.example.chacha.keepingbook;

public class Item_OrderList {
    private int _id;
    private String[] products;
    private int[] productCount;
    private String _time;
    private String _day;
    private String phone;
    private String memo;
    private int pay, check;

    public int get_id() {
        return _id;
    }

    public String[] getProducts() {
        return products;
    }

    public int[] getProductCount() {
        return productCount;
    }

    public String get_time() {
        return _time;
    }

    public String get_day() {
        return _day;
    }

    public String getMemo() {
        return memo;
    }

    public String getPhone() {
        return phone;
    }

    public int getCheck() {
        return check;
    }

    public int getPay() {
        return pay;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public void setProductCount(int[] productCount) {
        this.productCount = productCount;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public void set_day(String _day) {
        this._day = _day;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }
}
