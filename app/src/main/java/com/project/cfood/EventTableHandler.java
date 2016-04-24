package com.project.cfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Austin Holler on 4/23/2016.
 */
public class EventTableHandler {

    private DBCreator dbCreator;

    public EventTableHandler(Context context){
        dbCreator = new DBCreator(context);
    }

    public int insertEvent(EventClass event) {

        SQLiteDatabase db = dbCreator.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBCreator.KEY_EVENTID, event.getEventID());
        values.put(DBCreator.KEY_TITLE, event.getTitle());
        values.put(DBCreator.KEY_DESCR, event.getDescr());
        values.put(DBCreator.KEY_ADDRESS, event.getAddress());
        values.put(DBCreator.KEY_TIME, event.getTime());
        values.put(DBCreator.KEY_EVENTUSER, event.getUserID());

        // Inserting Row
        long event_Id = db.insert(DBCreator.TABLE_EVENTS, null, values);
        db.close(); // Closing database connection
        return (int) event_Id;
    }

    public void delete(int event_Id) {

        SQLiteDatabase db = dbCreator.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(DBCreator.TABLE_EVENTS, DBCreator.KEY_EVENTID + "= ?", new String[] { String.valueOf(event_Id) });
        db.close(); // Closing database connection
    }

    public void update(EventClass event) {

        SQLiteDatabase db = dbCreator.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBCreator.KEY_TITLE, event.getTitle());
        values.put(DBCreator.KEY_DESCR, event.getDescr());
        values.put(DBCreator.KEY_ADDRESS, event.getAddress());
        values.put(DBCreator.KEY_TIME, event.getTime());

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(DBCreator.TABLE_EVENTS, values, DBCreator.KEY_EVENTID + "= ?", new String[] { String.valueOf(event.getEventID()) });
        db.close(); // Closing database connection
    }

    /* Don't know if we'll actually ever need this, it returns a list of events,
       but I figured I'd add it in because it could eventually be useful

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbCreator.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DBCreator.KEY_EVENTID + "," +
                DBCreator.KEY_TITLE + "," +
                DBCreator.KEY_DESCR + "," +
                DBCreator.KEY_ADDRESS + "," +
                DBCreator.KEY_TIME + "," +
                DBCreator.KEY_EVENTUSER +
                " FROM " + DBCreator.TABLE_EVENTS;

        //EventClass event = new EventClass();
        ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> event = new HashMap<String, String>();
                event.put("id", cursor.getString(cursor.getColumnIndex(DBCreator.KEY_EVENTID)));
                event.put("name", cursor.getString(cursor.getColumnIndex(DBCreator.KEY_TITLE)));
                eventList.add(event);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventList;

    } */

    public EventClass getEventById(int Id){
        SQLiteDatabase db = dbCreator.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                DBCreator.KEY_EVENTID + "," +
                DBCreator.KEY_TITLE + "," +
                DBCreator.KEY_DESCR + "," +
                DBCreator.KEY_ADDRESS + "," +
                DBCreator.KEY_TIME +
                DBCreator.KEY_EVENTUSER +
                " FROM " + DBCreator.TABLE_EVENTS +
                " WHERE " + DBCreator.KEY_EVENTID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        EventClass event = new EventClass();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                event.setEventID(cursor.getInt(cursor.getColumnIndex(DBCreator.KEY_EVENTID)));
                event.setTitle(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_TITLE)));
                event.setDescr(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_DESCR)));
                event.setAddress(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_ADDRESS)));
                event.setTime(cursor.getString(cursor.getColumnIndex(DBCreator.KEY_TIME)));
                event.setUserID(cursor.getInt(cursor.getColumnIndex(DBCreator.KEY_EVENTUSER)));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return event;
    }

}