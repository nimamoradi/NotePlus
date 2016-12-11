package com.nimamoradi.NotePlus.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nimamoradi.NotePlus.R;

import java.io.IOException;

public class Writing extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 8080;
    int REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        TextView textView = (TextView) findViewById(R.id.notes);
        if (text != null)
            textView.setText(text);

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
            startActivity(openClockIntent);
        } else {
            Toast.makeText(this, "No alarm app installed in system", Toast.LENGTH_LONG).show();
        }
    }


    public void share(View view) {

        String shareBody = ((TextView) findViewById(R.id.notes)).getText().toString();
        String shareTitle = ((TextView) findViewById(R.id.title)).getText().toString();

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareTitle);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));


    }

    public void attach(View view) {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            Log.e("permission", "have permission");
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.e("permission", "no permission");
        } else {

            Log.e("permission", "get permission");
        }
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent intent = new Intent();
// Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMG);

//        if (galleryIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
//        } else {
//            Toast.makeText(this, R.string.noImage, Toast.LENGTH_LONG).show();
//        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setImageBitmap(bitmap);
                LinearLayout lin = (LinearLayout) findViewById(R.id.mainlayout);
                lin.addView(imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
