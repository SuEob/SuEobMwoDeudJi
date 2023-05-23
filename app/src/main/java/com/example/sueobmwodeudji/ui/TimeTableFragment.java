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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.RegistrationActivity;
import com.example.sueobmwodeudji.TimeTableSubActivity;
import com.example.sueobmwodeudji.adapter.TimeTableSubAddAdapter;
import com.example.sueobmwodeudji.databinding.FragmentTimeTableBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class TimeTableFragment extends Fragment {
    private FragmentTimeTableBinding binding;
    private TimeTableSubAddAdapter adapter;

    public static TimeTableFragment getInstance() {
        return new TimeTableFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        setHasOptionsMenu(true);

        if (RegistrationActivity.schedule != null) {
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


            try {
                for (int i=0; i<days.length; i++) {
                    for (int j=0; j<RegistrationActivity.schedule.getJSONArray(days[i]).length(); j++) {
                        JSONObject object = RegistrationActivity.schedule.getJSONArray(days[i]).getJSONObject(j);
                        String classContent = object.getString("class_content");
                        timetable[i][j].setText(classContent);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

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
        Intent intent = new Intent(getContext(), TimeTableSubActivity.class);

        if (item.getItemId() == R.id.add) {
            intent.putExtra("Code", 0);
            startActivity(intent);
        } else if (item.getItemId() == R.id.list) {
            intent.putExtra("Code", 1);
            startActivity(intent);
        } else if(item.getItemId() == R.id.settings){

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