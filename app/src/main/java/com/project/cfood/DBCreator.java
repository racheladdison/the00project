package com.project.cfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Austin Holler on 4/23/2016.
 */
public class DBCreator extends SQLiteOpenHelper {

    // Database Version
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "cfoodInfo";

    // Users table name
    public static final String TABLE_USERS = "users";
    // Users Table Columns names
    public static final String KEY_USERUSER = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_MYEVENTS = "myEvents";

    // Events table name
    public static final String TABLE_EVENTS = "events";
    // Events Table Columns names
    public static final String KEY_EVENTID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCR = "description";
    public static final String KEY_ADDRESS = "location";
    public static final String KEY_TIME = "time";
    public static final String KEY_EVENTUSER = "user";
    public static final String KEY_REPORTED = "reported";
    public static final String KEY_ACTIVE = "active";
    public static final String KEY_UPVOTES = "upvotes";

    public DBCreator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USERUSER + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + "TEXT" + KEY_LOCATION + " TEXT" + KEY_MYEVENTS
                + "TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_EVENTID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_DESCR + "TEXT" + KEY_ADDRESS + " TEXT" + KEY_TIME
                + "TEXT" + KEY_EVENTUSER + "INTEGER" + KEY_REPORTED + "INTEGER"
                + KEY_ACTIVE + "INTEGER" + KEY_UPVOTES + "INTEGER" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older tableS if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        // Creating tables again
        onCreate(db);
    }
}
