package com.example.sueobmwodeudji.fb;

import java.util.ArrayList;

public class TimeTableDTO {

    String timeTableName;
    int year, semester, totalCredit, nowCredit;
    ArrayList<String> mon, tue, wed, thu, fri;

    public String getTimeTableName() {
        return timeTableName;
    }

    public void setTimeTableName(String timeTableName) {
        this.timeTableName = timeTableName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    public int getNowCredit() {
        return nowCredit;
    }

    public void setNowCredit(int nowCredit) {
        this.nowCredit = nowCredit;
    }

    public ArrayList<String> getMon() {
        return mon;
    }

    public void setMon(ArrayList<String> mon) {
        this.mon = mon;
    }

    public ArrayList<String> getTue() {
        return tue;
    }

    public void setTue(ArrayList<String> tue) {
        this.tue = tue;
    }

    public ArrayList<String> getWed() {
        return wed;
    }

    public void setWed(ArrayList<String> wed) {
        this.wed = wed;
    }

    public ArrayList<String> getThu() {
        return thu;
    }

    public void setThu(ArrayList<String> thu) {
        this.thu = thu;
    }

    public ArrayList<String> getFri() {
        return fri;
    }

    public void setFri(ArrayList<String> fri) {
        this.fri = fri;
    }
}
