package com.example.sueobmwodeudji;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRatingsSubFormBinding;

public class RatingsSubFormActivity extends AppCompatActivity {
    ActivityRatingsSubFormBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void showItem() {
    }
}