package com.nimamoradi.NotePlus.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.util.Log;

import com.nimamoradi.NotePlus.model.Items;

import java.util.ArrayList;


/**
 * Created by UserPc on 11/7/2016.
 */
public class ItemDAO implements DAO<Items> {
    private static String query = "INSERT INTO " + ItemTable.TABLE_NAME
            + "(" + ItemTable.NAME_COLUMN + "," + ItemTable.NAME_COLUMN2 + ","
            + ItemTable.NAME_COLUMN3 + "," + ItemTable.NAME_COLUMN4 +
            "," + ItemTable.NAME_COLUMN5 +

            "," + ItemTable.NAME_COLUMN6
            + "," + ItemTable.NAME_COLUMN7
            + ") VALUES (?, ?, ?,?, ?, ?, ?);";
    private static String query2 = "INSERT INTO " + ItemTable.TABLE_NAME
            + "(" + ItemTable.NAME_COLUMN6 + "," + ItemTable.NAME_COLUMN7
            + ") VALUES (?, ?)";
    private SQLiteDatabase db;
    private SQLiteStatement insertStmt;
    private SQLiteStatement insertStmt2;

    public ItemDAO(SQLiteDatabase db) {

        this.db = db;
        insertStmt = db.compileStatement(ItemDAO.query);
        insertStmt2 = db.compileStatement(ItemDAO.query2);
    }

    public long add(Items object) {
        insertStmt2.clearBindings();

        insertStmt2.bindString(1, object.getData());
        insertStmt2.bindLong(2, object.getCount());
        // db.insert()
        Log.e(this + "", "adding it");
        return insertStmt2.executeInsert();
    }

    public long adduri(Items object) {
        insertStmt.clearBindings();
        insertStmt.bindString(2, object.getTitle());
        insertStmt.bindString(3, object.getText());
        insertStmt.bindString(4, object.getUrl1());
        insertStmt.bindString(5, object.getUrl2());
        insertStmt.bindString(6, object.getUrl3());
        insertStmt.bindString(7, object.getData());
        insertStmt.bindLong(8, object.getCount());
        // db.insert()
        return insertStmt.executeInsert();
    }

    public Items get(String name) {
        Items item = null;
        Cursor cursor = db.query(ItemTable.TABLE_NAME,
                new String[]{BaseColumns._ID, ItemTable.NAME_COLUMN, ItemTable.NAME_COLUMN2},
                ItemTable.NAME_COLUMN + " =?",
                new String[]{name}, null, null, null);
        if (cursor.moveToFirst()) {

            item = buildItemFromCursor(cursor);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }

        return item;
    }

    public Items get(long id) {
        Items item = null;
        Cursor cursor = db.query(ItemTable.TABLE_NAME,
                new String[]{BaseColumns._ID, ItemTable.NAME_COLUMN,
                        ItemTable.NAME_COLUMN2, ItemTable.NAME_COLUMN3,
                        ItemTable.NAME_COLUMN4, ItemTable.NAME_COLUMN5, ItemTable.NAME_COLUMN6, ItemTable.NAME_COLUMN7},
                BaseColumns._ID + " = ?",
                new String[]{id + ""}, null, null, null);
        if (cursor.moveToFirst()) {

            item = buildItemFromCursor(cursor);
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        Log.e(this + "", "getting item via id");
        return item;
    }

    public boolean contain(long id) {

        return get(id) != null;

    }

    private Items buildItemFromCursor(Cursor cursor) {
        long id = cursor.getLong(0);
        String uri1 = null;
        String uri2 = null;
        String uri3 = null;
        String date = null;
        int count = 0;
        String title = cursor.getString(1);
        String text = cursor.getString(2);

        try {
            date = cursor.getString(7);
            count = cursor.getInt(8);
            uri1 = cursor.getString(3);
            uri2 = cursor.getString(4);
            uri3 = cursor.getString(5);
            uri3 = cursor.getString(6);

        } catch (Exception e) {
            Log.e(this + "", "null uri");

            if (uri1 == null)
                uri1 = "";

            if (uri2 == null)
                uri2 = "";

            if (uri3 == null)
                uri3 = "";

        }
        Log.e(this + "", title);
        return new Items(id, title, text, uri1, uri2, uri3, count, date);
    }

    public boolean delete(Items object) {
        int rows = db.delete(ItemTable.TABLE_NAME,
                BaseColumns._ID + " = ?",
                new String[]{Long.toString(object.getId())});
        return rows == 1;
    }

    @Override
    public void update(Items object) {
        ContentValues cv = new ContentValues();

        cv.put(ItemTable.NAME_COLUMN, object.getTitle()); //These Fields should be your String values of actual column names
        cv.put(ItemTable.NAME_COLUMN2, object.getText());
        cv.put(ItemTable.NAME_COLUMN3, object.getUrl1());
        cv.put(ItemTable.NAME_COLUMN4, object.getUrl2());
        cv.put(ItemTable.NAME_COLUMN5, object.getUrl3());
        db.update(ItemTable.TABLE_NAME, cv, BaseColumns._ID + " = ?", new String[]{object.getId() + ""});
    }

    public ArrayList<Items> serach(String title, @Nullable String text) {
        ArrayList<Items> item = new ArrayList<Items>();
        Cursor cursor = db.query(ItemTable.TABLE_NAME,
                new String[]{BaseColumns._ID, ItemTable.NAME_COLUMN, ItemTable.NAME_COLUMN2,
                        ItemTable.NAME_COLUMN3
                        , ItemTable.NAME_COLUMN4},

                ItemTable.NAME_COLUMN + " =? OR" + ItemTable.NAME_COLUMN2 + " =?",
                new String[]{title, text}, null, null, null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext())
                item.add(buildItemFromCursor(cursor));
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        return item;
    }


}
