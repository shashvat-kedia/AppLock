package com.example.shashvatkedia.lock;

import android.content.Context;

/**
 * Created by Shashvat Kedia on 05-09-2017.
 */

public class Unlock {
    Context c;
    public Unlock(Context con) {
        c = con;
    }
    DataBase data=DataBase.getInstance(c);
    public void unlock(){
        for(Row imp : AppsDisplay.packages){
            if(data.findInfo(imp.getInfo().packageName)==1){
                data.deleteInfo(imp.getInfo().packageName);
            }
        }
    }
}
