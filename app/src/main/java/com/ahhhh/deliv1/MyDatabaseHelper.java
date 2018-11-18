package com.ahhhh.deliv1;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content. ContentValues;
import android.database.Cursor;
import android.os.DropBoxManager;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.util.ArrayList;


public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ahhhhDB.db";

    public static final String TABLE_NAME = "ApplicationTable";
    public static final String COLUMN_PRIMARY_KEY_APP = "_id";
    public static final String COLUMN_USERNAME = "Username";
    public static final String COLUMN_PASSWORD = "Password";

    public static final String COLUMN_ACCOUNT_TYPE = "AccountType";


    public static final String COLUMN_FIRST_NAME="FirstName";
    public static final String COLUMN_LAST_NAME="LastName";

    public static final String COLUMN_EMAIL_ADDRESS = "EmailAddress";
    public static final String COLUMN_PHONE_NUMBER = "PhoneNumber";


    public static final String SERVICES_TABLE = "Services";
    public static final String COLUMN_PRIMARY_KEY_SERVICE = "_id";
    public static final String COLUMN_SERVICE_TITLE = "ServiceTittle";
    public static final String COLUMN_SERVICE_RATE = "ServiceRate";



    public static final String SERVICE_PROVIDERS = "ServiceProvider";
    public static final String C_PRIMARY_SERVICE_PROVIDER = "_id";
    public static final String COLUMN_ADDRESS_UNIT = "AddressUnit";
    public static final String COLUMN_ADDRESS_NAME = "AddressName";
    public static final String COLUMN_ADDRESS_COUNTRY = "Country";
    public static final String COLUMN_ADDRESS_POSTAL = "PostalCode";
    public static final String COLUMN_ADDRESS_PROVINCE = "Province";
    public static final String COLUMN_LICENCE = "Licence";
    public static final String COLUMN_COMPANY_NAME = "CompanyName";
    public static final String COLUMN_AVAILABILITIES = "Availabilities";
    public static final String COLUMN_SERVICES = "Services";



    public MyDatabaseHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_APPLICATION_TABLE = "CREATE TABLE " +
                TABLE_NAME + "(" +
                COLUMN_PRIMARY_KEY_APP +
                " INTEGER PRIMARY KEY," + COLUMN_USERNAME +
                " TEXT," + COLUMN_PASSWORD +
                " TEXT, " +  COLUMN_FIRST_NAME + " TEXT, "
                + COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_ACCOUNT_TYPE + " INTEGER, " +
                COLUMN_EMAIL_ADDRESS + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT " +
                ")";

        db.execSQL(CREATE_USER_APPLICATION_TABLE);

        String CREATE_SERVICE_INFO_TABLE = "CREATE TABLE " +
                SERVICES_TABLE + "(" +
                COLUMN_PRIMARY_KEY_SERVICE +
                "INTEGER PRIMARY KEY, " +
                COLUMN_SERVICE_TITLE + " TEXT, " +
                COLUMN_SERVICE_RATE + " REAL " +
                ")";

        db.execSQL(CREATE_SERVICE_INFO_TABLE);

        String CREATE_SERVICE_PROVIDER_TABLE = "CREATE TABLE " +
                SERVICE_PROVIDERS + "( " +
                C_PRIMARY_SERVICE_PROVIDER + " INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_ADDRESS_UNIT + " INTEGER, " +
                COLUMN_ADDRESS_NAME + " TEXT, " +
                COLUMN_ADDRESS_PROVINCE + " TEXT, " +
                COLUMN_ADDRESS_COUNTRY + " TEXT, " +
                COLUMN_ADDRESS_POSTAL + " TEXT, " +
                COLUMN_LICENCE + " INTEGER DEFAULT 0, " +
                COLUMN_COMPANY_NAME + " TEXT, " +
                COLUMN_AVAILABILITIES + " BLOB, " +
                COLUMN_SERVICES + " BLOB, " +
                " FOREIGN KEY(" + COLUMN_USERNAME + ")" +
                " REFERENCES " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY_APP + ")" +
                ")";

        db.execSQL(CREATE_SERVICE_PROVIDER_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SERVICES_TABLE);
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
            return true;
        }
        cursor.close();
        db.close();
        return false;

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
        String description = "";
        int accountTypeFromDB = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ACCOUNT_TYPE + " FROM " +
                TABLE_NAME + " WHERE " + COLUMN_USERNAME
                + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            accountTypeFromDB = cursor.getInt(0);
            cursor.close();
        }
        if (accountTypeFromDB == 1) {
            description = "Administrator";
        }
        else if (accountTypeFromDB == 2) {
            description = "Homeowner";
        }
        else if (accountTypeFromDB == 3) {
            description = "ServiceProvider";
        }
        else {
            description = "This failed";
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
    /**
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
     */

    /**
     * Inserting user account information in the database
     *
     * @param fName first name of new user
     * @param lName last name of new user
     * @param username username of account created
     * @param password password for account login validation
     * @param accountType integer where
     *                    1 represents an Admin account,
     *                    2 represents a HomeOwner and
     *                    3 represents a ServiceProvider
     *
     *                    NOTE: Will most likely either create a Enum type or add a new table
     *                    in the database for references used
     */
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



    public void addService (String serviceDescription, double rateForService) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_SERVICE_TITLE, serviceDescription);
        values.put(COLUMN_SERVICE_RATE, rateForService);

        db.insert(SERVICES_TABLE, null, values);
        db.close();
    }

    public void removeService(String serviceDescription, double rateForService) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT " + COLUMN_SERVICE_RATE +  " FROM " + SERVICES_TABLE +
                " WHERE " + COLUMN_SERVICE_TITLE + " = \"" + serviceDescription + "\"" +
                " AND " + COLUMN_SERVICE_RATE + " = " + rateForService;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            db.delete(SERVICES_TABLE, COLUMN_SERVICE_TITLE + " =? AND " + COLUMN_SERVICE_RATE + " = ?" , new String[]{serviceDescription, String.valueOf(rateForService)} );
        }
        cursor.close();
        db.close();

    }

    public void updateServiceInfo(String oldServiceDesc, double oldRate, String newServiceDesc, double newRate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_SERVICE_TITLE, newServiceDesc);
        values.put(COLUMN_SERVICE_RATE, newRate);

        db.update(SERVICES_TABLE, values, COLUMN_SERVICE_TITLE + " =? AND " + COLUMN_SERVICE_RATE + " = ?" , new String[]{oldServiceDesc, String.valueOf(oldRate)});

        db.close();
    }

    public ArrayList<Service> getServices() {
        ArrayList<Service> services = new ArrayList<Service>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_SERVICE_TITLE + ", " + COLUMN_SERVICE_RATE +
                " FROM " + SERVICES_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String desc = cursor.getString(0);
                double rate = Double.parseDouble(cursor.getString(1));
                Service service = new Service(desc, rate);
                services.add(service);
            } while (cursor.moveToNext());
        }

        return services;

    }

    public ArrayList<Service> getServices(String username) {
        ArrayList<Service> services = new ArrayList<Service>();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_SERVICES + " FROM " + SERVICE_PROVIDERS +
                " WHERE " + COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {

        }

        return services;
    }



}
