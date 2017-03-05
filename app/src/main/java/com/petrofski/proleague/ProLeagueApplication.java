package com.petrofski.proleague;

import android.app.Application;

import com.firebase.client.Firebase;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by georgespetrofski on 26/11/15.
 */
public class ProLeagueApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);

        Parse.initialize(this, "", "");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}
