package com.example.trac.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.trac.R;
import com.example.trac.databinding.BluetoothScreenBinding;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BluetoothScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.bluetooth_screen);

        bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        binding.grantAccess.setOnClickListener(v -> checkAndEnableBluetooth());

    }

    private void checkAndEnableBluetooth() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), REQUEST_ENABLE_BT);
        } else if (bluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "Bluetooth is already on..", Toast.LENGTH_LONG).show();
            navigateToHomeScreen();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // Bluetooth is turned on
            Toast.makeText(this, "Bluetooth turned on..", Toast.LENGTH_SHORT).show();
            navigateToHomeScreen();
        } else {
            // Bluetooth permission denied
            Toast.makeText(this, "Bluetooth premission denied..", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToHomeScreen() {
        startActivity(new Intent(BluetoothActivity.this, TutorialActivity.class));
        finish();
    }

}
