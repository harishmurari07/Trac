package com.example.trac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (PreferenceManager.isFirstLaunch()) {
            startActivity(new Intent(LauncherActivity.this, TutorialActivity.class));
            finish();
        } else {
            startActivity(new Intent(LauncherActivity.this, RegisterActivity.class));
            finish();
        }
    }
}
