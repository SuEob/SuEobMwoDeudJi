package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.TimeTableClassActivity;
import com.example.sueobmwodeudji.TimeTableSecondActivity;
import com.example.sueobmwodeudji.TimeTableThridActivity;
import com.example.sueobmwodeudji.databinding.FragmentTimeTableBinding;
import com.example.sueobmwodeudji.dto.CallSchoolData;
import com.example.sueobmwodeudji.dto.TimeTableDTO;
import com.example.sueobmwodeudji.ui.dialog_ui.TimeTableClearFragmentDialog;
import com.example.sueobmwodeudji.ui.dialog_ui.TimeTableFragmentDialog;
import com.example.sueobmwodeudji.ui.dialog_ui.TimeTableNameFragmentDialog;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimeTableFragment extends Fragment {
    private FragmentTimeTableBinding binding;
    private final String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"}; // 월요일 ~ 금요일
    private static String class_name; // 수업명
    private static int day_of_week_position; // 요일
    private static int period_position; // 교시

    // 프래그먼트 바뀔때마다 리셋되서 값 고정
    private static Bundle args;
    private static final String TIMETABLE_DATA = "dataList";
    public static TextView[][] timetable;

    private int mYear = 0;
    private int mSemester = 0;

    public static TimeTableFragment getInstance() {
        return new TimeTableFragment();
    }

    public static TimeTableFragment newInstance(List<CallSchoolData> dataList) {
        TimeTableFragment fragment = new TimeTableFragment();
        args = new Bundle();
        args.putSerializable("dataList", (Serializable) dataList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        //CreateList();
        setTimeTable();

        // 비우기
        binding.clearBtn.setOnClickListener(v -> {
            TimeTableClearFragmentDialog dialog = TimeTableClearFragmentDialog.getInstance();
            dialog.setClearContentInterface(new TimeTableClearFragmentDialog.ClearContentInterface() {
                @Override
                public void onClick(int day_of_week, int period) {
                    timetable[day_of_week][period].setText("");
                    if (args != null) { // 시간표가 이미 작성된 상태에서 추가
                        List<CallSchoolData> dataList = (List<CallSchoolData>) args.getSerializable(TIMETABLE_DATA);
                        dataList.get(day_of_week).classCntnt.set(period, "");

                        for (CallSchoolData data : dataList) {
                            Log.d("TAG", data.toString());
                        }

                    }
                }
            });
            dialog.show(getChildFragmentManager(), "TAG");
        });

        TimeTableThridActivity.checkCall = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        // addFullTime();
        drowTable();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.time_table_tool_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            addPartTime();
        } else if (item.getItemId() == R.id.list) {
            Intent intent = new Intent(getContext(), TimeTableSecondActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    // TextView[][] timetable -> 시간표 초기화
    private void setTimeTable() {
        timetable = new TextView[5][];
        timetable[0] = new TextView[]{ // 월요일
                binding.timeTable.monday1,
                binding.timeTable.monday2,
                binding.timeTable.monday3,
                binding.timeTable.monday4,
                binding.timeTable.monday5,
                binding.timeTable.monday6,
                binding.timeTable.monday7,
                binding.timeTable.monday8
        };

        timetable[1] = new TextView[]{ // 화요일
                binding.timeTable.tuesday1,
                binding.timeTable.tuesday2,
                binding.timeTable.tuesday3,
                binding.timeTable.tuesday4,
                binding.timeTable.tuesday5,
                binding.timeTable.tuesday6,
                binding.timeTable.tuesday7,
                binding.timeTable.tuesday8
        };

        timetable[2] = new TextView[]{ // 수요일
                binding.timeTable.wednesday1,
                binding.timeTable.wednesday2,
                binding.timeTable.wednesday3,
                binding.timeTable.wednesday4,
                binding.timeTable.wednesday5,
                binding.timeTable.wednesday6,
                binding.timeTable.wednesday7,
                binding.timeTable.wednesday8
        };

        timetable[3] = new TextView[]{ // 목요일
                binding.timeTable.thursday1,
                binding.timeTable.thursday2,
                binding.timeTable.thursday3,
                binding.timeTable.thursday4,
                binding.timeTable.thursday5,
                binding.timeTable.thursday6,
                binding.timeTable.thursday7,
                binding.timeTable.thursday8
        };

        timetable[4] = new TextView[]{ // 금요일
                binding.timeTable.friday1,
                binding.timeTable.friday2,
                binding.timeTable.friday3,
                binding.timeTable.friday4,
                binding.timeTable.friday5,
                binding.timeTable.friday6,
                binding.timeTable.friday7,
                binding.timeTable.friday8
        };
    }

    // 시간표 전체 추가
    private void addFullTime() {
        // 새로운 시간표 추가 + 시간표 DTO의 값 존재
        if (TimeTableThridActivity.checkCall && args != null) {
            List<CallSchoolData> dataList = (List<CallSchoolData>) args.getSerializable(TIMETABLE_DATA);

            // TextView[][] timetable -> 시간표에 값 넣기
            for (int i = 0; i < days.length; i++) {
                for (int j = 0; j < dataList.get(i).classCntnt.size(); j++) {
                    String classContent = dataList.get(i).classCntnt.get(j);
                    timetable[i][j].setText(classContent);
                }
            }

            // 새로운 시간표 1번만
            TimeTableThridActivity.checkCall = false;
        }
    }

    // 시간표 부분 추가
    public void addPartTime() {
        if(mYear == 0) {
            Toast.makeText(getActivity(), "시간표가 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        // CallBack으로 Listener 만들기
        TimeTableFragmentDialog dialog = TimeTableFragmentDialog.getInstance();
//        dialog.setTimeTableInterface(new TimeTableFragmentDialog.TimeTableInterface() {
//            @Override
//            public void onClick(String class_name, int day_of_week, int period) {
//                // 시간표에 값 넣기
//                timetable[day_of_week][period].setText(class_name);
//                // DTO에 값 넣기
//                if (args != null) { // 시간표가 이미 작성된 상태에서 추가
//                    ArrayList<CallSchoolData> dataList = (List<CallSchoolData>) args.getSerializable(TIMETABLE_DATA);
//                    dataList.get(day_of_week).classCntnt.set(period, class_name);
//
//                    //for (CallSchoolData data : dataList) {
//                    //    Log.d("TAG", data.toString());
//                    //}
//
//                } else { // 시간표가 비어있는 상태에서 추가
//                    String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"}; // 월요일 ~ 금요일
//
//                    ArrayList<CallSchoolData> list = new ArrayList<>();
//
//                    ArrayList<String> subList;
//                    CallSchoolData schoolData;
//
//                    // List<CallSchoolData> 초기화
//                    for (String day : days) {
//                        subList = new ArrayList<>();
//                        schoolData = new CallSchoolData(day, subList);
//                        list.add(schoolData);
//                    }
//
//                }
//                else { // 시간표가 비어있는 상태에서 추가
//                    String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"}; // 월요일 ~ 금요일
//
//                    List<CallSchoolData> list = new ArrayList<>();
//
//                    List<String> subList;
//                    CallSchoolData schoolData;
//
//                    // List<CallSchoolData> 초기화
//                    for (String day:days) {
//                        subList = new ArrayList<>();
//                        schoolData = new CallSchoolData(day, subList);
//                        list.add(schoolData);
//                    }
//
//                    // 빈 값 넣기
//                    for (int i=0; i<5; i++) {
//                        for (int j=0; j<8; j++) {
//                            list.get(i).classCntnt.add("");
//                        }
//                    }
//
//                    list.get(day_of_week).classCntnt.set(period, class_name);
//
//                    TimeTableFragment.newInstance(list);
//
//                    for (CallSchoolData data : list) {
//                        Log.d("TAG", data.toString());
//                    }
//                }
                // 빈 값 넣기
//                    for (int i=0; i<5; i++) {
//                        for (int j=0; j<8; j++) {
//                            list.get(i).classCntnt.add("");
//                        }
//                    }
//
//                    list.get(day_of_week).classCntnt.set(period, class_name);
//
//                    TimeTableFragment.newInstance(list);

                //for (CallSchoolData data : list) {
                //    Log.d("TAG", data.toString());
                //}
//                }
//            }
//            }
//        });
        dialog.setDdd_year(mYear);
        dialog.setDdd_semester(mSemester);
        dialog.show(getChildFragmentManager(), "TAG");
//
    }

    public void drowTable() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ccc = db.collection("시간표");
        String userID = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        ccc.whereEqualTo("email", userID).whereEqualTo("selected", true).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot value) {
                if (!value.getDocuments().isEmpty()) {
                    TimeTableDTO dto = value.getDocuments().get(0).toObject(TimeTableDTO.class);
                    ArrayList<ArrayList<String>> sueobs = new ArrayList<>();
                    mYear = dto.getYear();
                    mSemester = dto.getSemester();
                    sueobs.add(dto.getMon());
                    sueobs.add(dto.getTue());
                    sueobs.add(dto.getWed());
                    sueobs.add(dto.getThu());
                    sueobs.add(dto.getFri());
                    ((MainActivity) getActivity()).getSupportActionBar().setTitle(dto.getTimeTableName());
                    for (int i = 0; i < 5; i++) {
                        for (int k = 0; k < sueobs.get(i).size(); k++) {
                            timetable[i][k].setText(sueobs.get(i).get(k));
                        }
                    }
                    // 학년/반
                    binding.gradeClassBtn.setOnClickListener(v -> {
                        Intent intent = new Intent(getContext(), TimeTableClassActivity.class);
                        intent.putExtra("data", dto);
                        startActivity(intent);
                    });

                    // 이름변경
                    binding.changeNameBtn.setOnClickListener(v -> {
                        TimeTableNameFragmentDialog dialog = TimeTableNameFragmentDialog.getInstance();
                        dialog.setChangeName(new TimeTableNameFragmentDialog.ChangeNameInterface() {
                            @Override
                            public void onClick(String name) {
                                FirebaseFirestore f = FirebaseFirestore.getInstance();
                                f.collection("시간표")
                                        .document("a@a.com " + dto.getYear() + " - " + dto.getSemester())
                                        .update("timeTableName", name);
                            }
                        });
                        dialog.show(getChildFragmentManager(), "TAG");
                    });
                }
                else{
                    binding.gradeClassBtn.setOnClickListener(v -> {
                        Toast.makeText(getActivity(), "시간표가 없습니다.", Toast.LENGTH_SHORT).show();
                    });

                    // 이름변경
                    binding.changeNameBtn.setOnClickListener(v -> {
                        Toast.makeText(getActivity(), "시간표가 없습니다.", Toast.LENGTH_SHORT).show();
                    });
                }
                }
        });
    }

}