package com.example.sueobmwodeudji.fb;

import java.util.Map;

public class PostDTO {
    String post_title, post_contents;
    int post_commentNUM, post_recommendNUM;
    Map<Integer, Map<String, String>> replys;

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_contents() {
        return post_contents;
    }

    public void setPost_contents(String post_contents) {
        this.post_contents = post_contents;
    }

    public int getPost_commentNUM() {
        return post_commentNUM;
    }

    public void setPost_commentNUM(int post_commentNUM) {
        this.post_commentNUM = post_commentNUM;
    }

    public int getPost_recommendNUM() {
        return post_recommendNUM;
    }

    public void setPost_recommendNUM(int post_recommendNUM) {
        this.post_recommendNUM = post_recommendNUM;
    }

    public Map<Integer, Map<String, String>> getReplys() {
        return replys;
    }

    public void setReplys(Map<Integer, Map<String, String>> replys) {
        this.replys = replys;
    }
}
