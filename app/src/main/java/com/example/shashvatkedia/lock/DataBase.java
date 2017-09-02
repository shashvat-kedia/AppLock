package com.example.shashvatkedia.lock;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.shashvatkedia.lock.ApplicationAdapter.con;
import static com.example.shashvatkedia.lock.ApplicationAdapter.p;

public class DataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="Apps.db";
    public static PackageManager p;
    public static final String CREATE_TABLE="CREATE TABLE "+ Table.FeedEntry.TABLE_NAME+
            " ("+ Table.FeedEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            Table.FeedEntry.COLUMN_NAME_APPNAME+" TEXT,"+Table.FeedEntry.COLUMN_NAME_PACKAGE+" TEXT,"+
            Table.FeedEntry.COLUMN_NAME_SELECTED+" INTEGER)";
    public static final String DELETE_TABLE="DROP TABLE IF EXISTS"+Table.FeedEntry.TABLE_NAME;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        p=MainActivity.pm;
    }

    @Override
    public void onCreate(SQLiteDatabase data){
        data.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase data,int oldVersion,int newVersion){
        data.execSQL(DELETE_TABLE);
        onCreate(data);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static void insertInfo(Row row){
        SQLiteDatabase data=ApplicationAdapter.data.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(Table.FeedEntry.COLUMN_NAME_APPNAME,row.getInfo().loadLabel(p).toString());
        content.put(Table.FeedEntry.COLUMN_NAME_PACKAGE,row.getInfo().packageName);
        int temp;
        if(row.isSelected()){
            temp=1;
        }
        else{
            temp=0;
        }
        content.put(Table.FeedEntry.COLUMN_NAME_SELECTED,temp);
        data.insert(Table.FeedEntry.TABLE_NAME,null,content);
    }

    public static int findInfo(String package_name){
        SQLiteDatabase data=ApplicationAdapter.data.getReadableDatabase();
        String[] columns={Table.FeedEntry.COLUMN_NAME_APPNAME,Table.FeedEntry.COLUMN_NAME_PACKAGE,Table.FeedEntry.COLUMN_NAME_SELECTED};
        String basis=Table.FeedEntry.COLUMN_NAME_PACKAGE+" = ?";
        String[] attri={package_name};
        Cursor cursor=data.query(Table.FeedEntry.TABLE_NAME,columns,basis,attri,null,null,null);
        if(cursor!=null){
            return 1;
        }
        else{
            return 0;
        }
    }

    public static void deleteInfo(String package_name){
        SQLiteDatabase data=ApplicationAdapter.data.getWritableDatabase();
        String condi = Table.FeedEntry.COLUMN_NAME_PACKAGE + " LIKE ?";
        String attri[]={package_name};
        data.delete(Table.FeedEntry.TABLE_NAME,condi,attri);
    }
}

