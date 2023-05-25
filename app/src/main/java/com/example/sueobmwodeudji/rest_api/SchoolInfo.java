package com.example.sueobmwodeudji.rest_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchoolInfo {
    @SerializedName("head")
    @Expose
    public List<Row> head;

    @SerializedName("row")
    @Expose
    public List<Row> row;

    public List<Row> getHead() {return head;}

    public List<Row> getRow() {
        return row;
    }
}
