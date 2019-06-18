package com.example.chacha.keepingbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TabFragment2 extends Fragment {
    private DBHelper dbHelper;
    private Context context;
    private String[] productName;
    private int[] productCount;
    private int count;
    EditText editPhone, editOutPay, editTime, editDate, editMemo;
    GridView gridView;
    ButtonAdapter buttonAdapter;
    int outPay, checkPay;
    Queue<Integer> pQ = new LinkedList<Integer>();
    public static final int DATE_REQUEST_CODE = 1234;
    public static final int TIME_REQUEST_CODE = 1235;
    String selectedDate, selectedTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_2, container, false);
        context = container.getContext();

        editPhone = (EditText) view.findViewById(R.id.editText_number);
        editOutPay = (EditText)view.findViewById(R.id.editText_outpay);
        editDate = (EditText)view.findViewById(R.id.pick_date);
        editTime = (EditText)view.findViewById(R.id.pick_time);
        editMemo = (EditText)view.findViewById(R.id.editText_memo);
        gridView = (GridView) view.findViewById(R.id.grid);

        if (dbHelper == null) {
            dbHelper = new DBHelper(getActivity(), null);
        }

        //create product button
        count = dbHelper.getCountProduct();
        productCount = new int[count];
        productName = dbHelper.getProducts();
        buttonAdapter = new ButtonAdapter(getActivity());
        buttonAdapter.setName(productName);
        buttonAdapter.setmyOnclick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editPhone.getText().toString().equals("")) {
                    Toast.makeText(context, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                int id = view.getId();
                if (productCount[id] == 0)
                    pQ.offer(id);
                productCount[id]++;
            }
        });
        gridView.setAdapter(buttonAdapter);

        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setTargetFragment(TabFragment2.this, DATE_REQUEST_CODE);
                datePickerFragment.show(fm, "datePicker");
            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setTargetFragment(TabFragment2.this, TIME_REQUEST_CODE);
                timePickerFragment.show(fm, "timePicker");
            }
        });

        Button.OnClickListener onClickListener = new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item_Order order = new Item_Order();
                Item_Memo memo = new Item_Memo();

                if (editPhone.getText().toString().equals("")) {
                    Toast.makeText(context, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = editPhone.getText().toString();
                switch (view.getId()) {
                   case R.id.button_fullpay:
                       checkPay = 1;
                        break;
                    case R.id.button_outpay:
                        checkPay = 0;
                        break;
                    case R.id.button_add:
                        if (editDate.getText().toString().equals("")) {
                            Toast.makeText(context, "주문 날짜를 입력하세요", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        memo.set_day(editDate.getText().toString());
                        memo.set_time(editTime.getText().toString());
                        memo.setCheck(checkPay);
                        memo.setPay(Integer.parseInt(editOutPay.getText().toString()));
                        memo.setPhone(phone);
                        memo.setMemo(String.format(editMemo.getText().toString(), "UTF-8"));
                        int id;
                        order.setPhone(phone);
                        while (pQ.isEmpty() == false) {
                            id = pQ.poll();
                            order.setContext(String.format(productName[id], "UTF-8"));
                            order.setCount(productCount[id]);
                            dbHelper.addOrder(order);
                        }
                        dbHelper.addMemo(memo);
                        break;
                }
            }
        };
        Button[] mButton = new Button[4];
        mButton[0] = view.findViewById(R.id.button_fullpay);
        mButton[0].setOnClickListener(onClickListener);
        mButton[1] = view.findViewById(R.id.button_outpay);
        mButton[1].setOnClickListener(onClickListener);
        mButton[2] = view.findViewById(R.id.button_add);
        mButton[2].setOnClickListener(onClickListener);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DATE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            selectedDate = data.getStringExtra("selectedDate");
            editDate.setText(selectedDate);
        }
        else if (requestCode == TIME_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            selectedTime = data.getStringExtra("selectedTime");
            editTime.setText(selectedTime);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Arrays.fill(productCount,0);
        checkPay = 0;
    }
}