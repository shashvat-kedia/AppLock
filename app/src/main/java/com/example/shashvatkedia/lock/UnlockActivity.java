package com.example.shashvatkedia.lock;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UnlockActivity extends AppCompatActivity {
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
        final DataBase data=DataBase.getInstance(UnlockActivity.this);
        final TextInputEditText pass=(TextInputEditText) findViewById(R.id.enter_password);
        final String password=pass.getText().toString();
        Button sub=(Button) findViewById(R.id.submitbutton);
        while(count<=4) {
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.findPass(password) == 1) {
                        finish();
                    } else {
                        Toast.makeText(UnlockActivity.this, "Wrong Password Try Again", Toast.LENGTH_LONG).show();
                        pass.setText("");
                        count++;
                        if (count == 5) {
                            Intent startMain = new Intent(Intent.ACTION_MAIN);
                            startMain.addCategory(Intent.CATEGORY_HOME);
                            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(startMain);
                        }
                    }
                }
            });
        }
    }
}
