package com.example.trac;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trac.databinding.RegisterFragmentBinding;

public class RegisterFragment extends Fragment {

    private RegisterFragmentBinding registerFragmentBinding;
    private RegisterListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        registerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        registerFragmentBinding.register.setOnClickListener(v -> navigateToOtpFragment());
        return registerFragmentBinding.getRoot();
    }

    private void navigateToOtpFragment() {
        listener.registerClicked();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (RegisterListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement RegisterListener");
        }
    }

    public interface RegisterListener {
        void registerClicked();
    }

}
