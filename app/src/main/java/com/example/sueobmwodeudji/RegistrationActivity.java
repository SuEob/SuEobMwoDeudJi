package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRegistrationBinding;
import com.example.sueobmwodeudji.rest_api.NEIS_API;
import com.example.sueobmwodeudji.rest_api.Row;
import com.example.sueobmwodeudji.rest_api.SchoolInfo;
import com.example.sueobmwodeudji.rest_api.SchoolResponse;
import com.example.sueobmwodeudji.rest_api.SchoolTimeTable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;
    Call<SchoolResponse> callInfo, callTimeTable;

    List<String> perioList = new LinkedList<String>();
    List<String> classCntntList = new LinkedList<String>();

    public static JSONObject schedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registrationBTN.setOnClickListener(view -> {
            if (schedule == null) {
                SchoolCallInfo();
            }

            Intent intent = new Intent(this, LoginActivity.class);
            setResult(RESULT_OK, intent);
            finish();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 파이어베이스 값 저장
    }

    // 교육청 코드와 학교 코드를 구하는 메소드
    public void SchoolCallInfo() {
        callInfo = NEIS_API.getInfoService().getSchoolInfo(
                "9da752136d5849b985288deb5036dba1",
                "json",
                "용문고등학교" // binding.registrationSchool.getText().toString();
        );

        callInfo.enqueue(new Callback<SchoolResponse>() {
            @Override
            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {
                if (response.isSuccessful()) {
                    SchoolResponse schoolInfoResponse = response.body();
                    SchoolInfo schoolInfo = schoolInfoResponse.getSchoolInfo().get(1); //row
                    List<Row> rows = schoolInfo.getRow();
                    Row row = rows.get(0);

                    SchoolCallTimeTable(row.getMinistryCode(), row.getShcoolCode());

                    Log.d("ministryCode", row.getMinistryCode());
                    Log.d("schoolCode", row.getShcoolCode());

                }
            }

            @Override
            public void onFailure(Call<SchoolResponse> call, Throwable t) {

            }
        });

    }

    // 월요일 ~ 금요일까지 몇 교시에 무슨 과목인지 구하는 메소드 (class_name="1")
    public void SchoolCallTimeTable(String ministryCode, String schoolCode) {

        callTimeTable = NEIS_API.getTimeTableService().getSchoolTimeTable(
                "9da752136d5849b985288deb5036dba1",
                "json",
                ministryCode,
                schoolCode,
                "2023",
                "1",
                "1",
                "1",
                "20230313",
                "20230317"
        );

        callTimeTable.enqueue(new Callback<SchoolResponse>() {
            @Override
            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        SchoolResponse schoolTimeTableResponse = response.body();
                        Log.d("response", String.valueOf(response.raw()));
                        SchoolTimeTable schoolTimeTable = schoolTimeTableResponse.getSchoolTimeTable().get(1); // row
                        List<Row> rows = schoolTimeTable.getRow();

                        for (int i = 0; i < rows.size(); i++) {
                            Row row = rows.get(i);
                            perioList.add(row.getPeriority());
                            classCntntList.add(row.getClassContent());
                        }

                        Log.d("perioList", String.valueOf(perioList));
                        Log.d("classCntntList", String.valueOf(classCntntList));

                        CreateJSON();

                    } catch (NullPointerException e) {
                        Log.e("ERROR", "NullPointerException");
                        // SchoolService 값 변경
                        SchoolCallTimeTableError(ministryCode, schoolCode);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SchoolResponse> call, Throwable t) {

            }
        });

    }

    // 월요일 ~ 금요일까지 몇 교시에 무슨 과목인지 구하는 메소드 (class_name="01")
    public void SchoolCallTimeTableError(String ministryCode, String schoolCode) {

        callTimeTable = NEIS_API.getTimeTableService().getSchoolTimeTable(
                "9da752136d5849b985288deb5036dba1",
                "json",
                ministryCode,
                schoolCode,
                "2023",
                "1",
                "1",
                "01",
                "20230313",
                "20230317"
        );

        callTimeTable.enqueue(new Callback<SchoolResponse>() {
            @Override
            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {
                if (response.isSuccessful()) {
                    try {
                        SchoolResponse schoolTimeTableResponse = response.body();
                        Log.d("response", String.valueOf(response.raw()));
                        SchoolTimeTable schoolTimeTable = schoolTimeTableResponse.getSchoolTimeTable().get(1); // row
                        List<Row> rows = schoolTimeTable.getRow();

                        for (int i = 0; i < rows.size(); i++) {
                            Row row = rows.get(i);
                            perioList.add(row.getPeriority());
                            classCntntList.add(row.getClassContent());
                        }

                        Log.d("perioList", String.valueOf(perioList));
                        Log.d("classCntntList", String.valueOf(classCntntList));

                        CreateJSON();

                    } catch (NullPointerException e) {
                        Log.e("ERROR", "NullPointerException");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SchoolResponse> call, Throwable t) {

            }
        });

    }


    // JSON 구조를 만드는 메소드
    public void CreateJSON() throws JSONException {
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"}; // 월요일 ~ 금요일
        int dayNum = 0; // 0:월요일 ~ 4:금요일
        int cnt = 0; // 첫번째 월요일 계산 용도

        /* JSON 구조
        JSONObject = {
            JSONArray = [{JSONObject},{JSONObject}],
            JSONArray = [{JSONObject},{JSONObject}], ...
        } */

        schedule = new JSONObject();

        List<JSONArray> dayList = new ArrayList<>();
        for (int i=0; i<5; i++) {
            resetJSONArray(dayList);
        }

        for (int i=0; i<perioList.size(); i++) {
            if (perioList.get(i).equals("1") && cnt!=0) { // 월요일 ~ 목요일까지 저장, 1교시로 요일을 구분
                schedule.put(days[dayNum], dayList.get(dayNum));
                dayNum++;
            } else if (perioList.get(i).equals("4")) { // 금요일 저장
                schedule.put(days[dayNum], dayList.get(dayNum));
            }
            addJSONObject(dayList.get(dayNum), perioList.get(i), classCntntList.get(i));
            cnt++;
        }

        Log.d("schedule", String.valueOf(schedule));

    }

    // CreateJSON 안에서 List<JSONArray>안에 JSONArray 초기화 후 add
    public void resetJSONArray(List<JSONArray> dayList) {
        JSONArray dayArray = new JSONArray();
        dayList.add(dayArray);
    }

    // CreateJSON 안에서 JSONObject 초기화 후 교시와 수업 내용을 put 그 후 JSONArray안 에 put
    public void addJSONObject(JSONArray dayArray, String period, String classContent) throws JSONException {
        JSONObject dayObject = new JSONObject();
        dayObject.put("perio", period);
        dayObject.put("class_content", classContent);
        dayArray.put(dayObject);
    }
}

