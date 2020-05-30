package com.example.trac.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trac.R;
import com.example.trac.databinding.RegisterScreenBinding;
import com.example.trac.fragment.RegisterFragment;

public class RegisterActivity extends AppCompatActivity {

    private RegisterScreenBinding registerScreenBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerScreenBinding = DataBindingUtil.setContentView(this, R.layout.register_screen);

        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().
                findFragmentByTag(RegisterFragment.class.getSimpleName());
        if (registerFragment == null) {
            registerFragment = new RegisterFragment();
        }
        attachFragment(registerFragment);
    }

    public void attachFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

}
