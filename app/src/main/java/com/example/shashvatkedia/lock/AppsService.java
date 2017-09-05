package com.example.shashvatkedia.lock;

import android.app.ActivityManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import java.util.List;

/**
 * Created by Shashvat Kedia on 05-09-2017.
 */

public class AppsService extends JobService{
    private static final String TAG="SyncService";
    @Override
    public boolean onStartJob(JobParameters params){
        DataBase data=DataBase.getInstance(getApplicationContext());
        ActivityManager activityManager=(ActivityManager) getApplicationContext().getSystemService(getApplicationContext().ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> info=activityManager.getRunningAppProcesses();
        if(info != null){
            for(final ActivityManager.RunningAppProcessInfo process : info){
                if(data.findInfo(process.processName)==1){
                    Intent service=new Intent(getApplicationContext(),UnlockActivity.class);
                    getApplicationContext().startService(service);
                }
            }
        }
        Util.Job(getApplicationContext());
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters params){
        return true;
    }
}
