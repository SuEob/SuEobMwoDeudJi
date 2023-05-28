package com.example.sueobmwodeudji.dto;

import java.util.ArrayList;
import java.util.List;

public class CallSchoolData {
    public String day;
    public List<String> classCntnt = new ArrayList();

    public CallSchoolData(String day, List<String> classCntnt) {
        this.day = day;
        this.classCntnt = classCntnt;
    }

    @Override
    public String toString() {
        return "{" + "day='" + day + '\'' + "," +
                " classCntnt=" + classCntnt + '}';
    }

}
