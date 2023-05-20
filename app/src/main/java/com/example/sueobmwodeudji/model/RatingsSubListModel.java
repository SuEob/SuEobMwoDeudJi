package com.example.sueobmwodeudji.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class RatingsSubListModel  implements Serializable {
    private String name;
    private String title;
    private String content;
    private String difficulty;
    private String type;
    private Boolean honey;
    private Date timestamp;

    private Map<String, Boolean> like;
    private ArrayList<CommunitySubCommentModel> comments = new ArrayList<>();

    public RatingsSubListModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Boolean> getLike() {
        return like;
    }

    public void setLike(Map<String, Boolean> like) {
        this.like = like;
    }

    public ArrayList<CommunitySubCommentModel> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommunitySubCommentModel> comments) {
        this.comments = comments;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHoney() {
        return honey;
    }

    public void setHoney(Boolean honey) {
        this.honey = honey;
    }
}
