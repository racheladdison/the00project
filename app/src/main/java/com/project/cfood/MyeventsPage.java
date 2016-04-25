package com.project.cfood;

import java.util.ArrayList;
import java.util.List;
import com.project.cfood.Event;
/**
 * Created by Andrew Candelaresi on 4/24/2016.
 */
public class MyeventsPage{
    List<Event> myEvents = new ArrayList<Event>();
    List<Event> favoriteEvents = new ArrayList<Event>();
    private int pageID;
    public  MyeventsPage(int userID)
    {
        this.pageID = userID;

    }
    public void createEvent(int EventID,int pageID, int userID, String name, String description, String location, String time, String date) {
        Event food = new Event(EventID,pageID, userID, name, description, location, time, date);
        myEvents.add(food);
    }
    public void addToFavorites(Event EventName){
        favoriteEvents.add(EventName);
    }
    public void editEvent(Event EventName, String name, String description, String location, String time, String date){
        EventName.setName(name);
        EventName.setDescription(description);
        EventName.setLocation(location);
        EventName.setTime(time);
        EventName.setDate(date);

    }

}
