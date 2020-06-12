package com.imgncreative.keyboardsehat.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "keyboardsehat.db";

    public static final String TABLE_SQLite = "sqlite";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_AKURASI = "akurasi";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TEXT_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_TEXT + " VARCHAR(40) NOT NULL, " +
                COLUMN_STATUS + " VARCHAR(10) NOT NULL," +
                COLUMN_AKURASI + " INTEGER(3) NOT NULL" +
                " )";

        final String SQL_INSERT_TEXT_TABLE = "INSERT INTO "+TABLE_SQLite+" ("+COLUMN_ID+" ,"+COLUMN_TEXT+", "+COLUMN_STATUS+", "+COLUMN_AKURASI+") VALUES " +
                "(1, 'Jancok', 'Aktif', 10),"+
                "(2, 'Kontol', 'Aktif', 5),"+
                "(3, 'Memek', 'Aktif', 5),"+
                "(4, 'Telek', 'Aktif', 5),"+
                "(5, 'Taek', 'Aktif', 5),"+
                "(6, 'Asu', 'Aktif', 0),"+
                "(7, 'Tolol', 'Aktif', 5),"+
                "(8, 'Bego', 'Aktif', 5),"+
                "(9, 'Budek', 'Aktif', 5),"+
                "(10, 'Ngentod', 'Aktif', 8),"+
                "(11, 'Kampang', 'Aktif', 5),"+
                "(12, 'Naskleng', 'Aktif', 5),"+
                "(14, 'Goblok', 'Aktif', 5),"+
                "(15, 'Brengsek', 'Aktif', 5),"+
                "(16, 'Sundala', 'Aktif', 5),"+
                "(17, 'Cukimae', 'Aktif', 5),"+
                "(18, 'Puki', 'Aktif', 5),"+
                "(19, 'Taelaso', 'Aktif', 5),"+
                "(20, 'Luji', 'Tidak aktif', 5),"+
                "(21, 'Kembeng', 'Tidak aktif', 5),"+
                "(22, 'Pantek', 'Aktif', 5)";
        db.execSQL(SQL_CREATE_TEXT_TABLE);
        db.execSQL(SQL_INSERT_TEXT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite +" ORDER BY "+COLUMN_ID+" DESC";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_TEXT, cursor.getString(1));
                map.put(COLUMN_STATUS, cursor.getString(2));
                map.put(COLUMN_AKURASI, cursor.getString(3));
                wordList.add(map);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String text, String status, String akurasi) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (text, status, akurasi) " +
                "VALUES ('" + text + "', '"+status+"', '"+akurasi+"')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String text, String status, int akurasi) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_SQLite + " SET "
                + COLUMN_TEXT + "='" + text + "', "
                + COLUMN_STATUS + "='" + status + "',"
                + COLUMN_AKURASI + "='" + akurasi + "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
