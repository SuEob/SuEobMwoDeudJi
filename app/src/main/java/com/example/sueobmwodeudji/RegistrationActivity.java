package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registrationBTN.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 파이어베이스 값 저장
    }
}

