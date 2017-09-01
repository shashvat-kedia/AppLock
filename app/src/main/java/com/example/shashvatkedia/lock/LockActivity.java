package com.example.shashvatkedia.lock;

import android.app.ActivityManager;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class LockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        TextInputEditText pass=(TextInputEditText) findViewById(R.id.etPassword);
        String password=pass.getText().toString();
    }
}
