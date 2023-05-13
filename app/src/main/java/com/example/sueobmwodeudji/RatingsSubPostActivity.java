package com.example.sueobmwodeudji;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRatingsSubPostBinding;

public class RatingsSubPostActivity extends AppCompatActivity {
    ActivityRatingsSubPostBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void showItem() {
    }
}