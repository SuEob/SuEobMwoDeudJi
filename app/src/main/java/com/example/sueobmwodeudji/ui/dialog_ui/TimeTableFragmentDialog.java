package com.example.sueobmwodeudji.ui.dialog_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sueobmwodeudji.databinding.FragmentTimeTableDialogBinding;

public class TimeTableFragmentDialog extends DialogFragment {
    private FragmentTimeTableDialogBinding binding;
    private int day_of_week_position;
    private int period_position;

    // 리스너를 만들기 위한 interface
    public interface TimeTableInterface {
        void onClick(String class_name, int day_of_week, int period);
    }
    private TimeTableInterface timeTableInterface;

    public void setTimeTableInterface(TimeTableInterface timeTableInterface) {
        this.timeTableInterface = timeTableInterface;
    }

    public static TimeTableFragmentDialog getInstance() {
        return new TimeTableFragmentDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true); // true -> 뒤로가기 버튼 or dialog 뒷배경 터치시 Dialog 종료
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentTimeTableDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] day_of_week = {"월요일", "화요일", "수요일", "목요일", "금요일"};
        final String[] period = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시", };

        // 요일 다이얼로그
        ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, day_of_week);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.dayOfWeekSpin.setAdapter(adapter1);

        binding.dayOfWeekSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day_of_week_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 교시 다이얼로그
        ArrayAdapter adapter2 = new ArrayAdapter(getContext().getApplicationContext(), android.R.layout.simple_spinner_item, period);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.periodSpin.setAdapter(adapter2);

        binding.periodSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                period_position = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 부모 프래그먼트(TimeTableFragment)에 데이터 전송
        binding.dialogBtn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "입력완료", Toast.LENGTH_SHORT).show();

            String value = binding.classNameEdit.getText().toString();
            timeTableInterface.onClick(value, day_of_week_position, period_position);

            getDialog().dismiss();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}