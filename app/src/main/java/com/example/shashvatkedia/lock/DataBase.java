package com.example.shashvatkedia.lock;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shashvat Kedia on 01-09-2017.
 */

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Locked_Apps";
    private static final String TABLE_NAME = "Apps";
    private final static String KEY_ID = "id";
    private static final String App = "ApplicationName";
    private static final String pack = "ApplicationPackageName"
    private static final String Selected = "CHECKBOX_STATE";
    PackageManager p;
    public DataBase(Context context, PackageManager pm) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        p = pm;
    }

    @Override
    public void onCreate(SQLiteDatabase data) {
        String CREATION_TABLE = "CREATE TABLE Apps ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "ApplicationName TEXT, "
                + "ApplicationPackageName TEXT, " + "CHECKBOX_STATE INTEGER )";
        data.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void addRow(Row row) {
        int temp;
        SQLiteDatabase data = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(App, row.getInfo().loadLabel(p).toString());
        values.put(pack, row.getInfo().packageName);
        if (row.isSelected() == true) {
            temp = 1;
        } else {
            temp = 0;
        }
        values.put(Selected, temp);
        data.insert(TABLE_NAME, null, values);
        data.close();
    }

    public int getApp(String name) {
        SQLiteDatabase data = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where " + App + " = '" + name + "'";
        Cursor cur = data.rawQuery(query, null);
        if (cur != null) {
            return 1;
        } else {
            return 0;
        }
    }
}

