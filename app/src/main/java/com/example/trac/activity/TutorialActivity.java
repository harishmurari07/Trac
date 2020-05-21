package com.example.trac.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.trac.preferences.PreferenceManager;
import com.example.trac.R;
import com.example.trac.adapter.ViewPagerAdapter;
import com.example.trac.data.WelcomeScreenData;
import com.example.trac.databinding.TutorialScreenBinding;
import com.google.android.material.tabs.TabLayoutMediator;

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

        new TabLayoutMediator(tutorialScreenBinding.pagerDots, tutorialScreenBinding.viewPager,
                true, (tab, position) -> {
        }).attach();
        tutorialScreenBinding.getStarted.setOnClickListener(view -> {
            //TODO: Fix this
//            if (tutorialScreenBinding.viewPager.getCurrentItem() adapter.getItemCount())
            PreferenceManager.getInstance().setFirstLaunch(false);
            startActivity(new Intent(TutorialActivity.this, RegisterActivity.class));
            finish();
        });
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
