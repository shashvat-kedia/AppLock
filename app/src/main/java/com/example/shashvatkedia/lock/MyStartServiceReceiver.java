package com.example.shashvatkedia.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.example.shashvatkedia.lock.ApplicationAdapter.con;

/**
 * Created by Shashvat Kedia on 05-09-2017.
 */

public class MyStartServiceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context con,Intent inte){
        Util.Job(con);
    }
}
