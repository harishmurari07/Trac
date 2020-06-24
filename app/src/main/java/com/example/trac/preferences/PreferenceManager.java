package com.example.trac.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.trac.model.UserDetails;
import com.google.gson.Gson;

public class PreferenceManager {

    private static String MY_PREFERENCES = "mypreferences";
    private static String IS_FIRST_LAUNCH = "isFirstLaunch";
    private static String KEY = "key";
    private static String PHONE = "phone";
    private static String USER_DETAILS = "user_details";

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

    public void setKey(String key) {
        getInstance().getEditor().putString(KEY, key).apply();
    }

    public void setUserDetails(String name, String phone, String email) {
        UserDetails userDetails = new UserDetails(name, phone, email);
        Gson gson = new Gson();
        String json = gson.toJson(userDetails);
        getInstance().getEditor().putString(USER_DETAILS, json).apply();
    }

    public static UserDetails getUserDetails() {
        Gson gson = new Gson();
        String json = preferences.getString(USER_DETAILS, "");
        return gson.fromJson(json, UserDetails.class);
    }

    public void setPhone(String phone) {
        getInstance().getEditor().putString(PHONE, phone).apply();
    }

    public static String getPhone() {
        return preferences.getString(PHONE, "");
    }

    private static String getKey() {
        return preferences.getString(KEY, "");
    }

    public static String token() {
        return "Bearer" + " " + getKey();
    }

    public static void initialize(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        }

    }

}
