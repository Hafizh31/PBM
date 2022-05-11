package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button button_simpan;
    EditText nama, nim, jurusan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        database = new Database(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        jurusan = findViewById(R.id.jurusan);
        button_simpan = findViewById(R.id.button_simpan);
        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into mahasiswa(nama, nim, jurusan) values ('" +
                        nama.getText().toString() + "', '" +
                        nim.getText().toString()+"'),'" +
                        jurusan.getText().toString()  +"'");
                Toast.makeText(CreateActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}