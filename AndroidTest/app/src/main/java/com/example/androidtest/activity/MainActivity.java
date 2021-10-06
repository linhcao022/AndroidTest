package com.example.androidtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidtest.R;
import com.example.androidtest.db.DBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edName;
    private EditText edQuantity;
    private Button btnView;
    private Button btnAdd;
    private DBHelper dbHelper;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        edName = findViewById(R.id.edName);
        edQuantity = findViewById(R.id.edQuantity);
        btnView = findViewById(R.id.btnView);
        btnAdd = findViewById(R.id.btnAdd);
        dbHelper = new DBHelper(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAdd.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAdd) {
            String name = edName.getText().toString();
            int quantity = Integer.parseInt(edQuantity.getText().toString());

            String isAdd = dbHelper.addProduct(edName.getText().toString(), quantity);
            Toast.makeText(this, isAdd, Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.btnView) {
            Intent intent = new Intent(this, ListProductActivity.class);
            startActivity(intent);
        }
    }
}
