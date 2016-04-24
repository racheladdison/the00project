package com.project.cfood;

/**
 * Created by Andrew Candelaresi on 4/21/2016.
 */
public class Event {
    private int userID;
    private String name;
    private String description;
    private String location;
    private String time;
    private String date;
    private int reportedCount;
    private boolean active;
    protected int upVotes;

    public Event(int userID, String name, String description, String location, String time, String date) {
        this.userID = userID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.time = time;
        this.date = date;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setReportedCount(int reportedCount) {
        this.reportedCount = reportedCount;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public int getReportedCount() {
        return reportedCount;
    }

    public boolean isActive() {
        return active;
    }

    public int getUpVotes() {
        return upVotes;
    }
    public int increaseUpvote() {
        this.getUpVotes();
        upVotes++;
        return upVotes;

    }
    public int increaseReportCount() {
        this.getReportedCount();
        reportedCount++;
        return reportedCount;

    }

}
