package com.example.sueobmwodeudji.rest_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NEIS_API {
    /*
SchoolResponse -> SchoolInfo -> Head -> Result
                             -> Row

SchoolResponse -> SchoolTimeTable -> Head -> Result
                                  -> Row
*/
    private static final String BASE_URL = "https://open.neis.go.kr/hub/";

    public static SchoolService getInfoService(){
        return getInfoInstance().create(SchoolService.class);
    }

    private static Retrofit getInfoInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static SchoolService getTimeTableService(){
        return getTimeTableInstance().create(SchoolService.class);
    }


    private static Retrofit getTimeTableInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
