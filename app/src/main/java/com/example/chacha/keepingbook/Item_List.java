package com.example.chacha.keepingbook;

public class Item_List {
    private String product;
    private int count;

    public Item_List(String product, int count){
        this.product = product;
        this.count = count;
    }

    public String getProduct() {
        return product;
    }

    public int getCount() {
        return count;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
