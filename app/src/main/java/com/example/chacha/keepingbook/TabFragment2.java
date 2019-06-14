package com.example.chacha.keepingbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class TabFragment2 extends Fragment {
    EditText editPhone;
    private DBHelper dbHelper;
    private Context context;
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);
        context = container.getContext();
        editPhone = (EditText) view.findViewById(R.id.editText_number);
        gridView = (GridView) view.findViewById(R.id.grid);

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper(getActivity(), null);
                }
                Item_Order order = new Item_Order();
                if (editPhone.getText().toString().equals("")) {
                    Toast.makeText(context, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (view.getId()) {
 /*                   case R.id.KongShiru:
                        order.setPhone(editPhone.getText().toString());
                        order.setContext("KongShiru");
                        order.setCount(1);
                        dbHelper.addOrder(order);
                        break;
                    case R.id.KongInjeolmi:
                        order.setPhone(editPhone.getText().toString());
                        order.setContext("KongInjeolmi");
                        order.setCount(1);
                        dbHelper.addOrder(order);
                        break;
                    case R.id.GgipiInjeolmi:
                        order.setPhone(editPhone.getText().toString());
                        order.setContext("GgipiInjeolmi");
                        order.setCount(1);
                        dbHelper.addOrder(order);
                        break;*/
                    case R.id.button_full_pay:
                        break;
                    case R.id.button_out_pay:
                        break;
                    case R.id.button_add:
                        break;
                }
            }
        };
/*        Button[] mButton = new Button[3];
        mButton[0] = view.findViewById(R.id.KongShiru);
        mButton[0].setOnClickListener(onClickListener);
        mButton[1] = view.findViewById(R.id.KongInjeolmi);
        mButton[1].setOnClickListener(onClickListener);
        mButton[2] = view.findViewById(R.id.GgipiInjeolmi);
        mButton[2].setOnClickListener(onClickListener);
*/
        return view;
    }
}