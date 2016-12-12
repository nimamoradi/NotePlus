package com.nimamoradi.NotePlus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nimamoradi.NotePlus.R;
import com.nimamoradi.NotePlus.model.Items;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        newnote(1);
        Intent i = new Intent(this, Writing.class);
        i.putExtra("items", newnote(45));
        startActivity(i);
        finish();

    }

    public Items newnote(long id) {


        Items item = new Items(id, "today", 1);

        return item;

    }
}
