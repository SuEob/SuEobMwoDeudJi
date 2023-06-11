package com.example.sueobmwodeudji.dto;

public class TimeTableSubFrameModel {
    public String addTitle, listTitle;
    public String day;
    public String period;


    // TimeTableAddFragment
    public TimeTableSubFrameModel(String addTitle, String day, String period) {
        this.addTitle = addTitle;
        this.day = day;
        this.period = period;
    }

    // TimeTableListFragment
    public TimeTableSubFrameModel(String listTitle) {
        this.listTitle = listTitle;

    }
}
