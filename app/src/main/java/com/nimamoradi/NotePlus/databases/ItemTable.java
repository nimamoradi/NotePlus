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
    public static final String NAME_COLUMN3 = "uri1";
    public static final String NAME_COLUMN4 = "uri2";
    public static final String NAME_COLUMN5 = "uri3";
    public static final String NAME_COLUMN6 = "Date";
    public static final String NAME_COLUMN7 = "count";
    public static void onCreate(final SQLiteDatabase db) {
        String query = "CREATE TABLE " + ItemTable.TABLE_NAME
                + "(" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemTable.NAME_COLUMN + " VARCHAR(200)," +
                ItemTable.NAME_COLUMN2 + " VARCHAR(500)," +
                ItemTable.NAME_COLUMN3 + " TEXT," +
                ItemTable.NAME_COLUMN4 + " TEXT," +
                ItemTable.NAME_COLUMN5 + " TEXT," +
                ItemTable.NAME_COLUMN6 + " TEXT," +
                ItemTable.NAME_COLUMN7 + " INTEGER" +
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
