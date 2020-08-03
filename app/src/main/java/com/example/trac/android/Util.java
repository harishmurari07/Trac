package com.example.trac.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Util {

    private static ProgressDialog progressDialog;

    public static void hideKeyboard(@NonNull Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    public static void showToast(@NonNull Context context, @NonNull String displayMessage) {
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();
    }

    public static void showLoadingDialog(@NonNull Context context, @NonNull String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    public static void dismissLoadingDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
