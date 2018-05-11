package com.kinzeroo_company.extrainvaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SQLite extends AppCompatActivity {
    DataBaseHelper myBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
    }
}
