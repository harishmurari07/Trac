package com.example.trac;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.trac.databinding.TutorialScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TutorialScreenBinding tutorialScreenBinding = DataBindingUtil.setContentView(this, R.layout.tutorial_screen);

        createWelcomeTutorial();
        tutorialScreenBinding.viewPager.setAdapter(adapter);
    }


    private void createWelcomeTutorial() {
        List<WelcomeScreenData> screens = new ArrayList<>();

        WelcomeScreenData welcomeScreenOne = new WelcomeScreenData();
        welcomeScreenOne.setTitle(getString(R.string.tutorial_title_one));
        welcomeScreenOne.setDescription(getString(R.string.tutorial_description_one));
        welcomeScreenOne.setScreenImage(R.drawable.ic_tutorial1);

        WelcomeScreenData welcomeScreenTwo = new WelcomeScreenData();
        welcomeScreenTwo.setTitle(getString(R.string.tutorial_title_two));
        welcomeScreenTwo.setDescription(getString(R.string.tutorial_description_two));
        welcomeScreenTwo.setScreenImage(R.drawable.ic_tutorial2);

        screens.add(welcomeScreenOne);
        screens.add(welcomeScreenTwo);

        adapter = new ViewPagerAdapter(screens);

    }

}
