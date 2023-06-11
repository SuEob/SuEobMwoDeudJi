package com.example.sueobmwodeudji;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.databinding.ActivityTimeTableThridBinding;
import com.example.sueobmwodeudji.dto.TimeTableDTO;
import com.example.sueobmwodeudji.rest_api.SchoolResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class TimeTableCreateActivity extends AppCompatActivity {
    public static boolean checkCall = false;
    private ActivityTimeTableThridBinding binding;
    int yearSelect, semesterSelect;

    Call<SchoolResponse> callInfo, callTimeTable;

    List<String> perioList = new ArrayList<>();
    List<String> classCntntList = new ArrayList<>();

    public static JSONObject schedule;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ccc = db.collection("시간표");
    // DocumentReference ddd = ccc.document("T3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableThridBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 툴바
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("시간표 생성");

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

/*        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grades);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.gradeSpin.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, className);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.classNameSpin.setAdapter(adapter4);*/

        binding.yearSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                yearSelect = position + 2023;
                // Log.d("년도", ""+yearSelect);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        binding.semesterSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                semesterSelect = position + 1;
                // Log.d("학기",""+semesterSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        binding.tableAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기에 addItem(binding.nameET.getText().toString());
                // SchoolCallInfo();
                ArrayList<String> list = new ArrayList<>();
                for (int k=0; k<8; k++) {
                    list.add("");
                }

                String tableName = binding.nameET.getText().toString();
                String userID = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                TimeTableDTO dto = new TimeTableDTO();
                dto.setTimeTableName(tableName);
                dto.setYear(yearSelect);
                dto.setSemester(semesterSelect);
                dto.setMon(list);
                dto.setTue(list);
                dto.setWed(list);
                dto.setThu(list);
                dto.setFri(list);
                dto.setEmail(userID);
                ccc.document(userID + " " + yearSelect + " - " + semesterSelect).set(dto);

                finish();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    // 교육청 코드와 학교 코드를 구하는 메소드
//    public void SchoolCallInfo() {
//        callInfo = NEIS_API.getInfoService().getSchoolInfo(
//                "9da752136d5849b985288deb5036dba1",
//                "json",
//                RegistrationActivity.school_name
//        );
//
//        callInfo.enqueue(new Callback<SchoolResponse>() {
//            @Override
//            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {
//                if (response.isSuccessful()) {
//                    SchoolResponse schoolInfoResponse = response.body();
//                    SchoolInfo schoolInfo = schoolInfoResponse.getSchoolInfo().get(1); //row
//                    List<Row> rows = schoolInfo.getRow();
//                    Row row = rows.get(0);
//
//                    SchoolCallTimeTable(row.getMinistryCode(), row.getShcoolCode());
//
//                    Log.d("ministryCode", row.getMinistryCode());
//                    Log.d("schoolCode", row.getShcoolCode());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SchoolResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    // 월요일 ~ 금요일까지 몇 교시에 무슨 과목인지 구하는 메소드 (class_name="1")
//    public void SchoolCallTimeTable(String ministryCode, String schoolCode) {
//
//        callTimeTable = NEIS_API.getTimeTableService().getSchoolTimeTable(
//                "9da752136d5849b985288deb5036dba1",
//                "json",
//                ministryCode,
//                schoolCode,
//                "2023",
//                "1",
//                binding.gradeSpin.getSelectedItem().toString().substring(0,1),
//                binding.classNameSpin.getSelectedItem().toString().substring(0,1),
//                "20230313",
//                "20230317"
//        );
//
//        callTimeTable.enqueue(new Callback<SchoolResponse>() {
//            @Override
//            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        SchoolResponse schoolTimeTableResponse = response.body();
//                        Log.d("response", String.valueOf(response.raw()));
//                        SchoolTimeTable schoolTimeTable = schoolTimeTableResponse.getSchoolTimeTable().get(1); // row
//                        List<Row> rows = schoolTimeTable.getRow();
//
//                        for (int i = 0; i < rows.size(); i++) {
//                            Row row = rows.get(i);
//                            perioList.add(row.getPeriority());
//                            classCntntList.add(row.getClassContent());
//                        }
//
//                        Log.d("perioList", String.valueOf(perioList));
//                        Log.d("classCntntList", String.valueOf(classCntntList));
//
//                        CreateList();
//
//                    } catch (NullPointerException e) {
//                        Log.e("ERROR", "NullPointerException");
//                        // SchoolService 값 변경
//                        SchoolCallTimeTableError(ministryCode, schoolCode);
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SchoolResponse> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    // 월요일 ~ 금요일까지 몇 교시에 무슨 과목인지 구하는 메소드 (class_name="01")
//    public void SchoolCallTimeTableError(String ministryCode, String schoolCode) {
//
//        callTimeTable = NEIS_API.getTimeTableService().getSchoolTimeTable(
//                "9da752136d5849b985288deb5036dba1",
//                "json",
//                ministryCode,
//                schoolCode,
//                "2023",
//                "1",
//                binding.gradeSpin.getSelectedItem().toString().substring(0,1),
//                "0"+binding.classNameSpin.getSelectedItem().toString().substring(0,1),
//                "20230313",
//                "20230317"
//        );
//
//        callTimeTable.enqueue(new Callback<SchoolResponse>() {
//            @Override
//            public void onResponse(Call<SchoolResponse> call, Response<SchoolResponse> response) {
//                if (response.isSuccessful()) {
//                    try {
//                        SchoolResponse schoolTimeTableResponse = response.body();
//                        Log.d("response", String.valueOf(response.raw()));
//                        SchoolTimeTable schoolTimeTable = schoolTimeTableResponse.getSchoolTimeTable().get(1); // row
//                        List<Row> rows = schoolTimeTable.getRow();
//
//                        for (int i = 0; i < rows.size(); i++) {
//                            Row row = rows.get(i);
//                            perioList.add(row.getPeriority());
//                            classCntntList.add(row.getClassContent());
//                        }
//
//                        Log.d("perioList", String.valueOf(perioList));
//                        Log.d("classCntntList", String.valueOf(classCntntList));
//
//                        CreateList();
//
//                    } catch (NullPointerException e) {
//                        Log.e("ERROR", "NullPointerException");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SchoolResponse> call, Throwable t) {
//
//            }
//        });
//
//    }

    /*
    List 구조를 만드는 메소드
    [{"day": "mon", "classCntnt": []},
        {"day": "tue", "classCntnt": []},
        {"day": "wed", "classCntnt": []},
        {"day": "thu", "classCntnt": []},
        {"day": "fri", "classCntnt": []}]
     */
//    public void CreateList() {
//        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"}; // 월요일 ~ 금요일
//        int dayNum = 0; // 0:월요일 ~ 4:금요일
//        int cnt = 0; // 첫번째 월요일 계산 용도
//
//        List<CallSchoolData> list = new ArrayList<>();
//
//        List<String> subList;
//        CallSchoolData schoolData;
//
//        for (String day:days) {
//            subList = new ArrayList<>();
//            schoolData = new CallSchoolData(day, subList);
//            list.add(schoolData);
//        }
//
//        for (int i=0; i<perioList.size(); i++) {
//            if (perioList.get(i).equals("1") && cnt!=0) {
//                dayNum++;
//            }
//            list.get(dayNum).classCntnt.add(classCntntList.get(i));
//            cnt++;
//        }
//
//        TimeTableFragment.newInstance(list);
//
//        checkCall = true;
//
//        for (CallSchoolData data : list) {
//            Log.d("TAG", data.toString());
//        }
//
//    }

}