package com.example.chacha.keepingbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    private ArrayList<Item_OrderList> orderLists;
    DBHelper dbHelper;

    public OrderListAdapter(ArrayList<Item_OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    @NonNull
    @Override
    public OrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orderlist_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.ViewHolder holder, int position) {
        Item_OrderList order = orderLists.get(position);
        String productOrder = new String();
        String[] p = order.getProducts();
        int[] pc = order.getProductCount();
        for (int i=0; i <p.length; i++) {
            productOrder += p[i];
            productOrder += (" "+pc[i]+"\n");
        }
        holder.id.setText(""+order.get_id());
        holder.phone.setText(order.getPhone());
        holder.time.setText(order.get_time());
        holder.products.setText(productOrder);
        if (order.getCheck() == 1) {
            holder.price.setText("완불");
        }
        else {
            holder.price.setText(""+order.getPay());
        }
        holder.memo.setText(order.getMemo());
    }

    @Override
    public int getItemCount() {
        return orderLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, phone, time, products, price, memo;
        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textView_id);
            phone = itemView.findViewById(R.id.textView_phone);
            time = itemView.findViewById(R.id.textView_time);
            products = itemView.findViewById(R.id.textView_products);
            price = itemView.findViewById(R.id.textView_price);
            memo = itemView.findViewById(R.id.textView_memo);
        }
    }
}
