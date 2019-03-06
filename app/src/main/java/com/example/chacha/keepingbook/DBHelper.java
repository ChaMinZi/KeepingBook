package com.example.chacha.keepingbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    /**
     * Database가 존재하지 않을 때, 딱 한번 실행된다.
     * DB를 만드는 역할을 한다.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer Osb = new StringBuffer();
        Osb.append(" CREATE TABLE ORDER_TABLE ( ");
        Osb.append(" NO INTEGER PRIMARY KEY, ");
        Osb.append(" CONTENT TEXT, ");
        Osb.append(" COUNT INTEGER ) ");

        StringBuffer Psb = new StringBuffer();
        Psb.append(" CREATE TABLE PRICE_TABLE ( ");
        Psb.append(" NAME TEXT PRIMARY KEY, ");
        Psb.append(" PRICE INTEGER ) ");

        StringBuffer Msb = new StringBuffer();
        Msb.append(" CREATE TABLE MEMO_TABLE ( ");
        Msb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
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
}