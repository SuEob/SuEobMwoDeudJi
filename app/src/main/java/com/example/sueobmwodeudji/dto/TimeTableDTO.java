package com.example.sueobmwodeudji.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class TimeTableDTO implements Serializable {

    String timeTableName;
    int year, semester;
    ArrayList<String> mon, tue, wed, thu, fri;
    String email;
    boolean selected;

    public String getTimeTableName() {
        return timeTableName;
    }
    public void setTimeTableName(String asd) { timeTableName = asd; }

    public int getYear() {
        return year;
    }
    public void setYear(int asd) {
        year = asd;
    }

    public int getSemester() {
        return semester;
    }
    public void setSemester(int asd) {
        semester = asd;
    }

    public ArrayList<String> getMon() {
        return mon;
    }
    public void setMon(ArrayList<String> asd) { mon = asd; }

    public ArrayList<String> getTue() {
        return tue;
    }
    public void setTue(ArrayList<String> asd) { tue = asd; }

    public ArrayList<String> getWed() {
        return wed;
    }
    public void setWed(ArrayList<String> asd) { wed = asd; }

    public ArrayList<String> getThu() {
        return thu;
    }
    public void setThu(ArrayList<String> asd) { thu = asd; }

    public ArrayList<String> getFri() {
        return fri;
    }
    public void setFri(ArrayList<String> asd) { fri = asd; }

    public String getEmail() { return email; }
    public void setEmail(String asd) { email = asd; }

    public boolean isSelected() { return selected; }
    public void setSelected(boolean asd) { selected = asd; }
}
