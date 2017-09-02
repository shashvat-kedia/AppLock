package com.example.shashvatkedia.lock;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.shashvatkedia.lock.ApplicationAdapter.p;

/**
 * Created by Shashvat Kedia on 01-09-2017.
 */

public class DataBase extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Table.FeedEntry.TABLE_NAME + " (" +
                    Table.FeedEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                    Table.FeedEntry.COLUMN_NAME_APPNAME + " TEXT," +
                    Table.FeedEntry.COLUMN_NAME_PACKAGE + " TEXT," + Table.FeedEntry.COLUMN_NAME_SELECTED + "INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Table.FeedEntry.TABLE_NAME;

    public static int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AppData.db";
    public PackageManager pm;
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        pm=context.getPackageManager();
    }
    @Override
    public void onCreate(SQLiteDatabase data){
        data.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase data,int oldVersion,int newVersion){
        data.execSQL(SQL_DELETE_ENTRIES);
        DATABASE_VERSION=newVersion;
        onCreate(data);
    }

    public void addInfo(DataBase data,Row info){
        int temp;
        SQLiteDatabase db=data.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Table.FeedEntry.COLUMN_NAME_APPNAME,info.getInfo().loadLabel(pm).toString());
        values.put(Table.FeedEntry.COLUMN_NAME_PACKAGE,info.getInfo().packageName);
        if(info.isSelected()){
            temp=1;
        }
        else{
            temp=0;
        }
        values.put(Table.FeedEntry.COLUMN_NAME_SELECTED,temp);
        db.insert(Table.FeedEntry.TABLE_NAME,null,values);
    }

    public int findInfo(DataBase data,String pack){
        SQLiteDatabase db=data.getReadableDatabase();
        String project[]={
                Table.FeedEntry.COLUMN_NAME_SELECTED
        };
        String select=Table.FeedEntry.COLUMN_NAME_PACKAGE + "=?";
        String selectionArgs[] = {pack};
        Cursor cursor = db.query(Table.FeedEntry.TABLE_NAME,project,select,selectionArgs,null,null,null);
        if(cursor!=null){
            return 1;
        }
        else{
            return 0;
        }
    }
}

