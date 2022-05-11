package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button button_simpan;
    TextView nama, nim, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        database = new Database(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        jurusan = findViewById(R.id.jurusan);
        button_simpan = findViewById(R.id.button_simpan);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama ='" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            nim.setText(cursor.getString(1).toString());
            jurusan.setText(cursor.getString(2).toString());
        }
    }
}