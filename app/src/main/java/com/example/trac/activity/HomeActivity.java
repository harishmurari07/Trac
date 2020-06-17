package com.example.trac.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trac.R;
import com.example.trac.databinding.HomeActivityBinding;
import com.example.trac.fragment.ContactsFragment;
import com.example.trac.fragment.HomeFragment;
import com.example.trac.fragment.MapsFragment;
import com.example.trac.fragment.MoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private HomeActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.home_activity);

        binding.bottomNavigation.setOnNavigationItemSelectedListener(onItemClickedListener);

        //default fragment view
        Fragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, fragment.getClass().getSimpleName()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener onItemClickedListener
            = item -> {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.maps:
                fragment = new MapsFragment();
                break;
            case R.id.contacts:
                fragment = new ContactsFragment();
                break;
            case R.id.more:
                fragment = new MoreFragment();
                break;
        }
        checkFragmentAndAdd(fragment);
        return true;
    };

    private void checkFragmentAndAdd(Fragment fragment) {
        Fragment attachFragment = getSupportFragmentManager().
                findFragmentByTag(fragment.getClass().getSimpleName());
        if (attachFragment == null) {
            attachFragment = fragment;
        }
        attachFragment(attachFragment);
    }

    private void attachFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
