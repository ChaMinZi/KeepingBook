package com.example.chacha.keepingbook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class ButtonAdapter extends BaseAdapter {
    private DBHelper dbHelper;
    private Context context;
    private String[] name;

    public ButtonAdapter(Context mContext) {
        context = mContext;
        if (dbHelper == null) {
            dbHelper = new DBHelper(context, null);//mainActicity를 넘겨줘야 함
        }
    }

    @Override
    public int getCount() {
        return (int)dbHelper.getCountProduct();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void setName(String[] name){
        this.name = name;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button button;

        if (view == null) {
            button = new Button(context);
            //button.setLayoutParams(new GridView.LayoutParams,55));
            //button.setPadding(8,8,8,8);
        }
        else {
            button = (Button)view;
        }

        button.setText(name[i]);
        //button.setTextColor(Color.RED);
        button.setId(i);
        return button;
    }
}
