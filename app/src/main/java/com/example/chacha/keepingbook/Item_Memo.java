package com.example.chacha.keepingbook;

public class Item_Memo {
    private int _id;
    private String _time;
    private String _day;
    private String phone;
    private String memo;

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

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_time(String phone) {
        this._time = _time;
    }

    public void set_day(String memo) {
        this._day = _day;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
