package com.example.sueobmwodeudji.model;

public class HomePopularPostData {
    public String post_title;
    public String post_date;

    public HomePopularPostData(String post_title, String post_date) {
        this.post_title = post_title;
        this.post_date = post_date;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_date() {
        return post_date;
    }
}
