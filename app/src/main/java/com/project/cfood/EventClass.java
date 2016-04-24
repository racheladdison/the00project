package com.project.cfood;

/**
 * Created by Austin Holler on 4/23/2016.
 */
public class EventClass {
    private int eventID;
    private String title;
    private String descr;
    private String address;
    private String time;
    private int userID;

    public EventClass() {};

    public EventClass(int eventID, String title, String descr, String address, String time, int userID) {
        this.eventID = eventID;
        this.title = title;
        this.descr = descr;
        this.address = address;
        this.time = time;
        this.userID = userID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getEventID() {
        return eventID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescr() {
        return descr;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public int getUserID() {
        return userID;
    }
}
