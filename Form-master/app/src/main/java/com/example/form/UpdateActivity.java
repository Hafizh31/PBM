package com.example.form;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    protected Cursor cursor;
    Database database;
    Button button_simpan;
    EditText nama, nim, jurusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        nama = findViewById(R.id.nama);
        nim = findViewById(R.id.nim);
        jurusan = findViewById(R.id.jurusan);
        button_simpan = findViewById(R.id.button_simpan);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa WHERE nama ='" +
                getIntent().getStringExtra("nama")+"'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
             nama.setText(cursor.getString(0).toString());
            nim.setText(cursor.getString(1).toString());
            jurusan.setText(cursor.getString(2).toString());
        }
        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update mahasiswa set nama='" +
                        nama.getText().toString()+"',nim ='" +
                        nim.getText().toString()+"', jurusan ='" +
                        jurusan.getText().toString()+"'where nama='" +
                        getIntent().getStringExtra("nama")+"'");
                Toast.makeText(UpdateActivity.this, "Data berhasil di update", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}