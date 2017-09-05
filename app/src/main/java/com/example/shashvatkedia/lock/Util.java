package com.example.shashvatkedia.lock;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

/**
 * Created by Shashvat Kedia on 05-09-2017.
 */

public class Util {
    public static void Job(Context con){
        ComponentName service=new ComponentName(con,AppsService.class);
        JobInfo.Builder build=new JobInfo.Builder(0,service);
        build.setMinimumLatency(1*1000);
        build.setOverrideDeadline(3*1000);
        build.setRequiresCharging(false);
        JobScheduler schedule=con.getSystemService(JobScheduler.class);
        schedule.schedule(build.build());
    }
}
