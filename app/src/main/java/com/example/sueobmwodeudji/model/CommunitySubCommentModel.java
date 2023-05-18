package com.example.sueobmwodeudji.model;

public class CommunitySubCommentModel {
    private String name;
    private String content;

    public CommunitySubCommentModel(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}