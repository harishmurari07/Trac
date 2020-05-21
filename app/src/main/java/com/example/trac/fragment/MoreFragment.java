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
        return moreFragmentBinding.getRoot();
    }

}
