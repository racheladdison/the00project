package com.project.cfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import android.database.Cursor;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
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
        button = (Button) findViewById( R.id.edit_profile);

        //Access google signin
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();

        mGoogleAPIClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signIn();

        Cursor res = db.rawQuery("SELECT * FROM users WHERE id="+acct.getIdToken()+"", null);

        // Create ArrayAdapter usig the event list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, getEventList(db, res));

        // Set each of the the ArrayAdapters as the ListView's adapter
        profileListView.setAdapter( listAdapter );
        profileEmailView.setText(res.getString(res.getColumnIndex("email")));
        profileUsernameView.setText(res.getString(res.getColumnIndex("name")));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
            }
        });
        //profileImageView.setImageResource();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleAPIClient);
        startActivityForResult(signInIntent, 9001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9001) {
            result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            acct = result.getSignInAccount();
        }
    }

    public ArrayList getEventList(SQLiteDatabase db, Cursor UserCursor) {
        ArrayList<HashMap<String, String>> eventList = new ArrayList<HashMap<String, String>>();
        Cursor EventCursor = db.rawQuery("SELECT * FROM events WHERE id="+UserCursor.getColumnIndex("myEvents")+"", null);
        if (EventCursor.moveToFirst()) {
            do {
                HashMap<String, String> events = new HashMap<String, String>();
                events.put("title", EventCursor.getString(EventCursor.getColumnIndex("title")));
                events.put("location", EventCursor.getString(EventCursor.getColumnIndex("title")));
                events.put("description", EventCursor.getString(EventCursor.getColumnIndex("description")));
                events.put("time", EventCursor.getString(EventCursor.getColumnIndex("time")));
                events.put("user", EventCursor.getString(EventCursor.getColumnIndex("user")));
                eventList.add(events);
            }
            while (EventCursor.moveToNext());
        }
        return eventList;
    }

    public void toForum(View view) {
        Intent intent = new Intent(this, Forum.class);
        startActivity(intent);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
