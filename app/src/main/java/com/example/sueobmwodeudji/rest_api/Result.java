package com.example.sueobmwodeudji.rest_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("CODE")
    @Expose
    private String code;

    @SerializedName("MESSAGE")
    @Expose
    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
