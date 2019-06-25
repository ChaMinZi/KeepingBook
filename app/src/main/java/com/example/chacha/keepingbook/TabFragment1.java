package com.example.chacha.keepingbook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TabFragment1 extends Fragment {
    private DBHelper dbHelper;
    private Context context;
    private RecyclerView recyclerView;
    ArrayList<Item_OrderList> orderLists = new ArrayList<Item_OrderList>();
    OrderListAdapter adapter;
    String[] phones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        context = container.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_order);
        recyclerView.setHasFixedSize(true);
        adapter = new OrderListAdapter(orderLists);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dbHelper == null)
            dbHelper = new DBHelper(getActivity(), null);
        phones = dbHelper.getPhone();
        Item_OrderList order;
        for (int i=0; i < phones.length; i++) {
            Log.e("phonessssss :", phones[i]);
            order = dbHelper.getOrder(phones[i]);
            orderLists.add(order);
            Log.e("orderrrrrrrrrrr :", ""+order.get_id());
            Log.e("orderrrrrrrrrrr :", order.getPhone());
            Log.e("orderrrrrrrrrrr :", order.get_day());
            Log.e("orderrrrrrrrrrr :", order.get_time());
            Log.e("orderrrrrrrrrrr :", ""+order.getCheck());
            Log.e("orderrrrrrrrrrr :", ""+order.getPay());
            Log.e("orderrrrrrrrrrr :", order.getMemo());
        }
    }
}
