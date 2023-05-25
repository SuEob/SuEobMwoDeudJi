package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRegistrationBinding;

public class RegistrationActivity extends AppCompatActivity {
    private ActivityRegistrationBinding binding;

    public static String school_name;

//    public static JSONObject schedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.registrationBTN.setOnClickListener(view -> {
//            if (schedule != null) {
//                //
//            }
            school_name = "용문고등학교";
            // school_name = binding.registrationSchool.getText().toString();

            Intent intent = new Intent(this, LoginActivity.class);
            setResult(RESULT_OK, intent);
            finish();
        });

    }

}

