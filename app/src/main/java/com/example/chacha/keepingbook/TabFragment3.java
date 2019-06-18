package com.example.chacha.keepingbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragment3 extends Fragment {
    private DBHelper dbHelper;
    private Context context;
    private int count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.tab_fragment_3, container, false);
        context = container.getContext();

        if (dbHelper == null) {
            dbHelper = new DBHelper(getActivity(), null);
        }
        return view;
    }
}
