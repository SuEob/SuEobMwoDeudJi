package com.example.sueobmwodeudji.model;

import java.util.Map;

public class CommunitySubListModel {
    private String title;
    private String content;
    private String name;

    public CommunitySubListModel(String title, String content, String name) {
        this.title = title;
        this.content = content;
        this.name = name;
    }

    public CommunitySubListModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
