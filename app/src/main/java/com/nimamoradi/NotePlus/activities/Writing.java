package com.nimamoradi.NotePlus.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nimamoradi.NotePlus.R;

public class Writing extends AppCompatActivity {
    int REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);


    }

    public void category(View view) {
        //create new table for database
    }

    public void task(View view) {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.SET_ALARM);
        if (result == PackageManager.PERMISSION_GRANTED) {
            Log.e("permission", "have permission");
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Log.e("permission", "no permission");
        } else {

            Log.e("permission", "get permission");
        }
        Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (openClockIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(openClockIntent, REQ_CODE);
        } else {
            Toast.makeText(this, "No alarm app installed in system", Toast.LENGTH_LONG).show();
        }
    }


}
