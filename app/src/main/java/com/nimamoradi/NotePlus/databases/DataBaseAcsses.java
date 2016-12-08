package com.nimamoradi.NotePlus.databases;

/**
 * Created by nima on 11/30/2016.
 */
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

public class DataBaseAcsses {


        private static String query = "INSERT INTO " + DataBaseHelper.TABLE_NAME
                + "(" + BaseColumns._ID + ", " + DataBaseHelper.NAME_COLUMN1
                +", " + DataBaseHelper.NAME_COLUMN2
                + ") VALUES (?, ?,?);";
    /**
     * Created by UserPc on 11/7/2016.
     */

        private SQLiteDatabase db;
        private SQLiteStatement insertStmt;

        public DataBaseAcsses(SQLiteDatabase db) {
            this.db = db;
            insertStmt = db.compileStatement(DataBaseAcsses.query);
        }

        public long add(Items object) {
            insertStmt.clearBindings();
            insertStmt.bindLong(1, object.getId());
            insertStmt.bindString(2, object.getTitle());
            insertStmt.bindString(3, object.getText());

          //  db.insert();
            return insertStmt.executeInsert();
        }

        public Items get(long id) {
            Items item = null;
            Cursor cursor = db.query(DataBaseHelper.TABLE_NAME,
                    new String[] {BaseColumns._ID, DataBaseHelper.NAME_COLUMN1,DataBaseHelper.NAME_COLUMN2},
                    BaseColumns._ID + " = ?",
                    new String[]{Long.toString(id)},null, null, null);
            if(cursor.moveToFirst()) {
                item = buildItemFromCursor(cursor);
            }
            if(!cursor.isClosed()) {
                cursor.close();
            }
            return item;
        }

        private Items buildItemFromCursor(Cursor cursor) {
            long id = cursor.getLong(0);
            String title = cursor.getString(1);
            String text = cursor.getString(2);

            return new Items(id, title,text);
        }

        public boolean delete(Items object) {
            int rows = db.delete(DataBaseHelper.TABLE_NAME,
                    BaseColumns._ID + " = ?",
                    new String[] {Long.toString(object.getId())});
            return rows == 1;
        }

}
