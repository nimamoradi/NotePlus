package com.nimamoradi.NotePlus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nimamoradi.NotePlus.R;
import com.nimamoradi.NotePlus.databases.ItemDAO;
import com.nimamoradi.NotePlus.model.Items;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ItemDAO itemdao = new ItemDAO(this);

        Intent i = new Intent(this, Writing.class);
//        Items item=new Items(1
//                ,"today");
        //this way you get item
        Items item = itemdao.getAll().get(0);


//            item.setTitle("high");
//            item.setText("hello");
//
//            itemdao.add(item);

        i.putExtra("items", item);
        startActivity(i);
        finish();

    }


}
