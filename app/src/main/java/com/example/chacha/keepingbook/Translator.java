package com.example.chacha.keepingbook;

import android.util.Log;

import java.io.UnsupportedEncodingException;

public class Translator {
    public byte[] Encode(String str) {
        byte[] aByte = str.getBytes();
        for(int i = 0; i< str.length(); i++){
            Log.e("String"," " + str.charAt(i));
        }
        return aByte;
    }

    public String Decode(byte[] aByte) {
        try {
            String str = new String(aByte, "UTF-8");
            for(int i = 0; i< str.length(); i++){
                Log.e("aByte", " " + str.charAt(i));
            }
            return str;
        } catch (
                UnsupportedEncodingException e) {
                e.printStackTrace();
        }
        return null;
    }
}
