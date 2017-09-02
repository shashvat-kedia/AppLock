package com.example.shashvatkedia.lock;

import android.provider.BaseColumns;

/**
 * Created by Shashvat Kedia on 02-09-2017.
 */

public class Table {
        private Table() {}
        public static class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "App";
            public static final String COLUMN_NAME_ID = "Id";
            public static final String COLUMN_NAME_APPNAME = "AppName";
            public static final String COLUMN_NAME_PACKAGE = "AppPackage";
            public static final String COLUMN_NAME_SELECTED = "Checked_State";
        }
}
