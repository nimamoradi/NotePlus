package com.nimamoradi.NotePlus.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
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
import com.nimamoradi.NotePlus.databases.DBOpenHelper;
import com.nimamoradi.NotePlus.databases.ItemDAO;
import com.nimamoradi.NotePlus.model.Items;

import java.io.IOException;

public class Writing extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 8080;

    Items item;
    ItemDAO itemdao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        DBOpenHelper dbhelper = new DBOpenHelper(this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();


        itemdao = new ItemDAO(db);
        String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        TextView textView1 = (TextView) findViewById(R.id.title);
        TextView textView = (TextView) findViewById(R.id.notes);

            Bundle extras = getIntent().getExtras();
            item = (Items) extras.getSerializable("items");
            if (itemdao.contain(item.getId())) {
                item = itemdao.get(item.getId());
                Log.e(this + "", "contain ");

                    if (item.getUrl1() != null)
                        setPic(item.getUrl1());
                    if (item.getUrl2() != null)
                        setPic(item.getUrl2());
                    if (item.getUrl3() != null)
                        setPic(item.getUrl3());

            } else {
                Log.e(this + "", "don't contain so add it ");
                itemdao.add(item);
            }

            textView.setText(item.getText());
            textView1.setText(item.getTitle());

        // item = new Items(1, SystemClock.currentThreadTimeMillis() + "", 1);
//            itemdao.add(item);


//            textView.setText(item.getText());
//            textView1.setText(item.getTitle());




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
            Log.e(this + " uri", item.getCount() + "");
            if (item == null)
                item = new Items(((TextView) findViewById(R.id.notes)).getText().toString(),
                        ((TextView) findViewById(R.id.title)).getText().toString());
            if (item.getUrl1() == null) item.setUrl1(uri.toString());
            else if (item.getUrl2() == null) item.setUrl2(uri.toString());
            else if (item.getUrl3() == null) item.setUrl3(uri.toString());
            else {

                Toast.makeText(this, "Image limit reached", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.e(this + " uri", item.getUrl1() + "");
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

    private void setPic(String url) {
        Uri uri = Uri.parse(url);
        if (item == null)
            item = new Items(((TextView) findViewById(R.id.notes)).getText().toString(),
                    ((TextView) findViewById(R.id.title)).getText().toString());
        if (item.getUrl1() == null) item.setUrl1(uri.toString());
        else if (item.getUrl2() == null) item.setUrl2(uri.toString());
        else if (item.getUrl3() == null) item.setUrl3(uri.toString());


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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    public void remove(View view) {
        itemdao.delete(item);
        item = null;
        finish();

    }

    /**
     * saves the contexts
     */
    public void save(View view) {


        item.setText(((TextView) findViewById(R.id.notes)).getText().toString());
        item.setTitle(((TextView) findViewById(R.id.title)).getText().toString());

        itemdao.update(item);

        Log.e(this + "", item.getText());

    }
}
