package com.project.cfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import java.util.ArrayList;


public class ProfileActivity extends AppCompatActivity{
    private ListView profileListView ;
    private ArrayAdapter<String> listAdapter ;
    private TextView profileEmailView;
    private TextView profileUsernameView;
    private DBCreator dbCreator;
    private ImageView profileImageView;
    private Button button;
    private GoogleSignInResult result;
    private GoogleSignInAccount acct;
    private GoogleApiClient mGoogleAPIClient;
    private EventTableHandler eventTable;
    private UserTableHandler userTable;
    private UserClass user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Load Username, Email, Name from SQL database
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();

        profileListView = (ListView) findViewById( R.id.forumListView);
        profileEmailView = (TextView) findViewById( R.id.email);
        profileUsernameView = (TextView) findViewById( R.id.username);
        profileImageView = (ImageView) findViewById( R.id.userphoto);
        button = (Button) findViewById( R.id.edit_profile);
        userTable = new UserTableHandler();
        eventTable = new EventTableHandler();

        GoogleApiClient mGoogleAPIClient = LoginActivity.getApiClient();
        GoogleSignInAccount acct = LoginActivity.getSignInResult().getSignInAccount();

        user = userTable.getUserById(acct.getIdToken());

        // Create ArrayAdapter using the event list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, getEventList(user, eventTable));

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
    }

    public ArrayList<String> getEventList(UserClass user, EventTableHandler eventTable) {
        ArrayList<String> eventList = new ArrayList<String>();
        for (int i = 0; i<user.getArrayListEvents().size(); i++) {
            eventList.add(eventTable.getEventById(user.getArrayListEvents().get(i)).getTitle());
        }
        return eventList;
    }
}
