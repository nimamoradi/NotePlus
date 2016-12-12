package com.nimamoradi.NotePlus.databases;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by UserPc on 11/7/2016.
 */
public class ItemTable {
    public static final String TABLE_NAME = "note";
    public static final String NAME_COLUMN = "title";
    public static final String NAME_COLUMN2 = "text";
    public static final String NAME_COLUMN3 = "wrap";


    public static void onCreate(final SQLiteDatabase db) {
        String query = "CREATE TABLE " + ItemTable.TABLE_NAME
                + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, "
                + ItemTable.NAME_COLUMN + " VARCHAR(200)," +
                ItemTable.NAME_COLUMN2 + " VARCHAR(500)," +
                ItemTable.NAME_COLUMN3 + " TEXT" +

                ");";
        db.execSQL(query);


    }


    public static void onUpgrade(final SQLiteDatabase db,
                                 int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + ItemTable.TABLE_NAME;
        db.execSQL(query);
        ItemTable.onCreate(db);
    }
}
