package com.nimamoradi.NotePlus.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by UserPc on 11/7/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {
    protected final static String DB_NAME = "pages";
    protected final static int DB_VERSION = 1;

    public DBOpenHelper(Context context) {
        super(context, DBOpenHelper.DB_NAME,
                null, DBOpenHelper.DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        ItemTable.onCreate(db);
    }

    public void onUpgrade(final SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        ItemTable.onUpgrade(db, oldVersion, newVersion);
    }


}
