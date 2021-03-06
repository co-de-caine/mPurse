package com.codecaine.mpurse.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codecaine.mpurse.entity.DBConstants;

import java.util.HashMap;

/**
 * This class includes all the SQLite Database connection and functions
 *
 * @author Deepankar
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables contained in DBConstants class

    private Context context;

    public SQLiteHandler(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConstants.CREATE_USER_TABLE);
        db.execSQL(DBConstants.CREATE_REQUEST_TABLE);
        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_REQUEST);
        // Create tables again
        onCreate(db);
    }

    public void updateUser(String uuid, String name, long phoneno, String email, double wallet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_UUID, uuid);
        values.put(DBConstants.KEY_NAME, name);
        values.put(DBConstants.KEY_EMAIL, email);
        values.put(DBConstants.KEY_PHONE, phoneno);
        values.put(DBConstants.KEY_WALLET, wallet);
        values.put(DBConstants.KEY_UPDATEFLAG, 0);
        // Updating Row
        long id = db.update(DBConstants.TABLE_USER, values, null, null);
        db.close(); // Closing database connection
        Log.d(TAG, "User details updated into sqlite: " + id);
    }

    public void addUser(String uuid, String name, long phoneno, String email, double wallet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_UUID, uuid);
        values.put(DBConstants.KEY_NAME, name);
        values.put(DBConstants.KEY_EMAIL, email);
        values.put(DBConstants.KEY_PHONE, phoneno);
        values.put(DBConstants.KEY_WALLET, wallet);
        // Inserting Row
        long id = db.insert(DBConstants.TABLE_USER, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "New user inserted into sqlite: " + id);
    }

    /**
     * Getting user data from database
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put(DBConstants.KEY_UUID, cursor.getString(0));
            user.put(DBConstants.KEY_NAME, cursor.getString(1));
            user.put(DBConstants.KEY_EMAIL, cursor.getString(2));
            user.put(DBConstants.KEY_PHONE, cursor.getString(3));
            user.put(DBConstants.KEY_WALLET, cursor.getString((4)));
            user.put(DBConstants.KEY_UPDATEFLAG, cursor.getString(((5))));
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());
        return user;
    }

    /**
     * Re-create database Delete all tables \
     */
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(DBConstants.TABLE_USER, null, null);
        db.close();
        Log.d(TAG, "Deleted all user info from sqlite");
    }

    public void addAmountToWallet(double wallet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_WALLET, wallet);

        //TODO Update flag value
        //values.put(DBConstants.KEY_UPDATEFLAG, 1);

        // Updating Row
        long id = db.update(DBConstants.TABLE_USER, values, null, null);
        db.close(); // Closing database connection
        Log.d(TAG, "Wallet Amount updated into sqlite: " + id);

    }

    public void addRequest(String refid, String recvrphone, String amountReq, int amountFulf, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBConstants.KEY_REFID, refid);
        values.put(DBConstants.KEY_RECVPHONE, recvrphone);
        values.put(DBConstants.KEY_AMTREQ, amountReq);
        values.put(DBConstants.KEY_AMTFULF, amountFulf);
        values.put(DBConstants.KEY_STATUS, status);
        // Inserting Row
        long id = db.insert(DBConstants.TABLE_REQUEST, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "New request inserted into sqlite: " + id);
    }
}
