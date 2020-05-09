package com.example.trac;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.example.trac.databinding.TutorialScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;
    private TutorialScreenBinding tutorialScreenBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tutorialScreenBinding = DataBindingUtil.setContentView(this, R.layout.tutorial_screen);

        createWelcomeTutorial();
        tutorialScreenBinding.viewPager.setAdapter(adapter);

        setUpPageIndicators();
        tutorialScreenBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setUpCurrentIndicator(position);
            }
        });
        tutorialScreenBinding.getStarted.setOnClickListener(view -> {
            PreferenceManager.getInstance().setFirstLaunch(true);
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

    private void setUpPageIndicators() {
        ImageView[] indicators = new ImageView[adapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(6, 0, 6, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dot));
            indicators[i].setLayoutParams(layoutParams);
            tutorialScreenBinding.pagerDots.addView(indicators[i]);
        }
    }

    private void setUpCurrentIndicator(int position) {
        int child = tutorialScreenBinding.pagerDots.getChildCount();
        for (int i = 0; i < child; i++) {
            ImageView imageView = (ImageView) tutorialScreenBinding.pagerDots.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactive_dot));
            }
        }
    }

}
