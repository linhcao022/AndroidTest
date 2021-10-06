package com.example.androidtest.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.R;
import com.example.androidtest.db.DBHelper;

public class ListProductActivity extends AppCompatActivity {
    private DBHelper db;
    private Cursor cursor;
    private SimpleCursorAdapter simpleCursorAdapter;
    private ListView lvProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_product);
        db = new DBHelper(this);
        lvProduct = findViewById(R.id.lvProduct);
        cursor = db.getAllProducts();
        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.item_product, cursor, new String[]{
                DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY}, new int[]{R.id.tvId, R.id.tvName, R.id.tvQuantity},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvProduct.setAdapter(simpleCursorAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //reload data
        Cursor c = db.getAllProducts();
        simpleCursorAdapter.changeCursor(c);
        simpleCursorAdapter.notifyDataSetChanged();
        db.close();
    }
}