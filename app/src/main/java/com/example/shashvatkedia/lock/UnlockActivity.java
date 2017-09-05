package com.example.shashvatkedia.lock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UnlockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
        DataBase data=DataBase.getInstance(UnlockActivity.this);

    }
}
