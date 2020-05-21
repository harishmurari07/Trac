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
import com.example.trac.databinding.MapsFragmentBinding;

public class MapsFragment extends Fragment {

    private MapsFragmentBinding mapsFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mapsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.maps_fragment, container, false);
        return mapsFragmentBinding.getRoot();
    }

}
