package com.example.chacha.keepingbook;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TabFragment3 extends Fragment {
    private DBHelper dbHelper;
    private Context context;
    private RecyclerView recyclerView;
    ArrayList<Item_List> productList  = new ArrayList<Item_List>();
    ProductListAdapter adapter;
    private int count;
    String[] productName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.tab_fragment_3, container, false);
        context = container.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_product);
        recyclerView.setHasFixedSize(true);
        adapter = new ProductListAdapter(productList);

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
        count = dbHelper.getCountProduct();
        productName = dbHelper.getProducts();
        int tempCnt;
        for (int i=0;i<count;i++) {
            tempCnt = dbHelper.getProductQuantity(productName[i]);
            productList.add(new Item_List(productName[i], tempCnt));
        }
    }
}
