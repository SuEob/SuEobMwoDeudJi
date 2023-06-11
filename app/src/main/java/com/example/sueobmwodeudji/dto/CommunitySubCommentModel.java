package com.example.sueobmwodeudji.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CommunitySubCommentModel implements Serializable {
    private String name;
    private String content;
    private Date timestamp;
    private Map<String, Boolean> like;
    private ArrayList<CommunitySubCommentCommentModel> commentModels = new ArrayList<>();


    public CommunitySubCommentModel() {
    }

    public CommunitySubCommentModel(String name, String content, Date timestamp, Map<String, Boolean> like, ArrayList<CommunitySubCommentCommentModel> commentModels) {
        this.name = name;
        this.content = content;
        this.timestamp = timestamp;
        this.like = like;
        this.commentModels = commentModels;
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

    public ArrayList<CommunitySubCommentCommentModel> getCommentModels() {
        return commentModels;
    }

    public void setCommentModels(ArrayList<CommunitySubCommentCommentModel> commentModels) {
        this.commentModels = commentModels;
    }
}