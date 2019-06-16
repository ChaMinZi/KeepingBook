package com.example.chacha.keepingbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TabFragment4 extends Fragment {
    private DBHelper dbHelper;
    private Context context;
    private ImageButton imgButton;
    EditText editPrice, editName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.tab_fragment_4, container, false);
        context = container.getContext();
        editName = (EditText)view.findViewById(R.id.product_name);
        editPrice = (EditText)view.findViewById(R.id.product_price);

        imgButton = view.findViewById(R.id.product_add);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper(getActivity(), null);
                }
                if (editName.getText().toString().equals("")) {
                    Toast.makeText(context, "상품명을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editPrice.getText().toString().equals("")) {
                    Toast.makeText(context, "가격을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                Item_Price product = new Item_Price();
                product.setName(String.format(editName.getText().toString(), "UTF-8"));
                product.setPrice(Integer.parseInt(editPrice.getText().toString()));
                dbHelper.addPrice(product);
 /*               for (int i=0; i<100;i++) {
                    product = new Item_Price();
                    product.setName(""+i);
                    product.setPrice(2000);
                    dbHelper.addPrice(product);
                }*/
            }
        });

        return view;
    }
}
