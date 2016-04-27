package com.project.cfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.view.MenuItem;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.ViewDebug;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView profileListView ;
    private TextView profileEmailView;
    private TextView profileUsernameView;
    private ImageView profileImageView;
    private Button button;
    private GoogleSignInAccount acct;
    private GoogleApiClient mGoogleAPIClient;
    private EventTableHandler eventTable;
    private UserTableHandler userTable;
    private UserClass user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //Load Username, Email, Name from SQL database
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        profileListView = (ListView) findViewById( R.id.profileListView);
        profileEmailView = (TextView) findViewById( R.id.email);
        profileUsernameView = (TextView) findViewById( R.id.username);
        profileImageView = (ImageView) findViewById( R.id.userphoto);
        button = (Button) findViewById( R.id.edit_profile);
        userTable = new UserTableHandler();
        eventTable = new EventTableHandler();

        createSample();

        //GoogleApiClient mGoogleAPIClient = LoginActivity.getApiClient();
        //GoogleSignInAccount acct = LoginActivity.getSignInResult().getSignInAccount();

        user = userTable.getUserById("7shensy675walmc24");//acct.getIdToken());

        // Create ArrayAdapter using the event list.
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, getEventList(user, eventTable));


        // Set each of the the ArrayAdapters as the ListView's adapter
        profileListView.setAdapter( listAdapter );
        profileEmailView.setText(user.getEmail());
        profileUsernameView.setText(user.getUserID());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
            }
        });
        //profileImageView.setImageResource();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public ArrayList<String> getEventList(UserClass user, EventTableHandler eventTable) {
        ArrayList<String> eventList = new ArrayList<String>();

        List<EventClass> eventLister = new ArrayList<>();

        eventLister = eventTable.getEventList();

        for (int i = 0; i<eventLister.size(); i++) {

            Log.d("Event", eventLister.get(i).getTitle());
            eventList.add(eventLister.get(i).getTitle());
        }

        /*for (int i = 0; i<user.getArrayListEvents().size(); i++) {
            eventList.add(eventTable.getEventById(user.getArrayListEvents().get(i)).getTitle());
        }*/
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
    @Override
    public void onBackPressed() {
       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
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

    private void createSample() {
        UserTableHandler userTable = new UserTableHandler();
        UserClass user = new UserClass();

        userTable.deleteTable();

        user.setUserID("7shensy675walmc24");
        user.setName("Jesus");
        user.setLocation("Boulder, CO");
        user.setEmail("jesus_lives@aol.com");
        user.setReputation(777);

        ArrayList<String> myEventsArray = new ArrayList<>();
        myEventsArray.add("1a85shyx");
        myEventsArray.add("37shfoe3");
        myEventsArray.add("ax874yte");

        Log.d("myEventsArray Size", Integer.toString(myEventsArray.size()));

        user.setMyEvents(myEventsArray);

        userTable.insertUser(user);
    }

}
