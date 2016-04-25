package com.project.cfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileActivity extends AppCompatActivity {
    private ListView profileListView ;
    private ArrayAdapter<String> listAdapter ;
    private TextView profileEmailView;
    private TextView profileUsernameView;
    private ImageView profileImageView;
    private DBCreator dbCreator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Load Username, Email, Name from SQL database
        SQLiteDatabase db = dbCreator.getReadableDatabase();

        profileListView = (ExpandableListView) findViewById( R.id.forumListView);
        profileEmailView = (TextView) findViewById( R.id.email);
        profileUsernameView = (TextView) findViewById( R.id.username);
        profileImageView = (ImageView) findViewById( R.id.userphoto);

        //TODO replace this with a database call that creates a list of event objects
        //String[] events = new Event()
        ArrayList<String> EventList = new ArrayList<String>();
        //EventList.addAll( Arrays.asList(/*replace with eventList*/) );

        // Create ArrayAdapter usig the event list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, EventList);

        // Set each of the the ArrayAdapters as the ListView's adapter
        profileListView.setAdapter( listAdapter );
        //profileEmailView.setText();
        //profileUsernameView.setText();
        //profileImageView.setImage();


    }
    public void EditProfile() {
        //Edit Profile
        //set on click listener for button? Or is that coded into the button itself?
        //.setOnClickListener((view -> {})
        //isEmailValid?
        //Change Password?
        //Set view to edit profile forum? -> same as login?

    }

    public void EditEvents() {
        //Edit Events

    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return ((password.length() > 4));
    }

    public void toRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void toForum(View view) {
        Intent intent = new Intent(this, Forum.class);
        startActivity(intent);
    }

}
