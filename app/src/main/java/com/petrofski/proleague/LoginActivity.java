package com.petrofski.proleague;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Layout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private Activity thisActivity;
    private TextInputLayout mailLayout;
    private EditText mail;
    private ProgressBar spinner;
    private View scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        thisActivity = this;

        spinner = (ProgressBar) findViewById(R.id.progressBar);
        scrollView = (View) findViewById(R.id.scrollView);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            Intent overview = new Intent(thisActivity, TabbedActivity.class);
            thisActivity.finish();
            startActivity(overview);
        }

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mailLayout = (TextInputLayout) findViewById(R.id.input_email_layout);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void goToRegister(View view) {
        Intent reg = new Intent(this, RegisterActivity.class);
        thisActivity.finish();
        startActivity(reg);
    }

    public void login(View view) {

        spinner.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        Intent overview = new Intent(thisActivity, TabbedActivity.class);
        thisActivity.finish();
        startActivity(overview);

        spinner.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        /*
        ParseUser.logInInBackground(mEmailView.getText().toString(), mPasswordView.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent overview = new Intent(thisActivity, TabbedActivity.class);
                    thisActivity.finish();
                    startActivity(overview);
                } else {
                    mailLayout.setErrorEnabled(true);
                    mailLayout.setError(e.getMessage());
                    mEmailView.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
                }
                spinner.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        });
        */
    }
}

