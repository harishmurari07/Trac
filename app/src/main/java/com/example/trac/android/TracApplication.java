package com.example.trac.android;

import android.app.Application;

import com.example.trac.preferences.PreferenceManager;

public class TracApplication extends Application {

    private static TracApplication context;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.initialize(this);
        context = this;
    }

    public static TracApplication getContext() {
        return context;
    }

}