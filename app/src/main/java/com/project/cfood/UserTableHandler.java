package com.project.cfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Austin Holler on 4/23/2016.
 */
public class UserTableHandler {

    private DBCreator dbCreator;

    public UserTableHandler(Context context){
        dbCreator = new DBCreator(context);
    }

    public int insertUser(UserClass user) {

        SQLiteDatabase db = dbCreator.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBCreator.KEY_USERUSER, user.getUserID());
        values.put(DBCreator.KEY_NAME, user.getName());
        values.put(DBCreator.KEY_EMAIL, user.getEmail());
        values.put(DBCreator.KEY_LOCATION, user.getLocation());
        values.put(DBCreator.KEY_MYEVENTID, user.getStringEvents());

        // Inserting Row
        long user_Id = db.insert(DBCreator.TABLE_USERS, null, values);
        db.close(); // Closing database connection
        return (int) user_Id;
    }

    public void delete(String user_Id) {

        SQLiteDatabase db = dbCreator.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(DBCreator.TABLE_USERS, DBCreator.KEY_USERUSER + "= ?", new String[] { String.valueOf(user_Id) });
        db.close(); // Closing database connection
    }

    public void update(UserClass user) {

        SQLiteDatabase db = dbCreator.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBCreator.KEY_NAME, user.getName());
        values.put(DBCreator.KEY_EMAIL, user.getEmail());
        values.put(DBCreator.KEY_LOCATION, user.getLocation());
        values.put(DBCreator.KEY_MYEVENTID, user.getStringEvents());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(DBCreator.TABLE_USERS, values, DBCreator.KEY_USERUSER + "= ?", new String[] { String.valueOf(user.getUserID()) });
        db.close(); // Closing database connection
    }

    /* Don't know if we'll actually ever need this, it returns a list of users,
       but I figured I'd add it in because it could eventually be useful

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbCreator.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DBCreator.KEY_NAME + "," +
                DBCreator.KEY_EMAIL + "," +
                DBCreator.KEY_LOCATION + "," +
                DBCreator.KEY_MYEVENTS +
                " FROM " + DBCreator.TABLE_USERS;

        //UserClass user = new UserClass();
        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(DBCreator.KEY_USERUSER)));
                user.put("name", cursor.getString(cursor.getColumnIndex(DBCreator.KEY_NAME)));
                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;

    } */

    public UserClass getUserById(String Id){
        SQLiteDatabase db = dbCreator.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DBCreator.KEY_NAME + "," +
                DBCreator.KEY_EMAIL + "," +
                DBCreator.KEY_LOCATION + "," +
                DBCreator.KEY_MYEVENTID +
                " FROM " + DBCreator.TABLE_USERS +
                " WHERE " + DBCreator.KEY_USERUSER + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        UserClass user = new UserClass();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                user.setUserID(cursor.getInt(cursor.getColumnIndex(DBCreator.KEY_USERUSER)));
                user.setName(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_EMAIL)));
                user.setLocation(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_LOCATION)));

                //Parse myEvents to an ArrayList before inserting it into the User Class
                String s = cursor.getString(cursor.getColumnIndex(DBCreator.KEY_MYEVENTID));
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));
                user.setMyEvents(myList);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }

}
