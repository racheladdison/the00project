package com.project.cfood;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;

import android.os.Bundle;
import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Forum extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView forumListView ;
    private ArrayAdapter<String> listAdapter ;
    private DBCreator dbCreator;
    private TextView mTitle;
    private EventTableHandler eventTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        setContentView(R.layout.activity_forum);
        //Declare Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //Set the use of custom toolbar
        setSupportActionBar(myToolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        eventTable = new EventTableHandler();
        mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);

        createSamples();

        forumListView = (ListView) findViewById( R.id.forumListView);
        //TODO replace this with a database call that creates a list of event objects

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, getEventList(eventTable));

        // Set each of the the ArrayAdapters as the ListView's adapter
        forumListView.setAdapter( listAdapter );


        //Nav bar stuff
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_forum_view) {
            toForum();
        } else if (id == R.id.nav_map_view) {
            //toMap(view);
        } else if (id == R.id.nav_profile) {
            toProfile();
        } else if (id == R.id.nav_sign_out) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public List<String> getEventList(EventTableHandler eventTable) {
        List<String> eventList = new ArrayList<String>();
        List<EventClass> tempList = eventTable.getEventList();
        for (int i = 0; i<tempList.size(); i++) {
            eventList.add(tempList.get(i).getTitle());
        }
        return eventList;
    }

    public void toForum() {
        Intent intent = new Intent(this, Forum.class);
        startActivity(intent);
    }

    /*public void toMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }*/

    public void toProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void createSamples() {

        EventTableHandler eventTable = new EventTableHandler();
        EventClass event = new EventClass();

        eventTable.deleteTable();

        event.setEventID("1a85shyx");
        event.setTitle("Jesus & Chips @ 4pm UMC");
        event.setDescr("Come hang out with our main man Jesus today at the UCB on campus! " +
                "You know what's up, there will be plenty of fish and chips because our man provides.");
        event.setAddress("University Memorial Center, Boulder, CO 80302");
        event.setTime("April 27, 2016");
        event.setUserID("7shensy675walmc24");
        event.setReportedCount(0);
        event.setActive(1);
        event.setUpVotes(1);

        eventTable.insertEvent(event);

        event.setEventID("37shfoe3");
        event.setTitle("Lucifer & Crucifer @ 4pm Engineering Center");
        event.setDescr("Lets be honest, Lucifer her and I'll be serving up mad Cruciferous veges today! " +
                " Why would you go hang out with my lame brother when I'm dishing it out w/mad sin on the side?!" +
                " You know what to do, and if you don't we'll be hanging out soon enough anyway.");
        event.setAddress("Engineering Center, Engineering Drive, Boulder, CO");
        event.setTime("April 27, 2016");
        event.setUserID("7shensy666walmc24");
        event.setReportedCount(0);
        event.setActive(1);
        event.setUpVotes(1);

        eventTable.insertEvent(event);

        event.setEventID("ax874yte");
        event.setTitle("Barney & Friends @ 4:05pm Imig Music");
        event.setDescr("Hands down this is the event you've been waiting for! Some other event to go to?" +
                        " Skip it, you won't ever get this chance again! Who could really be better...?");
        event.setTime("April 27, 2016");
        event.setUserID("jd9hdfa47923jsdfh");
        event.setReportedCount(0);
        event.setActive(1);
        event.setUpVotes(1);

        eventTable.insertEvent(event);
    }
/*
    public void toSignOut(View view) {
        Intent intent = new Intent(this, SignOut.class);
        startActivity(intent);

    }
    */

}
