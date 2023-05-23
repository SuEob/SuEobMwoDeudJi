package com.example.sueobmwodeudji.rest_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchoolResponse {
    @SerializedName("schoolInfo")
    @Expose
    public List<SchoolInfo> schoolInfo;

    public List<SchoolInfo> getSchoolInfo() {return schoolInfo;}

    @SerializedName("hisTimetable")
    @Expose
    public List<SchoolTimeTable> schoolTimeTable;

    public List<SchoolTimeTable> getSchoolTimeTable() {
        return schoolTimeTable;
    }
}
