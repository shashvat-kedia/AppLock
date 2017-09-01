package com.example.shashvatkedia.lock;

import android.content.pm.ApplicationInfo;

/**
 * Created by Shashvat Kedia on 31-08-2017.
 */

public class Row {
    ApplicationInfo info;
    boolean selected;
    int scan;

    public Row(ApplicationInfo i,int o){
        info=i;
        scan=o;
    }

    public ApplicationInfo getInfo(){
        return info;
    }

    public boolean isSelected(){
        return selected;
    }

    public int isScanned(){
        return scan;
    }

    public void setSelected(boolean select){
        this.selected=select;
    }


}
