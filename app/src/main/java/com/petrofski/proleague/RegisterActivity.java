package com.petrofski.proleague;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {


    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Activity thisActivity;
    private TextInputLayout mailLayout;
    private EditText mail;
    private ProgressBar spinner;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        thisActivity = this;

        spinner = (ProgressBar) findViewById(R.id.progressBar2);
        scrollView = (ScrollView) findViewById(R.id.scrollView2);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mailLayout = (TextInputLayout) findViewById(R.id.input_email_layout);
        mail = (EditText) findViewById(R.id.email);

        AdView mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void goToLogin(View view) {
        Intent login = new Intent(this, LoginActivity.class);
        thisActivity.finish();
        startActivity(login);
    }

    public void register(View view) {

        spinner.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        Intent overview = new Intent(thisActivity, TabbedActivity.class);
        thisActivity.finish();
        startActivity(overview);

        spinner.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        /*
        ParseUser user = new ParseUser();
        user.setUsername(mEmailView.getText().toString());
        user.setEmail(mEmailView.getText().toString());
        user.setPassword(mPasswordView.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    ParseUser.logInInBackground(mEmailView.getText().toString(), mPasswordView.getText().toString(), new LogInCallback() {
                        public void done(ParseUser user, ParseException e) {
                            if (user != null) {
                                Intent overview = new Intent(thisActivity, TabbedActivity.class);
                                thisActivity.finish();
                                startActivity(overview);
                            } else {
                                mailLayout.setErrorEnabled(true);
                                mailLayout.setError(e.getMessage());
                                mail.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
                            }
                        }
                    });
                } else {
                    mailLayout.setErrorEnabled(true);
                    mailLayout.setError(e.getMessage());
                    mail.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.DARKEN);
                }
            }
        });

        spinner.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        */

    }


}

