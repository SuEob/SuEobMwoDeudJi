package com.example.sueobmwodeudji.rest_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Head {
    @SerializedName("list_total_count")
    @Expose
    private Integer listTotalCount;

    @SerializedName("RESULT")
    @Expose
    private Result result;

    public Integer getListTotalCount() {
        return listTotalCount;
    }

    public Result getResult() {
        return result;
    }
}
