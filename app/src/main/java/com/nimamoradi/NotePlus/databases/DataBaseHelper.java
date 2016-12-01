package com.nimamoradi.NotePlus.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by nima on 11/30/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    public final static String DB_NAME = "notes";
    public final static int DB_VERSION = 1;
    public final static String TABLE_NAME="note";
    public static final String NAME_COLUMN1 = "Title";
    public static final String NAME_COLUMN2 = "Text";
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + "(" + BaseColumns._ID + " INTEGER, "
                + NAME_COLUMN1 + " TEXT," +
                 NAME_COLUMN2 + " TEXT," +
                " PRIMARY KEY (" + BaseColumns._ID + ")" +
                ");";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);

        onCreate(db);
    }
}
