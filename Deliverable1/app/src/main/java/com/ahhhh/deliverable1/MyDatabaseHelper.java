package com.ahhhh.deliverable1;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content. ContentValues;
import android.database.Cursor;
import android.hardware.usb.UsbRequest;

import java.sql.Ref;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper (Context context) {
        super (context, DatabaseReferences.DATABASE_NAME, null, DatabaseReferences.DATABASE_VERSION);
    }

    /*
    when the application is started i guess the db has to be created also?
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_CREDENTIALS_TABLE = "CREATE TABLE " +
                DatabaseReferences.UserCredentials.TABLE_NAME + "(" +
                DatabaseReferences.UserCredentials.COLUMN_PRIMARY_KEY +
                "INTEGER PRIMARY KEY," + DatabaseReferences.UserCredentials.COLUMN_USERNAME +
                "TEXT," + DatabaseReferences.UserCredentials.COLUMN_PASSWORD +
                "TEXT" + ")";
        db.execSQL(CREATE_USER_CREDENTIALS_TABLE);

        String CREATE_USER_ACCOUNTS_TABLE = "CREATE TABLE" +
                DatabaseReferences.UserAccounts.TABLE_NAME + "(" +
                DatabaseReferences.UserAccounts.COLUMN_PRIMARY_KEY + "INTEGER PRIMARY KEY," +
                DatabaseReferences.UserAccounts.ACCOUNT_TYPE + "INTEGER," +
                DatabaseReferences.UserAccounts.FIRST_NAME + "TEXT," +
                DatabaseReferences.UserAccounts.LAST_NAME + "TEXT," +
                DatabaseReferences.UserAccounts.EMAIL_ADDRESS + "TEXT," +
                DatabaseReferences.UserAccounts.PHONE_NUMBER + "BLOB" + ")";
        db.execSQL(CREATE_USER_ACCOUNTS_TABLE);

        String CREATE_REFERENCE_CODES_TABLE = "CREATE TABLE" +
                DatabaseReferences.ReferenceCodes.TABLE_NAME + "(" +
                DatabaseReferences.ReferenceCodes.CODE + "INTEGER PRIMARY KEY," +
                DatabaseReferences.ReferenceCodes.CODE_TYPE + "INTEGER," +
                DatabaseReferences.ReferenceCodes.DESCRIPTION + "TEXT" + ")";
        db.execSQL(CREATE_REFERENCE_CODES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseReferences.UserCredentials.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseReferences.UserAccounts.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseReferences.ReferenceCodes.TABLE_NAME);
        onCreate(db);
    }



}
