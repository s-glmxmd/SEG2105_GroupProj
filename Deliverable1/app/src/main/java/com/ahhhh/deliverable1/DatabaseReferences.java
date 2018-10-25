package com.ahhhh.deliverable1;

//for entire database
public final class DatabaseReferences {
    protected static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "ahhhhDB.db";

    private DatabaseReferences() {
        //to prevent us from accidentally creating an instance of this class
    }

    //create one per table
    public static class UserCredentials {
        public static final String TABLE_NAME = "UserCredentials";
        public static final String COLUMN_PRIMARY_KEY = "id";
        public static final String COLUMN_USERNAME = "Username";
        public static final String COLUMN_PASSWORD = "Password";

    }

    public static class UserAccounts {
        public static final String TABLE_NAME = "UserAccounts";
        public static final String ACCOUNT_TYPE = "AccountType";
        public static final String EMAIL_ADDRESS = "EmailAddress";
        public static final String PHONE_NUMBER = "PhoneNumber";
        public static final String FIRST_NAME = "FirstName";
        public static final String LAST_NAME = "LastName";
        public static final String COLUMN_PRIMARY_KEY  = "id";

    }

    public static class ReferenceCodes {
        public static final String TABLE_NAME = "ReferenceCodes";
        public static final String CODE = "Code"; //primary key for table
        public static final String CODE_TYPE = "CodeType";
        public static final String DESCRIPTION = "Description";

    }
}
