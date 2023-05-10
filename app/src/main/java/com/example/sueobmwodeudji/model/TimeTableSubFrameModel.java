package com.example.sueobmwodeudji.model;

public class TimeTableSubFrameModel {
    public String title;
    public String teacher;
    public String content;
    public int layoutId;

    // TimeTableAddFragment
    public TimeTableSubFrameModel(String title, String teacher, String content) {
        this.title = title;
        this.teacher = teacher;
        this.content = content;
    }

    // TimeTableListFragment
    public TimeTableSubFrameModel(String title) {
        this.title = title;

    }
}
