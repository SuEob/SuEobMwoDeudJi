package com.example.sueobmwodeudji.model;

public class RatingsSubListModel {
    private String title;
    private String sub_title;

    public RatingsSubListModel(String title, String sub_title) {
        this.title = title;
        this.sub_title = sub_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return sub_title;
    }

    public void setSubTitle(String sub_title) {
        this.sub_title = sub_title;
    }
}
