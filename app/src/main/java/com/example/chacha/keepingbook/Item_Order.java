package com.example.chacha.keepingbook;

public class Item_Order {
    private int count;
    private String product;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public String getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
