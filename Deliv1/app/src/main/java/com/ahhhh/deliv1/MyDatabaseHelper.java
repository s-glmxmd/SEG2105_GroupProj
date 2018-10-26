package com.ahhhh.deliv1;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content. ContentValues;
import android.database.Cursor;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ahhhhDB.db";

    public static final String CREDENTIAL_TABLE_NAME = "UserCredentials";
    public static final String CREDENTIAL_COLUMN_PRIMARY_KEY = "_id";
    public static final String CREDENTIAL_COLUMN_USERNAME = "Username";
    public static final String CREDENTIAL_COLUMN_PASSWORD = "Password";

    public static final String CREDENTIAL_LAST_NAME = "FirstName";
    public static final String CREDENTIAL_FIRST_NAME = "LastName";
    public static final String CREDENTIAL_ACCOUNT_TYPE = "AccountType";


    public static final String CREDENTIAL_COLUMN_FIRST_NAME="FirstName";
    public static final String CREDENTIAL_COLUMN_LAST_NAME="LastName";

    public static final String ACCOUNTS_TABLE_NAME = "UserAccounts";
    public static final String ACCOUNTS_ACCOUNT_TYPE = "AccountType";
    public static final String ACCOUNTS_EMAIL_ADDRESS = "EmailAddress";
    public static final String ACCOUNTS_PHONE_NUMBER = "PhoneNumber";
    public static final String ACCOUNTS_FIRST_NAME = "fname";
    public static final String ACCOUNTS_LAST_NAME = "lname";
    public static final String ACCOUNTS_COLUMN_PRIMARY_KEY  = "_id";




    public MyDatabaseHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private int primaryCredentials = 0;
    private int primaryAccount = 0;
    private int primaryReference = 0;

    public int getPrimaryCredentials() {
        return this.primaryCredentials;
    }
    public void incrementPrimaryCredentials() {
        this.primaryCredentials ++;
    }

    public int getPrimaryAccount() {
        return this.primaryAccount;
    }
    public void incrementPrimaryAccount() {
        this.primaryAccount ++;
    }
    public int getPrimaryReference() {
        return this.primaryReference;
    }
    public void incrementPrimaryRefence() {
        this.primaryReference ++;
    }

    /*
    when the application is started i guess the db has to be created also?
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_CREDENTIALS_TABLE = "CREATE TABLE " +
                CREDENTIAL_TABLE_NAME + "(" +
                CREDENTIAL_COLUMN_PRIMARY_KEY +
                "INTEGER PRIMARY KEY," + CREDENTIAL_COLUMN_USERNAME +
                "TEXT," + CREDENTIAL_COLUMN_PASSWORD +
                "TEXT" +  CREDENTIAL_FIRST_NAME + " TEXT"
                + CREDENTIAL_LAST_NAME + "TEXT" +
                CREDENTIAL_ACCOUNT_TYPE + "INTEGER" + ")";


        db.execSQL(CREATE_USER_CREDENTIALS_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREDENTIAL_TABLE_NAME);
        onCreate(db);
    }


    public boolean adminCreated() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + ACCOUNTS_TABLE_NAME + " WHERE "
                + ACCOUNTS_ACCOUNT_TYPE + "=\"" + 1 + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //an admin account was found
            cursor.close();
            db.close();
            return false;
        }
        cursor.close();
        db.close();
        return true;

    }


    public String findPassword(String username) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String query = "SELECT " + CREDENTIAL_COLUMN_PASSWORD  + " FROM "  +
                CREDENTIAL_TABLE_NAME + " WHERE " + CREDENTIAL_COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);
        String passwordSaved = "";
        if (cursor.moveToFirst()) {
            passwordSaved = cursor.getString(0);
            cursor.close();
        }
        db.close();
        return passwordSaved;

    }

    public boolean usernameExist(String username) {
        boolean result = true;
        SQLiteDatabase db =  this.getReadableDatabase();

        String query = "Select * FROM "  +
                CREDENTIAL_TABLE_NAME + " WHERE " + CREDENTIAL_COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            result = false;
        }
        cursor.close();
        db.close();
        return result;

    }

    public String accountType(String username) {
        int primaryKeyUserCre = -1;
        int keyToReturn = 0;
        String description = "";

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + CREDENTIAL_COLUMN_PRIMARY_KEY + " FROM " +
                CREDENTIAL_TABLE_NAME + " WHERE " + CREDENTIAL_COLUMN_USERNAME
                + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            primaryKeyUserCre = Integer.parseInt(cursor.getString(0));
            query = "SELECT " + ACCOUNTS_ACCOUNT_TYPE + " FROM " +
                    ACCOUNTS_TABLE_NAME + " WHERE " + ACCOUNTS_COLUMN_PRIMARY_KEY
                    + " = \"" + primaryKeyUserCre + "\"";
            cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                keyToReturn = Integer.parseInt(cursor.getString(0));

                query = "SELECT " + DatabaseReferences.ReferenceCodes.DESCRIPTION + " FROM " +
                        DatabaseReferences.ReferenceCodes.TABLE_NAME + " WHERE " +
                        DatabaseReferences.ReferenceCodes.CODE_TYPE + "=\"" + 1 + "\"" + " AND " +
                        DatabaseReferences.ReferenceCodes.CODE + "=\"" + keyToReturn +
                        "\"";
                cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {
                    description = cursor.getString(0);
                    cursor.close();
                }
            }

        }
        db.close();
        return description;

    }

    public void addReferenceCode(int code, int codeType, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseReferences.ReferenceCodes.CODE, code);
        values.put(DatabaseReferences.ReferenceCodes.CODE_TYPE, codeType);
        values.put(DatabaseReferences.ReferenceCodes.DESCRIPTION, description);

        db.insert(DatabaseReferences.ReferenceCodes.TABLE_NAME, null, values);
        db.close();
    }

    public void addUserCredential(String username, String password, int id, String firstName, String lastName, int accountType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CREDENTIAL_COLUMN_USERNAME, username);
        values.put(CREDENTIAL_COLUMN_PASSWORD, password);
        values.put(CREDENTIAL_COLUMN_PRIMARY_KEY, id);
        values.put(CREDENTIAL_FIRST_NAME, firstName);
        values.put(CREDENTIAL_LAST_NAME, lastName);
        values.put(CREDENTIAL_ACCOUNT_TYPE, accountType);

        db.insert(CREDENTIAL_TABLE_NAME, null, values);
        db.close();
    }

    public void deleteUserCredential(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + CREDENTIAL_TABLE_NAME + " WHERE " +
                CREDENTIAL_COLUMN_PRIMARY_KEY + " = \"" + id + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(CREDENTIAL_TABLE_NAME, CREDENTIAL_COLUMN_PRIMARY_KEY+ " = " + idStr, null);
            cursor.close();

        }
        db.close();
    }

    public void deleteUserAccount(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + ACCOUNTS_TABLE_NAME + " WHERE " +
                ACCOUNTS_COLUMN_PRIMARY_KEY+ " = \"" + id + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(CREDENTIAL_TABLE_NAME, CREDENTIAL_COLUMN_PRIMARY_KEY+ " = " + idStr, null);
            cursor.close();

        }
        db.close();
    }


    public void addUserPersonnelInfo(String firstName, String lastName, int id, int accountType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ACCOUNTS_FIRST_NAME, firstName);
        values.put(ACCOUNTS_LAST_NAME , lastName);
        values.put(ACCOUNTS_ACCOUNT_TYPE, accountType);
        values.put(ACCOUNTS_COLUMN_PRIMARY_KEY, id);

        db.insert(ACCOUNTS_TABLE_NAME, null, values);
        db.close();
    }


    public void addUserAccount(int accountType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ACCOUNTS_ACCOUNT_TYPE, accountType);

        db.insert(ACCOUNTS_TABLE_NAME, null, values);
        db.close();
    }


}
