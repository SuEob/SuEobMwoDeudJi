package com.example.sueobmwodeudji.dto;

public class RatingsSubRecentListModel {
    private String class_name;
    private String teacher_name;
    private String content;

    public RatingsSubRecentListModel(String class_name, String teacher_name, String content) {
        this.class_name = class_name;
        this.teacher_name = teacher_name;
        this.content = content;
    }

    public String getClassName() {
        return class_name;
    }

    public void setClassName(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacherName() {
        return teacher_name;
    }

    public void setTeacherName(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
