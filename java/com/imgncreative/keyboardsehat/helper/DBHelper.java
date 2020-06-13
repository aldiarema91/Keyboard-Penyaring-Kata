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

    public static final String TABLE_Text = "tb_text";
    public static final String TABLE_Pin = "tb_pin";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_AKURASI = "akurasi";

    public static final String COLUMN_PIN = "mypin";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TEXT_TABLE = "CREATE TABLE " + TABLE_Text + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_TEXT + " VARCHAR(40) NOT NULL, " +
                COLUMN_STATUS + " VARCHAR(10) NOT NULL," +
                COLUMN_AKURASI + " INTEGER(3) NOT NULL" +
                ")";

        final String SQL_INSERT_TEXT_TABLE = "INSERT INTO "+TABLE_Text+" ("+COLUMN_ID+" ,"+COLUMN_TEXT+", "+COLUMN_STATUS+", "+COLUMN_AKURASI+") VALUES " +
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

        final String SQL_CREATE_PIN_TABLE = "CREATE TABLE " + TABLE_Pin + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_PIN + " VARCHAR(6)" +
                " )";

        final String SQL_INSERT_PIN_TABLE = "INSERT INTO "+TABLE_Pin+" ("+COLUMN_ID+" ,"+COLUMN_PIN+") VALUES " +
                "(1, '0')";
        db.execSQL(SQL_CREATE_PIN_TABLE);
        db.execSQL(SQL_INSERT_PIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Text);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Pin);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_Text +" ORDER BY "+COLUMN_ID+" DESC";
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

    public ArrayList<HashMap<String, String>> getMyPin() {
        ArrayList<HashMap<String, String>> mypin;
        mypin = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_Pin;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(COLUMN_ID, cursor.getString(0));
            map.put(COLUMN_PIN, cursor.getString(1));
            mypin.add(map);
        }

        Log.e("select mypin ", "" + mypin);

        database.close();
        return mypin;
    }

    public void updateMyPin(int id, String pin) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_Pin + " SET "
                + COLUMN_PIN + "='" + pin + "'"
                + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update mypin ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void insert(String text, String status, String akurasi) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_Text + " (text, status, akurasi) " +
                "VALUES ('" + text + "', '"+status+"', '"+akurasi+"')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String text, String status, int akurasi) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_Text + " SET "
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

        String updateQuery = "DELETE FROM " + TABLE_Text + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
