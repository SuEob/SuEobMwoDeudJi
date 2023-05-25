package com.example.sueobmwodeudji;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityTimeTableThridBinding;
import com.example.sueobmwodeudji.rest_api.NEIS_API;
import com.example.sueobmwodeudji.rest_api.Row;
import com.example.sueobmwodeudji.rest_api.SchoolInfo;
import com.example.sueobmwodeudji.rest_api.SchoolResponse;
import com.example.sueobmwodeudji.rest_api.SchoolTimeTable;
import com.example.sueobmwodeudji.ui.sub_ui.TimeTableListFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TimeTableThridActivity extends AppCompatActivity {
    private ActivityTimeTableThridBinding binding;

    Call<SchoolResponse> callInfo, callTimeTable;

    List<String> perioList = new LinkedList<String>();
    List<String> classCntntList = new LinkedList<String>();

    public static JSONObject schedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableThridBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String[] years = {"2023년"};
        final String[] semesters = {"1학기", "2학기"};
        final String[] grades = {"1학년", "2학년", "3학년"};
        final String[] className = {"1반", "2반", "3반", "4반", "5반", "6반", "7반", "8반", "9반", "10반"};

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.yearSpin.setAdapter(adapter1);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, semesters);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.semesterSpin.setAdapter(adapter2);

        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grades);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.gradeSpin.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, className);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.classNameSpin.setAdapter(adapter4);

        binding.tableAddBtn.setOnClickListener(v -> {
            TimeTableListFragment fragment = new TimeTableListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("time_table_name", binding.nameET.getText().toString());
            fragment.setArguments(bundle);

            SchoolCallInfo();
            finish();
        });
    }

    // 교육청 코드와 학교 코드를 구하는 메소드
    public void SchoolCallInfo() {
        callInfo = NEIS_API.getInfoService().getSchoolInfo(
                "9da752136d5849b985288deb5036dba1",
                "json",
                RegistrationActivity.school_name
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
                binding.gradeSpin.getSelectedItem().toString().substring(0,1),
                binding.classNameSpin.getSelectedItem().toString().substring(0,1),
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
                binding.gradeSpin.getSelectedItem().toString().substring(0,1),
                "0"+binding.classNameSpin.getSelectedItem().toString().substring(0,1),
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
    public void resetJSONArray(List< JSONArray > dayList) {
        JSONArray dayArray = new JSONArray();
        dayList.add(dayArray);
    }

    // CreateJSON 안에서 JSONObject 초기화 후 교시와 수업 내용을 put 그 후 JSONArray 안에 put
    public void addJSONObject(JSONArray dayArray, String period, String classContent) throws
    JSONException {
        JSONObject dayObject = new JSONObject();
        dayObject.put("perio", period);
        dayObject.put("class_content", classContent);
        dayArray.put(dayObject);
    }

}