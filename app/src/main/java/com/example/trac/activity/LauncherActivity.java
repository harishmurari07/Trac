package com.example.trac.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.trac.preferences.PreferenceManager;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PreferenceManager.isFirstLaunch()) {
            startActivity(new Intent(LauncherActivity.this, BluetoothActivity.class));
            finish();
        } else {
            startActivity(new Intent(LauncherActivity.this, HomeActivity.class));
//            startActivity(new Intent(LauncherActivity.this, RegisterActivity.class));
            finish();
        }
    }
}
