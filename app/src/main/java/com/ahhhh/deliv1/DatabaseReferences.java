package com.ahhhh.deliv1;

//for entire database
public final class DatabaseReferences {


    private DatabaseReferences() {
        //to prevent us from accidentally creating an instance of this class
    }

    public static class ReferenceCodes {
        public static final String TABLE_NAME = "ReferenceCodes";
        public static final String CODE = "Code"; //primary key for table
        public static final String CODE_TYPE = "CodeType";
        public static final String DESCRIPTION = "Description";

    }
}
