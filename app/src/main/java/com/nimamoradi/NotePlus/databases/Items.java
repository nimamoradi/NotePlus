package com.nimamoradi.NotePlus.databases;

/**
 * Created by nima on 11/30/2016.
 */

public class Items {

    private long id;
    private String Title;
    private String Text;
    public Items(long id, String title, String text) {
        this.id = id;
        Text = text;
        Title = title;
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
