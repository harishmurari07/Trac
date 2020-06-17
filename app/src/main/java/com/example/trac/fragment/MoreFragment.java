package com.example.trac.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trac.R;
import com.example.trac.databinding.MoreFragmentBinding;

public class MoreFragment extends Fragment {

    private MoreFragmentBinding moreFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        moreFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.more_fragment, container, false);
        initViews();
        attachListeners();
        return moreFragmentBinding.getRoot();
    }

    private void attachListeners() {
//        moreFragmentBinding.logoutRow.layout.setOnClickListener(v -> moreFragmentBinding.pbarCloud.show());
    }

    private void initViews() {
        moreFragmentBinding.bluetoothRow.helpText.setText("Bluetooth Device Data");
        moreFragmentBinding.inviteRow.helpText.setText("Invite Friends");
        moreFragmentBinding.reportRow.helpText.setText("Report a bug");
        moreFragmentBinding.faqRow.helpText.setText("FAQ's");
        moreFragmentBinding.unlinkRow.helpText.setText("Unlink");
        moreFragmentBinding.logoutRow.helpText.setText("Logout");
    }

}
