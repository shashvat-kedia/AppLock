package com.example.shashvatkedia.lock;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class LockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        final TextInputEditText pass=(TextInputEditText) findViewById(R.id.etPassword);
        Button submit=(Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password=pass.getText().toString();
                DataBase data=DataBase.getInstance(LockActivity.this);
                data.insertPass(password);
                pass.setText("");
                Intent i=new Intent(LockActivity.this,Util.class);
                startActivity(i);
            }
        });
    }
}
