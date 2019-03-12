package com.example.chacha.keepingbook;

public class Item_Order {
    private int count;
    private String context;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public String getContext() {
        return context;
    }

    public int getCount() {
        return count;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
