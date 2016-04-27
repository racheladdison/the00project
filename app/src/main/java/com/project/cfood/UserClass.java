package com.project.cfood;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;
/**
 * Created by Leif Waldner on 4/21/16.
 * Edited by Austin Holler on 4/23/16.
 */

//TODO make userclass private
public class UserClass {
    private String userID;
    private String name;
    private String email;
    private String location;
    private ArrayList<String> myEvents;
    private Integer reputation;
    public UserClass() {}

    public UserClass(String userID, String name, String email, String location, ArrayList<String> myEvents) {
        this.userID = userID;
        this.name = name;
        this.location = location;
        this.email = email;
        this.myEvents = myEvents;
    }

    public void setUserID(String userID) {
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

    public void setMyEvents(ArrayList<String> myEvents) { this.myEvents = myEvents; }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public String getUserID() {
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

    public ArrayList<String> getArrayListEvents() { return myEvents; }

    public String getStringEvents() {
        String joined = TextUtils.join(", ", this.myEvents);
        return joined;
    }

    public Integer getReputation() {
        return reputation;
    }
}