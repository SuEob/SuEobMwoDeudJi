package com.example.sueobmwodeudji.dto;

public class CommunitySubRecentListModel {
    private String title;
    private String nickname;
    private String content;

    public CommunitySubRecentListModel(String title, String nickname, String content) {
        this.title = title;
        this.nickname = nickname;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
