package com.project.cfood;

/**
 * Created by Leif Waldner on 4/21/16.
 * Edited by Austin Holler on 4/23/16.
 */

//TODO make userclass private
public class UserClass {
    private int userID;
    private String name;
    private String email;
    private String location;
    private String myEvents;
    private Integer reputation;

    public UserClass() {}

    public UserClass(int userID, String name, String description, String email, String location,String myEvents) {
        this.userID = userID;
        this.name = name;
        this.location = location;
        this.email = email;
        this.myEvents = myEvents;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMyEvents(String myEvents) {
        this.myEvents = myEvents;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
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

    public String getMyEvents() { return myEvents; }

    public Integer getReputation() {
        return reputation;
    }
}