package com.ahhhh.deliv1;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content. ContentValues;
import android.database.Cursor;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ahhhhDB.db";

    public static final String TABLE_NAME = "ApplicationTable";
    public static final String COLUMN_PRIMARY_KEY = "_id";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";

    public static final String COLUMN_ACCOUNT_TYPE = "AccountType";


    public static final String COLUMN_FIRST_NAME="FirstName";
    public static final String COLUMN_LAST_NAME="LastName";

    public static final String COLUMN_EMAIL_ADDRESS = "EmailAddress";
    public static final String COLUMN_PHONE_NUMBER = "PhoneNumber";




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

    
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_APPLICATION_TABLE = "CREATE TABLE " +
                TABLE_NAME + "(" +
                COLUMN_PRIMARY_KEY +
                " INTEGER PRIMARY KEY," + COLUMN_USERNAME +
                " TEXT," + COLUMN_PASSWORD +
                " TEXT, " +  COLUMN_FIRST_NAME + " TEXT, "
                + COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_ACCOUNT_TYPE + " INTEGER, " +
                COLUMN_EMAIL_ADDRESS + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT" +
                ")";


        db.execSQL(CREATE_USER_APPLICATION_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    /**
     * This application restricts the creation of admin accounts to one only.
     * This method searches through db to find out if this has already been done in order
     * to later restrict the user from creating another one.
     *
     * @return true if there already exists an admin account in the db
     */
    public boolean adminCreated() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COLUMN_ACCOUNT_TYPE + "=\"" + 1 + "\"";
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


    /**
     * Method to search through db in order to find password associated to account
     * for login credential validation
     *
     * @param username username to find password for
     * @return String representing password saved in db
     */
    public String findPassword(String username) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String query = "SELECT " + COLUMN_PASSWORD  + " FROM "  +
                TABLE_NAME + " WHERE " + COLUMN_USERNAME
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

    /**
     * Searches through db to make sure that the new username is not already in use
     *
     * @param username: value we wish to search in db
     * @return true if the username exists and false otherwise
     */
    public boolean usernameExist(String username) {
        boolean result = false;
        SQLiteDatabase db =  this.getReadableDatabase();

        String query = "Select * FROM "  +
                TABLE_NAME + " WHERE " + COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            result = true;
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
        String query = "SELECT " + COLUMN_PRIMARY_KEY + " FROM " +
                TABLE_NAME + " WHERE " + COLUMN_USERNAME
                + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            primaryKeyUserCre = Integer.parseInt(cursor.getString(0));
            query = "SELECT " + COLUMN_ACCOUNT_TYPE + " FROM " +
                    TABLE_NAME + " WHERE " + COLUMN_PRIMARY_KEY
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

    /**
     * Currently only have one table for all the record from the application
     *
     * N.B.:
     *      Will need to create a ReferenceCodes table to keep track of various codes throughout
     *      db
     */
    /**
    public void addReferenceCode(int code, int codeType, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseReferences.ReferenceCodes.CODE, code);
        values.put(DatabaseReferences.ReferenceCodes.CODE_TYPE, codeType);
        values.put(DatabaseReferences.ReferenceCodes.DESCRIPTION, description);

        db.insert(DatabaseReferences.ReferenceCodes.TABLE_NAME, null, values);
        db.close();
    }

    */
    public void deleteUserAccount(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_NAME + " WHERE " +
                COLUMN_PRIMARY_KEY+ " = \"" + id + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_NAME, COLUMN_PRIMARY_KEY+ " = " + idStr, null);
            cursor.close();

        }
        db.close();
    }





    public void addUserAccount(String fName, String lName, String username, String password, int accountType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_FIRST_NAME, fName);
        values.put(COLUMN_LAST_NAME, lName);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ACCOUNT_TYPE, accountType);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


}
