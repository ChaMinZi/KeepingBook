package com.example.chacha.keepingbook;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final int dbVersion = 1;
    private static final String dbName = "RiceCake";

     public DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, dbName, factory, dbVersion);
        this.context = context;
    }

    /**
     * Database가 존재하지 않을 때, 딱 한번 실행된다.
     * DB를 만드는 역할을 한다.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer Osb = new StringBuffer();
        Osb.append(" CREATE TABLE IF NOT EXISTS ORDER_TABLE ( ");
        Osb.append(" PHONE TEXT PRIMARY KEY, ");
        Osb.append(" CONTENT TEXT, ");
        Osb.append(" COUNT INTEGER ) ");

        StringBuffer Psb = new StringBuffer();
        Psb.append(" CREATE TABLE IF NOT EXISTS PRICE_TABLE ( ");
        Psb.append(" NAME TEXT PRIMARY KEY, ");
        Psb.append(" PRICE INTEGER ) ");

        StringBuffer Msb = new StringBuffer();
        Msb.append(" CREATE TABLE IF NOT EXISTS MEMO_TABLE ( ");
        Msb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        Msb.append(" DAY TEXT, ");
        Msb.append(" TIME TEXT, ");
        Msb.append(" PHONE TEXT, ");
        Msb.append(" MEMO TEXT ) ");

        db.execSQL(Osb.toString());
        db.execSQL(Psb.toString());
        db.execSQL(Msb.toString());

        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
    }

    /**
     * Application의 버전이 올라가서 Table 구조가 변경되었을 때 실행
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }

    public void addOrder(Item_Order order) {
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO ORDER_TABLE ( ");
        sb.append(" PHONE, CONTENT, COUNT ) ");
        sb.append(" VALUES ( ?, ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[]{
                        order.getPhone(),
                        order.getContext(),
                        order.getCount()
                });
        Toast.makeText(context, "주문 추가 완료", Toast.LENGTH_SHORT).show();
    }

    public void addPrice(Item_Price product) {
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO PRICE_TABLE ( ");
        sb.append(" NAME, PRICE ) ");
        sb.append(" VALUES ( ?, ? ) ");

        db.execSQL(sb.toString(), new Object[]{product.getName(), product.getPrice()});
        Toast.makeText(context,"상품 추가 완료", Toast.LENGTH_SHORT).show();
    }

    public int getCountProduct(){
        SQLiteDatabase db = getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db,"PRICE_TABLE");
        db.close();
        return (int)count;
    }

    public String[] getProducts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = (Cursor) db.rawQuery("SELECT NAME FROM PRICE_TABLE", null);
        int count = cursor.getCount();
        String[] product = new String[count];

        cursor.moveToFirst();
        for (int i=0; i<count;i++) {
            product[i] = cursor.getString(cursor.getColumnIndex("NAME"));
            Log.e("eeeeeeee",cursor.getString(cursor.getColumnIndex("NAME")));
            cursor.moveToNext();
        }
        return product;
    }
}