package com.scudderapps.muneem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.scudderapps.muneem.Model.CategoryData;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sInstance;

    //database info
    static final String DATABASE_NAME = "Transactions database";
    static final int DATABASE_VERSION = '2';

    //table names
    static final String CATEGORY_TABLE = "cat_tb";
    static final String TRANSACTIONS_TABLE = "trans_tb";

    //category table columns
    static final String CAT_ROW_ID = "id";
    static final String CAT_NAME = "cat_name";
    static final String CAT_TYPE = "cat_type";
    static final String CAT_COLOR = "cat_color";

    //CREATE TABLE STATEMENTS

    static final String CREATE_CAT_TABLE = "CREATE TABLE cat_tb(id int primary key unique," +
           "cat_name text," +
           "cat_type text," +
           "cat_color int);";



    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CAT_TABLE);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP IF TABLE EXISTS "+CATEGORY_TABLE);
        onCreate(db);
    }

    public void addCategory(CategoryData data) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(CAT_NAME, data.getName());
            values.put(CAT_TYPE, data.getType());
            values.put(CAT_COLOR, data.getColor());

            db.insert(CATEGORY_TABLE, null, values);
            db.setTransactionSuccessful();

        } catch (SQLException e) {
            String error = e.getMessage();
            Log.d("DATABASE ERROR", error);
        } finally {
            db.endTransaction();
        }
    }
}
