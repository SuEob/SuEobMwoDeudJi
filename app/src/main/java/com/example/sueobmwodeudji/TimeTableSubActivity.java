package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityTimeTableSubBinding;
import com.example.sueobmwodeudji.ui.sub_ui.TimeTableAddFragment;
import com.example.sueobmwodeudji.ui.sub_ui.TimeTableListFragment;

public class TimeTableSubActivity extends AppCompatActivity {
    private ActivityTimeTableSubBinding binding;
    private int code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableSubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TimeTableSubView();
    }

    private void TimeTableSubView() {
        Intent intent = getIntent();
        code = intent.getIntExtra("Code", 0);

        if (code == 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.time_table_sub_container, new TimeTableAddFragment()).commit();
        else if (code == 1)
            getSupportFragmentManager().beginTransaction().replace(R.id.time_table_sub_container, new TimeTableListFragment()).commit();
    }
}