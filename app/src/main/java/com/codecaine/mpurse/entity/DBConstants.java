package com.codecaine.mpurse.entity;

/**
 * Created by Deepankar on 12-01-2016.
 */
public class DBConstants {
    // Database Version
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "mPurse";

    // Table names
    public static final String TABLE_USER = "user";
    public static final String TABLE_REQUEST = "request";

    // Users Table Columns names
    public static final String KEY_UUID = "uuid";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phoneno";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_WALLET = "wallet";
    public static final String KEY_UPDATEFLAG = "update_flag";

    // Requests Table Columns names
    public static final String KEY_REFID = "refid";
    public static final String KEY_RECVPHONE = "recvphone";
    public static final String KEY_AMTREQ = "amtreq";
    public static final String KEY_AMTFULF = "amtfulf";
    public static final String KEY_STATUS = "status";


    // Queries for creation of user table
    public static final String CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_USER + "("
                    + KEY_UUID + " TEXT PRIMARY KEY NOT NULL,"
                    + KEY_NAME + " TEXT NOT NULL,"
                    + KEY_EMAIL + " TEXT NOT NULL,"
                    + KEY_PHONE + " INTEGER NOT NULL, "
                    + KEY_WALLET + " REAL NOT NULL, "
                    + KEY_UPDATEFLAG + "NUMERIC DEFAULT 0 "
                    + ")";

    public static final String CREATE_REQUEST_TABLE =
            "CREATE TABLE " + TABLE_REQUEST + "("
                    + KEY_REFID + " TEXT PRIMARY KEY NOT NULL,"
                    + KEY_UUID + " TEXT NOT NULL,"
                    + KEY_RECVPHONE + " TEXT NOT NULL,"
                    + KEY_AMTREQ + " REAL NOT NULL, "
                    + KEY_AMTFULF + " REAL NOT NULL, "
                    + KEY_STATUS + "TEXT NOT NULL "
                    + ")";

}
