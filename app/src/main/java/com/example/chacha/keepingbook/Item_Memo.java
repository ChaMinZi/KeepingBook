package com.example.chacha.keepingbook;

public class Item_Memo {
    private int _id;
    private String _time;
    private String _day;
    private String phone;
    private String memo;
    private int pay, check;

    public int get_id() {
        return _id;
    }

    public String get_time() {return _time;}

    public String get_day() {return _day;}

    public String getPhone() {
        return phone;
    }

    public String getMemo() {
        return memo;
    }

    public int getPay() {
        return pay;
    }

    public int getCheck() {
        return check;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public void set_day(String _day) {
        this._day = _day;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
