package com.example.sueobmwodeudji.dto;

public class HomePopularRatingsData {
    public String post_title;
    public String post_content;

    public HomePopularRatingsData(String post_title, String post_content) {
        this.post_title = post_title;
        this.post_content = post_content;
    }

    public String getPost_title() {
        return post_title;
    }

    public String getPost_content() {
        return post_content;
    }
}
