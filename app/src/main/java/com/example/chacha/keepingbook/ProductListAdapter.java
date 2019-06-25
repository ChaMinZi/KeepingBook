package com.example.chacha.keepingbook;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private ArrayList<Item_List> items;

    public ProductListAdapter(ArrayList<Item_List> productList) {
        this.items = productList;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_view, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductListAdapter.ViewHolder holder, int position) {
        Item_List item = items.get(position);
        holder.name.setText(item.getProduct());
        holder.count.setText(""+item.getCount());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, count;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView1);
            count = itemView.findViewById(R.id.textView2);
        }
    }
}
