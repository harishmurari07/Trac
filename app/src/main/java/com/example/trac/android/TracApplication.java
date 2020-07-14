package com.example.trac.android;

import android.app.Application;
import android.content.Context;

import com.example.trac.preferences.PreferenceManager;

public class TracApplication extends Application {

//    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceManager.initialize(this);
//        context = getApplicationContext();
    }

//    public static Context getContext() {
//        return context;
//    }

}