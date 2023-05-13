package com.example.sueobmwodeudji;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityCommunitySubFormBinding;

public class CommunitySubFormActivity  extends AppCompatActivity {
    ActivityCommunitySubFormBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void showItem() {
    }
}