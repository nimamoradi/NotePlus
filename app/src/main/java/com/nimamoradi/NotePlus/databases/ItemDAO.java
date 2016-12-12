package com.nimamoradi.NotePlus.databases;

import android.content.ContentValues;
import android.content.Context;
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
public class ItemDAO extends DBOpenHelper implements DAO<Items> {
    private static String query = "INSERT INTO " + ItemTable.TABLE_NAME
            + "(" + ItemTable.NAME_COLUMN + "," + ItemTable.NAME_COLUMN2 + ","
            + ItemTable.NAME_COLUMN3 + "," + ItemTable.NAME_COLUMN4
            + ") VALUES (?, ?, ?, ?);";
    private static String query2 = "INSERT INTO " + ItemTable.TABLE_NAME
            + "(" + ItemTable.NAME_COLUMN + "," + ItemTable.NAME_COLUMN2

            + ") VALUES (?, ?);";

    private SQLiteStatement insertStmt;
    private SQLiteStatement insertStmt2;

    public ItemDAO(Context context) {
        super(context);


        // insertStmt2 = db.compileStatement(ItemDAO.query);
    }

    public void add(Items items) {
        SQLiteDatabase db = this.getWritableDatabase();
        // db.insert()

        ContentValues values = new ContentValues();
        values.put(ItemTable.NAME_COLUMN, items.getTitle());
        values.put(ItemTable.NAME_COLUMN2, items.getText());

        // insert pages

        db.insert(ItemTable.TABLE_NAME, null, values);
        // close database transaction

        db.close();


    }

    public long adduri(Items object) {
        insertStmt2.clearBindings();
        insertStmt2.bindString(3, object.getTitle());
        insertStmt2.bindString(2, object.getText());
        insertStmt2.bindString(4, object.getUrl1());
        insertStmt2.bindString(5, object.getUrl2());
        // db.insert()
        return insertStmt2.executeInsert();
    }



    public Items get(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        // get notes query
        Log.e("database", db.getAttachedDbs().toString());
        Cursor cursor = db.query(ItemTable.TABLE_NAME, // a. table

                null, BaseColumns._ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        // if results !=null, parse the first one
        if (cursor.moveToFirst()) {


            Items items = buildItemFromCursor(cursor);


            return items;
        }
        return null;
    }

    public ArrayList<Items> getAll() {

        ArrayList<Items> notes = new ArrayList<Items>();

        // select book query

        String query = "SELECT  * FROM " + ItemTable.TABLE_NAME;

        // get reference of the BookDB database

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        // parse all results

        Items items = null;

        if (cursor.moveToFirst()) {

            do {
                items = buildItemFromCursor(cursor);

                // Add book to books

                notes.add(items);

            } while (cursor.moveToNext());
        }
        return notes;
    }

    public boolean contain(long id) {

        return get(id) != null;

    }

    private Items buildItemFromCursor(Cursor cursor) {

//        String uri1 = null;
//        String uri2 = null;
//        String uri3 = null;
        long id = cursor.getLong(0);
        String title = cursor.getString(1);
        String text = cursor.getString(2);

        try {
//            uri1 = cursor.getString(3);
//            uri2 = cursor.getString(4);
//            uri3 = cursor.getString(5);
        } catch (Exception e) {
            Log.e(this + "", "null uri");

//            uri1 = "";
//            uri2 = "";
//            uri3 = "";

        }
        Log.e(this + "", title);
        return new Items(id, title, text);
    }

    public void delete(Items object) {
        SQLiteDatabase db = this.getWritableDatabase();

        // delete book

        db.delete(ItemTable.TABLE_NAME, BaseColumns._ID + " = ?", new String[]{String.valueOf(object.getId())});

        db.close();

    }


    public void update(Items object) {
        // get reference of the notesDB database

        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(ItemTable.NAME_COLUMN, object.getTitle()); // get title
        values.put(ItemTable.NAME_COLUMN2, object.getText()); // get text
        db.update(ItemTable.TABLE_NAME, values, BaseColumns._ID + " = ?", new String[]{String.valueOf(object.getId())});
        db.close();

    }

    public ArrayList<Items> serach(String title, @Nullable String text) {
        SQLiteDatabase db = this.getWritableDatabase();
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
