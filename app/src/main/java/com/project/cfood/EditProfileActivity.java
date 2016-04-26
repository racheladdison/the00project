package com.project.cfood;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

public class EditProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private Button button;
    private EditText mEmailView;
    private EditText mUserNameView;
    private UserTableHandler userTable;
    private GoogleApiClient mGoogleAPIClient;
    private GoogleSignInAccount acct;
    private UserClass user;
    private GoogleSignInResult result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        button = (Button) findViewById( R.id.confirm_button);
        mEmailView = (EditText) findViewById( R.id.email);
        mUserNameView = (EditText) findViewById( R.id.username);
        userTable = new UserTableHandler(this);

        //Access User
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();

        mGoogleAPIClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signIn();

        user = userTable.getUserById(acct.getIdToken());
        attemptEditProfile(user, userTable);

        //TODO check to make sure the edit's are legal before changing back to profile
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptEditProfile(user, userTable);
            }
        });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleAPIClient);
        startActivityForResult(signInIntent, 9001);
    }

    private void attemptEditProfile(UserClass user, UserTableHandler userTable) {

        // Reset errors.
        mEmailView.setError(null);
        mUserNameView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String username = mUserNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(username) || !isUsernameValid(username)) {
            mUserNameView.setError(getString(R.string.error_invalid_username));
            focusView = mUserNameView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email) || !isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            user.setEmail(email);
            user.setName(username);
            userTable.update(user);
            startActivity(new Intent(EditProfileActivity.this, ProfileActivity.class));
        }
    }

    private boolean isUsernameValid(String username) {
        return (username.length() > 6);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 9001) {
            result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            acct = result.getSignInAccount();
        }
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}
}
