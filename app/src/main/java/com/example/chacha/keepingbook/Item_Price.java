package com.example.chacha.keepingbook;

public class Item_Price {
    private int price;
    private byte[] name;

    public byte[] getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(byte[] encode) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
