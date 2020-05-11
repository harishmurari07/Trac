package com.example.trac;

import android.app.Application;

public class TracApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.initialize(this);
    }
}
