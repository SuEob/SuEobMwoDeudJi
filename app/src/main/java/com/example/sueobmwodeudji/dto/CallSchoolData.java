package com.example.sueobmwodeudji.dto;

import java.util.ArrayList;
import java.util.List;

public class CallSchoolData {
    public String day;
    public ArrayList<String> classCntnt = new ArrayList();

    public CallSchoolData(String day, ArrayList<String> classCntnt) {
        this.day = day;
        this.classCntnt = classCntnt;
    }

    @Override
    public String toString() {
        return "{" + "day='" + day + '\'' + "," +
                " classCntnt=" + classCntnt + '}';
    }

}
