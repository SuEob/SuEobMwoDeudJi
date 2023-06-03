package com.example.sueobmwodeudji;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivitySearchBinding;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableFourthBinding;

public class TimeTableFourthActivity extends AppCompatActivity {
    ActivityTimeTableFourthBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableFourthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String[] periods = {"1교시", "2교시", "3교시", "4교시", "5교시", "6교시", "7교시", "8학년"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, periods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.periodSpin.setAdapter(adapter);
    }
}