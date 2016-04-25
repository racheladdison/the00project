package com.project.cfood;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Forum extends AppCompatActivity {
    private ListView forumListView ;
    private ArrayAdapter<String> listAdapter ;
    private DBCreator dbCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase db = dbCreator.getReadableDatabase();

        setContentView(R.layout.activity_forum);
        //Declare Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //Set the use of custom toolbar
        setSupportActionBar(myToolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);

        forumListView = (ExpandableListView) findViewById( R.id.forumListView);
        //TODO replace this with a database call that creates a list of event objects
        //String[] events = new Event()
        ArrayList<String> EventList = new ArrayList<String>();
        //EventList.addAll( Arrays.asList(/*replace with eventList*/) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, EventList);

        // Set each of the the ArrayAdapters as the ListView's adapter
        forumListView.setAdapter( listAdapter );
    }

}
