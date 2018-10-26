package com.ahhhh.deliv1;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content. ContentValues;
import android.database.Cursor;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper (Context context) {
        super (context, DatabaseReferences.DATABASE_NAME, null, DatabaseReferences.DATABASE_VERSION);
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
                DatabaseReferences.UserCredentials.TABLE_NAME + "(" +
                DatabaseReferences.UserCredentials.COLUMN_PRIMARY_KEY +
                "INTEGER PRIMARY KEY," + DatabaseReferences.UserCredentials.COLUMN_USERNAME +
                "TEXT," + DatabaseReferences.UserCredentials.COLUMN_PASSWORD +
                "TEXT" + ")";
        db.execSQL(CREATE_USER_CREDENTIALS_TABLE);

        String CREATE_USER_ACCOUNTS_TABLE = "CREATE TABLE " +
                DatabaseReferences.UserAccounts.TABLE_NAME + "(" +
                DatabaseReferences.UserAccounts.COLUMN_PRIMARY_KEY + "INTEGER PRIMARY KEY," +
                DatabaseReferences.UserAccounts.ACCOUNT_TYPE + "INTEGER," +
                DatabaseReferences.UserAccounts.FIRST_NAME + "TEXT," +
                DatabaseReferences.UserAccounts.LAST_NAME + "TEXT," +
                DatabaseReferences.UserAccounts.EMAIL_ADDRESS + "TEXT," +
                DatabaseReferences.UserAccounts.PHONE_NUMBER + "BLOB" + ")";
        db.execSQL(CREATE_USER_ACCOUNTS_TABLE);

        String CREATE_REFERENCE_CODES_TABLE = "CREATE TABLE " +
                DatabaseReferences.ReferenceCodes.TABLE_NAME + "(" +
                DatabaseReferences.ReferenceCodes.CODE + "INTEGER PRIMARY KEY," +
                DatabaseReferences.ReferenceCodes.CODE_TYPE + "INTEGER," +
                DatabaseReferences.ReferenceCodes.DESCRIPTION + "TEXT" + ")";
        db.execSQL(CREATE_REFERENCE_CODES_TABLE);

        this.addReferenceCode(0, 0, "Account Types");
        this.addReferenceCode(1, 1, "Administrator");
        this.addReferenceCode(2, 1, "Service Provider");
        this.addReferenceCode(3, 1, "Homeowner");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseReferences.UserCredentials.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseReferences.UserAccounts.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseReferences.ReferenceCodes.TABLE_NAME);
        onCreate(db);
    }


    public boolean adminCreated() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + DatabaseReferences.UserAccounts.TABLE_NAME + " WHERE "
                + DatabaseReferences.UserAccounts.ACCOUNT_TYPE + "=\"" + 1 + "\"";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            //an admin account was found
            return false;
        }
        return true;

    }


    public String findPassword(String username) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String query = "SELECT " + DatabaseReferences.UserCredentials.COLUMN_PASSWORD  + " FROM "  +
                DatabaseReferences.UserCredentials.TABLE_NAME + " WHERE " + DatabaseReferences.UserCredentials.COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);
        String passwordSaved = "";
        if (cursor.moveToFirst()) {
            passwordSaved = cursor.getString(0);
        }

        return passwordSaved;

    }

    public boolean usernameExist(String username) {
        SQLiteDatabase db =  this.getReadableDatabase();
        String query = "SELECT " + DatabaseReferences.UserCredentials.COLUMN_USERNAME  + " FROM "  +
                DatabaseReferences.UserCredentials.TABLE_NAME + " WHERE " + DatabaseReferences.UserCredentials.COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (!cursor.moveToFirst()) {
             return false;
        }
        return true;

    }

    public String accountType(String username) {
        int primaryKeyUserCre = -1;
        int keyToReturn = 0;
        String description = "";

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + DatabaseReferences.UserCredentials.COLUMN_PRIMARY_KEY + " FROM " +
                DatabaseReferences.UserCredentials.TABLE_NAME + " WHERE " + DatabaseReferences.UserCredentials.COLUMN_USERNAME
                + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            primaryKeyUserCre = Integer.parseInt(cursor.getString(0));
            query = "SELECT " + DatabaseReferences.UserAccounts.ACCOUNT_TYPE + " FROM " +
                    DatabaseReferences.UserAccounts.TABLE_NAME + " WHERE " + DatabaseReferences.UserAccounts.COLUMN_PRIMARY_KEY
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
                }
            }

        }
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

    public void addUserCredential(String username, String password, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseReferences.UserCredentials.COLUMN_USERNAME, username);
        values.put(DatabaseReferences.UserCredentials.COLUMN_PASSWORD, password);
        values.put(DatabaseReferences.UserCredentials.COLUMN_PRIMARY_KEY, id);

        db.insert(DatabaseReferences.UserCredentials.TABLE_NAME, null, values);
        db.close();
    }


    public void addUserPersonnelInfo(String firstName, String lastName, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseReferences.UserAccounts.FIRST_NAME, firstName);
        values.put(DatabaseReferences.UserAccounts.LAST_NAME , lastName);
        values.put(DatabaseReferences.UserAccounts.COLUMN_PRIMARY_KEY, id);

        db.insert(DatabaseReferences.UserAccounts.TABLE_NAME, null, values);
        db.close();
    }


    public void addUserAccount(int accountType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseReferences.UserAccounts.ACCOUNT_TYPE, accountType);

        db.insert(DatabaseReferences.UserAccounts.TABLE_NAME, null, values);
        db.close();
    }


}
