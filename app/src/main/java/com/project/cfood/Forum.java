package com.project.cfood;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Forum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        //Declare Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //Set the use of custom toolbar
        setSupportActionBar(myToolbar);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) myToolbar.findViewById(R.id.toolbar_title);
    }

}
