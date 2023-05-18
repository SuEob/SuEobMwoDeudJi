package com.example.sueobmwodeudji.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class CommunitySubListModel implements Serializable {
    private String name;
    private String title;
    private String content;
    private Date timestamp;

    private Map<String, Boolean> like;
    private ArrayList<CommunitySubCommentModel> coments = new ArrayList<>();

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<CommunitySubCommentModel> getComents() {
        return coments;
    }

    public void setComents(ArrayList<CommunitySubCommentModel> coments) {
        this.coments = coments;
    }
    public Map<String, Boolean> getLike() {
        return like;
    }
    public void setLike(Map<String, Boolean> like) {
        this.like = like;
    }
}
