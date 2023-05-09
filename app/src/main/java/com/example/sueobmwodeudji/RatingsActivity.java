package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRatingsListBinding;

public class RatingsActivity extends AppCompatActivity {
    private ActivityRatingsListBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String class_name = intent.getStringExtra("class_name");
        String teacher_name = intent.getStringExtra("teacher_name");

        binding.classTv.setText(class_name);
        binding.teacherTv.setText(teacher_name);
    }
}
