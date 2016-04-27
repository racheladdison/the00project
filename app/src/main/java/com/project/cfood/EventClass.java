package com.project.cfood;

/**
 * Created by Andrew Candelaresi on 4/21/2016.
 */
public class EventClass {
    private String eventID;
    private String title;
    private String descr;
    private String address;
    private String time;
    private String userID;
    private int reportedCount;
    private int active;
    protected int upVotes;

    public EventClass() {};

    public EventClass(String eventID, String title, String descr, String address, String time, String userID) {
        this.eventID = eventID;
        this.title = title;
        this.descr = descr;
        this.address = address;
        this.time = time;
        this.userID = userID;
    }

    public void setEventID(String eventID) {
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

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setReportedCount(int reportedCount) {
        this.reportedCount = reportedCount;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public String getEventID() {
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

    public String getUserID() {
        return userID;
    }

    public int getReportedCount() {
        return reportedCount;
    }

    public int isActive() {
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
