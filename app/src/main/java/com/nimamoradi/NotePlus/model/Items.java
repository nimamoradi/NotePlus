package com.nimamoradi.NotePlus.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by nima on 11/30/2016.
 */

public class Items implements Serializable {

    int count = 0;
    private long id;
    private String Title;
    private String Text;
    private String warp;

    public Items(long id, String date) {
        this.id = id;


    }

    public Items(String title, String text) {
        Title = title;
        Text = text;
    }


    public Items(long id, String title, String text, String warp) {
        this.id = id;
        Text = text;
        Title = title;
        this.warp = warp;
    }

    public static String makewarp(String time, String... uri) {
        String warp = "";
        warp += time;
        for (int i = 0; i < uri.length; i++)
            warp += '\10' + uri[i];
        return warp;
    }

    public String getWarp() {
        return warp;
    }

    public void setWarp(String warp) {
        this.warp = warp;
    }

    public void addURI(String Uri) {

        warp += '\10' + Uri;
    }

    public ArrayList<String> getURI() {
        ArrayList<String> getURI = new ArrayList<String>();
        String[] a = warp.split("\10");
        for (int i = 0; i < a.length; i++)
            getURI.add(a[i]);
        return getURI;

    }

    public String getURI(int count) {

        String[] a = warp.split("\10");

        return a[count + 1];

    }

    public String getTime() {
        String time = (warp.split("\10"))[0];
        return time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }


}
