package com.project.cfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Austin Holler on 4/23/2016.
 */
public class UserTableHandler {

    private UserClass user;

    public UserTableHandler(){
        user = new UserClass();
    }

    public int insertUser(UserClass user) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(DBCreator.KEY_USERUSER, user.getUserID());
        values.put(DBCreator.KEY_NAME, user.getName());
        values.put(DBCreator.KEY_EMAIL, user.getEmail());
        values.put(DBCreator.KEY_LOCATION, user.getLocation());
        values.put(DBCreator.KEY_MYEVENTID, user.getStringEvents());

        // Inserting Row
        long user_Id = db.insert(DBCreator.TABLE_USERS, null, values);
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
        return (int) user_Id;
    }

    public void delete(int user_Id) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(DBCreator.TABLE_USERS, DBCreator.KEY_USERUSER + "= ?", new String[] { String.valueOf(user_Id) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    public void update(UserClass user) {

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(DBCreator.KEY_NAME, user.getName());
        values.put(DBCreator.KEY_EMAIL, user.getEmail());
        values.put(DBCreator.KEY_LOCATION, user.getLocation());
        values.put(DBCreator.KEY_MYEVENTID, user.getStringEvents());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(DBCreator.TABLE_USERS, values, DBCreator.KEY_USERUSER + "= ?", new String[] { String.valueOf(user.getUserID()) });
        DatabaseManager.getInstance().closeDatabase(); // Closing database connection
    }

    /* Don't know if we'll actually ever need this, it returns a list of users,
       but I figured I'd add it in because it could eventually be useful */

    public List<UserClass> getUserList() {
        //Open connection to read only
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery =  "SELECT  " +
                DBCreator.KEY_NAME + "," +
                DBCreator.KEY_EMAIL + "," +
                DBCreator.KEY_LOCATION + "," +
                DBCreator.KEY_MYEVENTID +
                " FROM " + DBCreator.TABLE_USERS;

        UserClass user = new UserClass();
       List<UserClass> userList = new ArrayList<>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                user = new UserClass();
                user.setName(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_EMAIL)));
                user.setLocation(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_LOCATION)));

                String s = cursor.getString(cursor.getColumnIndex(DBCreator.KEY_MYEVENTID));
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));
                user.setMyEvents(myList);

                userList.add(user);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return userList;

    }

    public UserClass getUserById(String Id){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
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
                user.setUserID(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_USERUSER)));
                user.setName(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_EMAIL)));
                user.setLocation(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_LOCATION)));

                String s = cursor.getString(cursor.getColumnIndex(DBCreator.KEY_MYEVENTID));
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));
                user.setMyEvents(myList);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return user;
    }

}
