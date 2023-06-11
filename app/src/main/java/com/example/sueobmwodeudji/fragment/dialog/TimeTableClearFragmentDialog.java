package com.example.sueobmwodeudji.fragment.dialog;

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

import com.example.sueobmwodeudji.databinding.FragmentTimeTableClearDialogBinding;

public class TimeTableClearFragmentDialog extends DialogFragment {
    private FragmentTimeTableClearDialogBinding binding;
    private int day_of_week_position;
    private int period_position;

    public interface ClearContentInterface {
        void onClick(int day_of_week, int period);
    }

    public ClearContentInterface clearContentInterface;

    public void setClearContentInterface(ClearContentInterface clearContentInterface) {
        this.clearContentInterface = clearContentInterface;
    }

    public static TimeTableClearFragmentDialog getInstance() { return new TimeTableClearFragmentDialog(); }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
       binding = FragmentTimeTableClearDialogBinding.inflate(inflater, container, false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String[] day_of_week = {"월요일", "화요일", "수요일", "목요일", "금요일"};
        final String[] period = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8교시"};

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

        ArrayAdapter adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, period);
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

        binding.dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "입력완료", Toast.LENGTH_SHORT).show();
                clearContentInterface.onClick(day_of_week_position, period_position);
                getDialog().dismiss();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
