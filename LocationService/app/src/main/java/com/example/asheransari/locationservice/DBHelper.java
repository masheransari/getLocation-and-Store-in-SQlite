package com.example.asheransari.locationservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asher.ansari on 7/1/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static int DATABSE_VERSION = 1;
    public static String DATABASE_NAME = "locat.db";
    private String TABLE_CONTACT = "CREATE TABLE " + tableColumn.TABLE_NAME + " (" +
            tableColumn._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            tableColumn.LONTIUDE + " text, " +
            tableColumn.LATITUDE+ " text);";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CONTACT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL();
        onCreate(sqLiteDatabase);
    }
}
