package com.project.cfood;

/**
 * Created by sarahwaldner on 4/21/16.
 */

//TODO make userclass private
public class UserClass {
    private int userID;
    private String name;
    private String email;
    private String location;

    public UserClass(int userID, String name, String description, String location, String time, String date) {
        this.userID = userID;
        this.name = name;
        this.location = location;
        this.email = email;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
    public String getEmail() {
        return email;
    }
}