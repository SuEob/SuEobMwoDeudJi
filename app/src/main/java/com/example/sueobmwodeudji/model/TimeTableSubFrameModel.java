package com.example.sueobmwodeudji.model;

public class TimeTableSubFrameModel {
    public String addTitle, listTitle;
    public String teacher;
    public String content;
    public int layoutId;

    // TimeTableAddFragment
    public TimeTableSubFrameModel(String addTitle, String teacher, String content) {
        this.addTitle = addTitle;
        this.teacher = teacher;
        this.content = content;
    }

    // TimeTableListFragment
    public TimeTableSubFrameModel(String listTitle) {
        this.listTitle = listTitle;

    }
}
