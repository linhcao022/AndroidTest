package com.example.androidtest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "android";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "product";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + "( " +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                QUANTITY + " INTEGER);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public String addProduct(String user, int quantity) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, user);
        contentValues.put(QUANTITY, quantity);
        long isAdd = db.insert(TABLE_NAME, null, contentValues);
        if (isAdd == -1) {
            return "Add Fail";
        }
        db.close();
        return  "Add Success";
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }
}
