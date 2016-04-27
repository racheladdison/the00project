package com.project.cfood;

import java.util.ArrayList;
import java.util.List;
import com.project.cfood.EventClass;
/**
 * Created by Andrew Candelaresi on 4/24/2016.
 */
public class MyeventsPage{
    List<EventClass> myEvents = new ArrayList<EventClass>();
    List<EventClass> favoriteEvents = new ArrayList<EventClass>();
    private int pageID;
    public  MyeventsPage(int userID)
    {
        this.pageID = userID;

    }
    public void createEvent(String EventID, String userID, String name, String description, String location, String time) {
        EventClass food = new EventClass(EventID, name, description, location, time, userID);
        myEvents.add(food);
    }
    public void addToFavorites(EventClass EventName){
        favoriteEvents.add(EventName);
    }
    public void editEvent(EventClass EventName, String name, String description, String location, String time){
        EventName.setTitle(name);
        EventName.setDescr(description);
        EventName.setAddress(location);
        EventName.setTime(time);

    }

}
