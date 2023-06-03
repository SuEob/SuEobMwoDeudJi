package com.example.sueobmwodeudji.rest_api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SchoolService {
    @GET("schoolInfo")
    Call<SchoolResponse> getSchoolInfo(
            @Query("KEY") String key,
            @Query("Type") String type,
            @Query("SCHUL_NM") String school_name // 학교 이름
    );

    @GET("hisTimetable")
    Call<SchoolResponse> getSchoolTimeTable(
            @Query("KEY") String key,
            @Query("Type") String type,
            @Query("ATPT_OFCDC_SC_CODE") String ministry_code, // 교육청 코드
            @Query("SD_SCHUL_CODE") String school_code, // 학교 코드
            @Query("AY") String year, // 년도
            @Query("SEM") String semester, // 학기
            @Query("GRADE") String grade, // 학년
            @Query("CLASS_NM") String class_name, // 반
            @Query("TI_FROM_YMD") String start_date, // 시작
            @Query("TI_TO_YMD") String end_date // 끝
    );
}
