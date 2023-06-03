package com.example.sueobmwodeudji.rest_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {
    // 학교에 대한 정보
    @SerializedName("ATPT_OFCDC_SC_CODE")
    @Expose
    public String ministry_code; // 교육청 코드

    @SerializedName("SD_SCHUL_CODE")
    @Expose
    public String school_code; // 학교 코드

    public String getMinistryCode() {return ministry_code;}

    public String getShcoolCode() {return school_code;}

    // 시간표에 대한 정보
    @SerializedName("PERIO")
    @Expose
    private String periority; // 학기

    @SerializedName("ITRT_CNTNT")
    @Expose
    private String class_content; // 수업 내용

    public String getPeriority() {return periority;}

    public String getClassContent() {return class_content;}
}
