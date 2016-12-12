package com.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by nima on 12/1/2016.
 */

public class StorageUtils{
    private final int REQUEST_CODE = 8080;
  Activity activity;
    private SharedPreferences prefs;

    public StorageUtils(Activity activity) {
        this.activity = activity;
    }

    public Activity getActivity() {

        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void readWriteExternalStorage(String text) {
        int result = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //show an explanation for the user and tell him why this permission is needed
            } else {
                getPermission();
            }
        }
    }

    private void getPermission() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE
        );
    }


    private void showMessage(String message) {
        Log.i("nima.com.Utils.storage",message);
    }
}
