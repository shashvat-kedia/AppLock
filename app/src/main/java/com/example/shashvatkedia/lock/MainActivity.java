package com.example.shashvatkedia.lock;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.example.shashvatkedia.lock.ApplicationAdapter.con;
import static com.example.shashvatkedia.lock.ApplicationAdapter.p;

public class MainActivity extends AppCompatActivity{
    static ArrayList<Row> locked=new ArrayList<Row>(0);
    ArrayList<Row> packages = new ArrayList<Row>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> installedApps = pm.getInstalledApplications(0);
        for (ApplicationInfo info : installedApps) {
            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            } else {
                Row r = new Row(info, 0);
                packages.add(r);
            }
        }
        ApplicationAdapter info = new ApplicationAdapter(this, packages, pm);
        ListView screen = (ListView) findViewById(R.id.activity_main);
        screen.setAdapter(info);
        Button save = (Button) findViewById(R.id.lock_button);
        save.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Row in : packages) {
                    if (in.isSelected() && in.isScanned() == 0) {
                        in.scan = 1;
                    } else if (in.isSelected() == false) {
                        in.scan = 0;
                    }
                }
                Intent lock = new Intent(getApplicationContext(), LockActivity.class);
                startActivity(lock);
            }
        });
    }
}
