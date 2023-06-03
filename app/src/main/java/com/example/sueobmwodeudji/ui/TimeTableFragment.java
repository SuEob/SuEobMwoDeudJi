package com.example.sueobmwodeudji.ui;

import android.content.DialogInterface;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.TimeTableSecondActivity;
import com.example.sueobmwodeudji.TimeTableThridActivity;
import com.example.sueobmwodeudji.databinding.FragmentTimeTableBinding;
import com.example.sueobmwodeudji.dto.CallSchoolData;
import com.example.sueobmwodeudji.ui.sub_ui.TimeTableFragmentDialog;

import java.io.Serializable;
import java.util.List;

public class TimeTableFragment extends Fragment {
    private FragmentTimeTableBinding binding;

    private static Bundle args;
    private static final String TIMETABLE_DATA = "dataList";


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        // 새로 추가한 데이터값을 받아옴 + TimeTableThridActivity.checkCall 이 false가 됨 흠..
        if (TimeTableThridActivity.checkCall && args != null) {
            List<CallSchoolData> dataList = (List<CallSchoolData>) args.getSerializable(TIMETABLE_DATA);

            String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"}; // 월요일 ~ 금요일

            TextView[][] timetable  = new TextView[5][];
            timetable[0] = new TextView[] {
                    binding.timeTable.monday1,
                    binding.timeTable.monday2,
                    binding.timeTable.monday3,
                    binding.timeTable.monday4,
                    binding.timeTable.monday5,
                    binding.timeTable.monday6,
                    binding.timeTable.monday7,
                    binding.timeTable.monday8
            };

            timetable[1] = new TextView[] {
                    binding.timeTable.tuesday1,
                    binding.timeTable.tuesday2,
                    binding.timeTable.tuesday3,
                    binding.timeTable.tuesday4,
                    binding.timeTable.tuesday5,
                    binding.timeTable.tuesday6,
                    binding.timeTable.tuesday7,
                    binding.timeTable.tuesday8
            };

            timetable[2] = new TextView[] {
                    binding.timeTable.wednesday1,
                    binding.timeTable.wednesday2,
                    binding.timeTable.wednesday3,
                    binding.timeTable.wednesday4,
                    binding.timeTable.wednesday5,
                    binding.timeTable.wednesday6,
                    binding.timeTable.wednesday7,
                    binding.timeTable.wednesday8
            };

            timetable[3] = new TextView[] {
                    binding.timeTable.thursday1,
                    binding.timeTable.thursday2,
                    binding.timeTable.thursday3,
                    binding.timeTable.thursday4,
                    binding.timeTable.thursday5,
                    binding.timeTable.thursday6,
                    binding.timeTable.thursday7,
                    binding.timeTable.thursday8
            };

            timetable[4] = new TextView[] {
                    binding.timeTable.friday1,
                    binding.timeTable.friday2,
                    binding.timeTable.friday3,
                    binding.timeTable.friday4,
                    binding.timeTable.friday5,
                    binding.timeTable.friday6,
                    binding.timeTable.friday7,
                    binding.timeTable.friday8
            };

            for (int i=0; i<days.length; i++) {
                for (int j=0; j<dataList.get(i).classCntnt.size(); j++) {
                    String classContent = dataList.get(i).classCntnt.get(j);
                    timetable[i][j].setText(classContent);
                }
            }

            // onResume()으로 인한 메모리 낭비 방지
            TimeTableThridActivity.checkCall = false;

        }

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
            new TimeTableFragmentDialog().show(
                    getActivity().getSupportFragmentManager(), "Dialog"
            );
        } else if (item.getItemId() == R.id.list) {
            Intent intent = new Intent(getContext(), TimeTableSecondActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    public void dialogClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("시간표 삭제").setMessage("시간표를 삭제하시겠습니까?");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d ("성공", "눌림");
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d ("실패", "눌림");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}