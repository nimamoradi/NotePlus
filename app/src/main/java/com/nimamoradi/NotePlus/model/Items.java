package com.nimamoradi.NotePlus.model;

/**
 * Created by nima on 11/30/2016.
 */

public class Items {

    private long id;
    private String Title;
    private String Text;
    private String Url1;
    private String Url2;
    private String Url3;

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

    public String getUrl1() {
        return Url1;
    }

    public void setUrl1(String url1) {
        Url1 = url1;
    }

    public String getUrl2() {
        return Url2;
    }

    public void setUrl2(String url2) {
        Url2 = url2;
    }

    public String getUrl3() {
        return Url3;
    }

    public void setUrl3(String url3) {
        Url3 = url3;
    }
}
