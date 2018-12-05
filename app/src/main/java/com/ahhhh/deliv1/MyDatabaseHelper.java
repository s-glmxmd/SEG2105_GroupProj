package com.ahhhh.deliv1;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.DropBoxManager;
import android.os.strictmode.SqliteObjectLeakedViolation;


import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


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
    public static final String COLUMN_ADDRESS_NAME = "AddressName";
    public static final String COLUMN_ADDRESS_COUNTRY = "Country";
    public static final String COLUMN_ADDRESS_POSTAL = "PostalCode";
    public static final String COLUMN_ADDRESS_PROVINCE = "Province";
    public static final String COLUMN_LICENCE = "Licence";
    public static final String COLUMN_COMPANY_NAME = "CompanyName";
    public static final String COLUMN_AVAILABILITIES = "Availabilities";
    public static final String COLUMN_SERVICES = "Services";
    public static final String COLUMN_ADDITIONAL_INFO = "AdditionalInfo";


    public static final String SERVICE_RATING = "ServiceRating";
    public static final String PRIMARY_RATING = "_id";
    public static final String SERVICE_PROVIDER = "ServiceProvider";
    public static final String HOMEOWNER = "Homeowner";
    public static final String SERVICEPROVIDER = "ServiceProvider";
    public static final String RATING = "Rate";
    public static final String DAY_OF_SERVICE = "ServiceDay";
    public static final String COMMENTS = "Comments";


    public static final String HOMEOWNER_BOOKINGS = "HomeownerBookings";
    public static final String PRIMARY_HOMEOWNER_BOOKING = "_id";
    public static final String COLUMN_BOOKINGS = "Bookings";



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
                COLUMN_ADDRESS_NAME + " TEXT, " +
                COLUMN_ADDRESS_PROVINCE + " TEXT, " +
                COLUMN_ADDRESS_COUNTRY + " TEXT, " +
                COLUMN_ADDRESS_POSTAL + " TEXT, " +
                COLUMN_LICENCE + " INTEGER DEFAULT 0, " +
                COLUMN_COMPANY_NAME + " TEXT, " +
                COLUMN_AVAILABILITIES + " BLOB, " +
                COLUMN_SERVICES + " BLOB, " +
                COLUMN_ADDITIONAL_INFO + " TEXT, "+
                " FOREIGN KEY(" + COLUMN_USERNAME + ")" +
                " REFERENCES " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY_APP + ")" +
                ")";

        db.execSQL(CREATE_SERVICE_PROVIDER_TABLE);

        String CREATE_RATING_TABLE = "CREATE TABLE " +
                SERVICE_RATING + "(" +
                PRIMARY_RATING +
                "INTEGER PRIMARY KEY, " +
                SERVICE_PROVIDER + " TEXT, " +
                HOMEOWNER + " TEXT, " +
                DAY_OF_SERVICE + " TEXT, " +
                COLUMN_SERVICE_TITLE + " TEXT, " +
                COLUMN_SERVICE_RATE + " REAL, " +
                RATING + " INTEGER, " +
                COMMENTS + " TEXT, " +
                " FOREIGN KEY(" + COLUMN_SERVICE_TITLE + ")" +
                " REFERENCES " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY_SERVICE + "), " +
                " FOREIGN KEY(" + COLUMN_SERVICE_RATE + ")" +
                " REFERENCES " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY_SERVICE + "), " +
                " FOREIGN KEY(" + SERVICE_PROVIDER + ")" +
                " REFERENCES " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY_APP + "), " +
                " FOREIGN KEY(" + HOMEOWNER + ")" +
                " REFERENCES " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY_APP + ")" +
                ")";

        db.execSQL(CREATE_RATING_TABLE);

        String CREATE_SERVICE_HOMEOWNER_TABLE = "CREATE TABLE " +
                HOMEOWNER_BOOKINGS + "( " +
                PRIMARY_HOMEOWNER_BOOKING + " INTEGER PRIMARY KEY, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_BOOKINGS + " BLOB, " +
                " FOREIGN KEY(" + COLUMN_USERNAME + ") " +
                " REFERENCES " + TABLE_NAME + "(" + COLUMN_PRIMARY_KEY_APP + ") " +
                ")";

        db.execSQL(CREATE_SERVICE_HOMEOWNER_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SERVICES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SERVICE_PROVIDERS);
        db.execSQL("DROP TABLE IF EXISTS " + SERVICE_RATING);
        db.execSQL("DROP TABLE IF EXISTS " + HOMEOWNER_BOOKINGS);
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

    public String[] getName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME +
                " FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME
                + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        String[] values = new String[2];
        int i = 0;
        if (cursor.moveToNext()) {
            while (i < 2) {
                values[i] = cursor.getString(i);
                i ++;
            }

        }
        cursor.close();
        db.close();
        return values;
    }

    public String[] getSPInfo(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String [] spInfo = new String[7];

        String querySP = "SELECT " + COLUMN_COMPANY_NAME + ", " +
                COLUMN_ADDRESS_NAME + ", " + COLUMN_ADDRESS_PROVINCE + ", " +
                COLUMN_ADDRESS_COUNTRY + ", " + COLUMN_ADDRESS_POSTAL + ", " +
                COLUMN_ADDITIONAL_INFO + " FROM " + SERVICE_PROVIDERS +
                " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";

        String queryLicence = "SELECT " + COLUMN_LICENCE + " FROM " + SERVICE_PROVIDERS +
                " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";

        String queryPhone = "SELECT " + COLUMN_PHONE_NUMBER + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";

        Cursor cursorSP = db.rawQuery(querySP, null);
        int i = 0;
        if (cursorSP.moveToFirst()){
            do {
                spInfo[i] = cursorSP.getString(i);
                i ++;
            }while ( i < 5);

        }
        cursorSP.close();

        Cursor cursorLic = db.rawQuery(queryLicence, null);
        if (cursorLic.moveToNext()) {
            if ( cursorLic.getInt(0) == 0) {
                spInfo[5] = "no";
            }
            else {
                spInfo[5] = "yes";
            }
        }
        cursorLic.close();

        Cursor cursorPhone = db.rawQuery(queryPhone, null);
        if (cursorPhone.moveToNext()) {
            spInfo[6] = cursorPhone.getString(0);
        }
        cursorPhone.close();

        db.close();
        return spInfo;

    }


    public void addSPInfo(String username, String company, String address, String province, String country, String postal, String desc, boolean isLicenced, String phoneNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valuesAccount = new ContentValues();
        ContentValues valuesSP = new ContentValues();


        //update account info with phone number to account info
        valuesAccount.put(COLUMN_PHONE_NUMBER, phoneNumber);
        db.update(TABLE_NAME, valuesAccount, COLUMN_USERNAME + " =?", new String[]{username});
        db.close();


        db = this.getWritableDatabase();
        //adding to service provider
        valuesSP.put(COLUMN_USERNAME, username);
        valuesSP.put(COLUMN_ADDRESS_NAME, address);
        valuesSP.put(COLUMN_ADDRESS_PROVINCE, province);
        valuesSP.put(COLUMN_ADDRESS_COUNTRY, country);
        valuesSP.put(COLUMN_ADDRESS_POSTAL, postal);
        valuesSP.put(COLUMN_ADDITIONAL_INFO, desc);
        valuesSP.put(COLUMN_COMPANY_NAME, company);
        if (isLicenced) {
            valuesSP.put(COLUMN_LICENCE, 1);
        }
        else {
            valuesSP.put(COLUMN_LICENCE, 0);
        }
        db.insert(SERVICE_PROVIDERS, null, valuesSP);

        db.close();

    }

    public boolean infoProvided(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean result;

        String query = "SELECT " + COLUMN_COMPANY_NAME + " FROM " +
                SERVICE_PROVIDERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        result = cursor.moveToNext();

        cursor.close();
        db.close();
        return result;

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


    //not selecting bookings cuz wtf
    public ArrayList<ServiceProvider> getServiceProviders() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ServiceProvider> sProviders = new ArrayList<>();
        String addName, addProv, addPost, addCount, companyName, username, addInfo, fName, lName, password;
        int phone;
        boolean isLicenced;
        ArrayList<Availability> availabilities;


        String query = "SELECT T." + COLUMN_USERNAME + ", " + COLUMN_ADDRESS_NAME + ", " +
                COLUMN_ADDRESS_PROVINCE + ", " + COLUMN_ADDRESS_COUNTRY + ", " + COLUMN_ADDRESS_POSTAL + ", " +
                COLUMN_LICENCE + ", " + COLUMN_COMPANY_NAME + ", " +
                COLUMN_ADDITIONAL_INFO + ", " +  COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME  + ", " + COLUMN_PASSWORD + ", " + COLUMN_PHONE_NUMBER +
                " FROM " + SERVICE_PROVIDERS + " S, " + TABLE_NAME + " T " + " WHERE " + COLUMN_ACCOUNT_TYPE + " = 3";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                availabilities = new ArrayList<>();
                username = cursor.getString(0);
                addName = cursor.getString(1);
                addProv = cursor.getString(2);
                addCount = cursor.getString(3);
                addPost = cursor.getString(4);
                if (cursor.getInt(5) == 0) {
                    isLicenced = false;
                }else {
                    isLicenced = true;
                }
                companyName = cursor.getString(6);
                addInfo = cursor.getString(7);
                fName = cursor.getString(8);
                lName = cursor.getString(9);
                password = cursor.getString(10);
                phone = cursor.getInt(11);
                availabilities.addAll(this.getAvailabilities(username));

                ProfileAddress pAdd = new ProfileAddress(addName, addProv, addCount, addPost);
                ServiceProvider sp = new ServiceProvider(username, password, fName, lName);
                sp.setWorkerAddress(pAdd);
                sp.addAvailabilities(availabilities);
                sp.setCompanyName(companyName);
                sp.setLicenced(isLicenced);
                sp.setDescription(addInfo);
                sp.setPhoneNumber(String.valueOf(phone));
                //ArrayList<Booking> bookings = this.getBookings(username);      not saved cuz no association between
                                                                                //sp and booking
                sProviders.add(sp);
            }while (cursor.moveToNext());
        }

        return sProviders;
    }

    public boolean updateBookings(String hoUsername, String spUsername, Booking booking, Context c) {
        ArrayList<Booking> currentBookings = this.getBookings(hoUsername);
        booking.setSp(spUsername);
        currentBookings.add(booking);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try{

            // Serialize data object to a file
            File f = new File( c.getFilesDir(), "MyBookings.ser" );

            f.createNewFile();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f, true));

            out.writeObject(currentBookings);
            out.close();

            // Serialize data object to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
            out = new ObjectOutputStream(bos) ;
            out.writeObject(currentBookings);
            out.close();

            // Get the bytes of the serialized object
            byte[] data = bos.toByteArray();
            values.put(COLUMN_BOOKINGS, data);

            db.update(HOMEOWNER_BOOKINGS, values, COLUMN_USERNAME + "=? ", new String[]{hoUsername});
        } catch (IOException e) {
            db.close();
            return false;
        }

        db.close();
        return true;

    }


    @SuppressWarnings("unchecked")
    public ArrayList<Booking> getBookings(String hoUsername) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_BOOKINGS + " FROM " + HOMEOWNER_BOOKINGS +
                " WHERE " + COLUMN_USERNAME
                + " = \"" + hoUsername + "\"";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            byte[] value = cursor.getBlob(0);
            if (value != null) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(value));
                    try {
                        bookings = (ArrayList<Booking>) ois.readObject();
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                } catch (IOException e) {
                    return null;
                }
            }
        }
        cursor.close();
        db.close();
        return bookings;
    }

    public void addRateForService(Booking booking, int rate, String spUsername, String hoUsername, String day, String comments) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Service service = booking.getService();
        String serviceTitle = service.getServiceName();
        double serviceRate = service.getHourlyRate();

        values.put(SERVICE_PROVIDER, spUsername);
        values.put(HOMEOWNER, hoUsername);
        values.put(DAY_OF_SERVICE, day);
        values.put(COMMENTS, comments);
        values.put(COLUMN_SERVICE_TITLE, serviceTitle);
        values.put(COLUMN_SERVICE_RATE, serviceRate);
        values.put(RATING, rate);

        db.insert(SERVICE_RATING, null, values);
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

    @SuppressWarnings("unchecked")
    public ArrayList<Service> getServices(String username) {
        ArrayList<Service> services = new ArrayList<Service>();
        ArrayList<Service> tmp;


        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_SERVICES + " FROM " + SERVICE_PROVIDERS +
                " WHERE " + COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            byte[] value = cursor.getBlob(0);
            if (value != null) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(value));
                    try {
                        services = (ArrayList<Service>) ois.readObject();
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                } catch (IOException e) {
                    return null;
                }
            }
        }
        cursor.close();
        db.close();
        return services;
    }


    public ArrayList<Availability> getAvailabilities(String username) {
        ArrayList<Availability> Availabilities = new ArrayList<>();
        ArrayList<Availability> tmp;


        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT " + COLUMN_AVAILABILITIES + " FROM " + SERVICE_PROVIDERS +
                " WHERE " + COLUMN_USERNAME
                + " = \"" + username + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            byte[] value = cursor.getBlob(0);
            if (value != null) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(value));
                    try {
                        Availabilities = (ArrayList<Availability>) ois.readObject();
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                } catch (IOException e) {
                    return null;
                }
            }
        }
        cursor.close();
        db.close();
        return Availabilities;
    }


    public boolean addAvailbility(String username, Availability availability, Context c) {
        ArrayList<Availability> availabilities = this.getAvailabilities(username);
        availabilities.add(availability);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try{

            // Serialize data object to a file
            File f = new File( c.getFilesDir(), "MyAvail.ser" );

            f.createNewFile();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f, true));

            out.writeObject(availabilities);
            out.close();

            // Serialize data object to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
            out = new ObjectOutputStream(bos) ;
            out.writeObject(availabilities);
            out.close();

            // Get the bytes of the serialized object
            byte[] data = bos.toByteArray();
            values.put(COLUMN_AVAILABILITIES, data);

            db.update(SERVICE_PROVIDERS, values, COLUMN_USERNAME + "=?", new String[]{username});
        } catch (IOException e) {
            return false;
        }

        db.close();
        return true;
    }


    public boolean addService(String username, Service service, Context c) {
        ArrayList<Service> services = this.getServices(username);
        services.add(service);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        try{

            // Serialize data object to a file
            File f = new File( c.getFilesDir(), "MyServices.ser" );

            f.createNewFile();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f, true));

            out.writeObject(services);
            out.close();

            // Serialize data object to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
            out = new ObjectOutputStream(bos) ;
            out.writeObject(services);
            out.close();

            // Get the bytes of the serialized object
            byte[] data = bos.toByteArray();
            values.put(COLUMN_SERVICES, data);

            db.update(SERVICE_PROVIDERS, values, COLUMN_USERNAME + "=?", new String[]{username});
        } catch (IOException e) {
            return false;
        }

        db.close();
        return true;
    }

    public boolean removeService(String username, Service service) {
        ArrayList<Service> services = this.getServices(username);
        boolean val = false;
        for (int i = 0; i < services.size(); i ++){
            if (services.get(i).getServiceName().equals(service.getServiceName()) && services.get(i).getHourlyRate() == service.getHourlyRate() && !val){
                services.remove(i);
                val = true;
            }
        }


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        byte[] data = SerializationUtils.serialize(services);

        values.put(COLUMN_SERVICES, data);

        db.update(SERVICE_PROVIDERS, values, COLUMN_USERNAME + "=?", new String[]{username});

        db.close();

        return val;
    }


}
