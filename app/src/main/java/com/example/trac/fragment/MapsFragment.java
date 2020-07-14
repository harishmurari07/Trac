package com.example.trac.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.trac.R;
import com.example.trac.android.Util;
import com.example.trac.databinding.MapsFragmentBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

public class MapsFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_FINE_LOCATION = 7;
    private MapsFragmentBinding mapsFragmentBinding;
    private MapView mapView;
    private FusedLocationProviderClient client;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mapsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.maps_fragment, container, false);
        mapView = mapsFragmentBinding.map;
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        return mapsFragmentBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocationOnMap();
            } else {
                Util.showToast(getContext(), "You have disabled location permission");
            }
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Request Permission
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_FINE_LOCATION);
        } else {
            //Already granted permission
            getCurrentLocationOnMap();
        }
    }

    private void getCurrentLocationOnMap() {
        Task<Location> locationTask = client.getLastLocation();
        locationTask.addOnSuccessListener(location -> {
            if (location != null) {
                mapView.onCreate(null);
                mapView.onResume();
                mapView.getMapAsync(googleMap -> {
                    MapsInitializer.initialize(MapsFragment.this.getContext());
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    googleMap.addMarker(markerOptions);
                });
            }
        });
    }

}
