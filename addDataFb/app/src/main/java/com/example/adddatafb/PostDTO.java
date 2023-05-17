package com.example.adddatafb;

import java.util.Map;

public class PostDTO {
    String post_title, post_contents;
    int post_commentNUM, post_recommendNUM;
    Map<Integer, Map<String, String>> replys;

    public String getPost_title() {
        return post_title;
    }
    public void setPost_title(String asd) { post_title = asd; }

    public String getPost_contents() {
        return post_contents;
    }
    public void setPost_contents(String asd) { post_contents = asd; }

    public int getPost_commentNUM() {
        return post_commentNUM;
    }
    public void setPost_commentNUM(int asd) { post_commentNUM = asd; }

    public int getPost_recommendNUM() {
        return post_recommendNUM;
    }
    public void setPost_recommendNUM(int asd) { post_recommendNUM = asd; }

    public Map<Integer, Map<String, String>> getReplys() { return replys; }
    public void setReplys(Map<Integer, Map<String, String>> asd) { replys = asd; }
}
