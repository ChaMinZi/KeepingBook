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
        StringBuffer Psb = new StringBuffer();
        Psb.append(" CREATE TABLE IF NOT EXISTS PRICE_TABLE ( ");
        Psb.append(" NAME TEXT PRIMARY KEY, ");
        Psb.append(" PRICE INTEGER ) ");

        StringBuffer Osb = new StringBuffer();
        Osb.append(" CREATE TABLE IF NOT EXISTS ORDER_TABLE ( ");
        Osb.append(" PHONE TEXT, ");
        Osb.append(" PRODUCT TEXT, ");
        Osb.append(" COUNT INTEGER, ");
        Osb.append(" FOREIGN KEY(PHONE) REFERENCES MEMO_TABLE(PHONE) ) ");

        StringBuffer Msb = new StringBuffer();
        Msb.append(" CREATE TABLE IF NOT EXISTS MEMO_TABLE ( ");
        Msb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        Msb.append(" DAY TEXT, ");
        Msb.append(" TIME TEXT, ");
        Msb.append(" PHONE TEXT, ");
        Msb.append(" MEMO TEXT, ");
        Msb.append(" _CHECK INTEGER, ");
        Msb.append(" _PAY INTEGER ) ");

        db.execSQL(Psb.toString());
        db.execSQL(Osb.toString());
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
        sb.append(" PHONE, PRODUCT, COUNT ) ");
        sb.append(" VALUES ( ?, ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[]{
                        order.getPhone(),
                        order.getProduct(),
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

    public void addMemo(Item_Memo memo) {
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO MEMO_TABLE ( ");
        sb.append(" DAY, TIME, PHONE, MEMO, _CHECK, _PAY ) ");
        sb.append(" VALUES ( ?, ?, ?, ?, ?, ? ) ");

        db.execSQL(sb.toString(), new Object[]{memo.get_day(), memo.get_time(), memo.getPhone(), memo.getMemo(), memo.getCheck(), memo.getPay()});
        Toast.makeText(context, "주문 부가사항 추가 완료", Toast.LENGTH_SHORT).show();
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
            cursor.moveToNext();
        }
        return product;
    }

    public int getProductQuantity(String product) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = (Cursor)db.rawQuery("SELECT SUM(COUNT) FROM ORDER_TABLE WHERE PRODUCT = '" + product + "'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int getPrice(String product) {
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = (Cursor)db.rawQuery("SELECT PRICE FROM PRICE_TABLE WHERE NAME = '" + product + "'", null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public String[] getPhone() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = (Cursor)db.rawQuery("SELECT PHONE FROM ORDER_TABLE group by PHONE", null);
        int count = cursor.getCount();
        String[] phones = new String[count];
        cursor.moveToFirst();
        for (int i=0;i<count;i++) {
            phones[i] = cursor.getString(cursor.getColumnIndex("PHONE"));
            cursor.moveToNext();
        }
        return phones;
    }

    public Item_OrderList getOrder(String phone) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = (Cursor)db.rawQuery("SELECT * FROM ORDER_TABLE WHERE PHONE = '"+phone+"'", null);
        int count = cursor.getCount();
        Item_OrderList item = new Item_OrderList();
        String[] products = new String[count];
        int[] productCount = new int[count];

        item.setPhone(phone);
        cursor.moveToFirst();
        for (int i=0; i<count; i++) {
            products[i] = cursor.getString(cursor.getColumnIndex("PRODUCT"));
            productCount[i] = cursor.getInt(cursor.getColumnIndex("COUNT"));
            Log.e("gggggggggggg :", products[i]);
            Log.e("gggggggggggg", ""+productCount[i]);
            cursor.moveToNext();
        }
        item.setProducts(products);
        item.setProductCount(productCount);

        Cursor mcursor = (Cursor)db.rawQuery("SELECT * FROM MEMO_TABLE WHERE PHONE = '"+phone+"'", null);
        mcursor.moveToFirst();
        item.set_id(mcursor.getInt(mcursor.getColumnIndex("_ID")));
        item.set_day(mcursor.getString(mcursor.getColumnIndex("DAY")));
        item.set_time(mcursor.getString(mcursor.getColumnIndex("TIME")));
        item.setMemo(mcursor.getString(mcursor.getColumnIndex("MEMO")));
        item.setCheck(mcursor.getInt(mcursor.getColumnIndex("_CHECK")));
        item.setPay(mcursor.getInt(mcursor.getColumnIndex("_PAY")));

        return item;
    }
}