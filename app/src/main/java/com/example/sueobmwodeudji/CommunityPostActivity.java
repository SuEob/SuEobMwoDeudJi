package com.example.sueobmwodeudji;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityCommunityPostBinding;

public class CommunityPostActivity extends AppCompatActivity {
    ActivityCommunityPostBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
