package com.example.trac.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private static String MY_PREFERENCES = "mypreferences";
    private static String IS_FIRST_LAUNCH = "isFirstLaunch";

    private static SharedPreferences preferences;
    private static PreferenceManager instance;

    private PreferenceManager() {
    }

    public static PreferenceManager getInstance() {
        if (instance == null)
            instance = new PreferenceManager();
        return instance;
    }

    private SharedPreferences.Editor getEditor() {
        return preferences.edit();
    }

    public void setFirstLaunch(boolean setValue) {
        getInstance().getEditor().putBoolean(IS_FIRST_LAUNCH, setValue).apply();
    }

    public static boolean isFirstLaunch() {
        return preferences.getBoolean(IS_FIRST_LAUNCH, true);
    }

    public static void initialize(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        }

    }

}
