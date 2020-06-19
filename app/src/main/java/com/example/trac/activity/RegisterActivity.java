package com.example.trac.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.trac.R;
import com.example.trac.fragment.RegisterFragment;
import com.example.trac.viewmodel.LoginViewModel;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.register_screen);

        //sets the value for existing and new User
        boolean isExistingUser = getIntent().getBooleanExtra("EXISTING_USER", false);
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.setIsExistingUser(isExistingUser);

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
