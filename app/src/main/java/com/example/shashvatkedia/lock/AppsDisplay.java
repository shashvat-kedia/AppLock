package com.example.shashvatkedia.lock;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static java.security.AccessController.getContext;

public class AppsDisplay extends AppCompatActivity {
    static ArrayList<Row> locked=new ArrayList<Row>(0);
    static ArrayList<Row> packages = new ArrayList<Row>();
    public static PackageManager pm;
    DataBase data= DataBase.getInstance(AppsDisplay.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_display);
        pm = getPackageManager();
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
                             if(data.findInfo(in.getInfo().packageName)==0){
                                   data.insertInfo(in);
                                 }
                            }
                            else if (in.isSelected() == false) {
                                in.scan = 0;
                              if(data.findInfo(in.getInfo().packageName)==1){
                                   data.deleteInfo(in.getInfo().packageName);
                               }
                            }
                        }
                        for(Row imp : packages){
                           if(imp.getInfo().packageName=="com.example.shashvatkedia.lock"){
                               data.insertInfo(imp);
                            }
                        }
                        Intent lock = new Intent(getApplicationContext(), LockActivity.class);
                        startActivity(lock);
                    }
                });
    }
}
