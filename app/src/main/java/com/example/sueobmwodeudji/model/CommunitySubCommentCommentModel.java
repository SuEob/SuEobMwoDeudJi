package com.example.sueobmwodeudji.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class CommunitySubCommentCommentModel implements Serializable {
    private String name;
    private String content;
    private Date timestamp;
    private Map<String, Boolean> like;

    public CommunitySubCommentCommentModel() {
    }

    public CommunitySubCommentCommentModel(String name, String content, Date timestamp, Map<String, Boolean> like) {
        this.name = name;
        this.content = content;
        this.timestamp = timestamp;
        this.like = like;
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
}
