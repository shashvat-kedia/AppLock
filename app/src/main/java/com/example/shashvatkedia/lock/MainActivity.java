package com.example.shashvatkedia.lock;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button lock=(Button) findViewById(R.id.lock_button);
        Button unlock=(Button) findViewById(R.id.unlock_button);
        DataBase data=DataBase.getInstance(this);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AppsDisplay.class);
                startActivity(i);
            }
        });
        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Unlock un=new Unlock(MainActivity.this);
                un.unlock();
            }
        });
    }
}