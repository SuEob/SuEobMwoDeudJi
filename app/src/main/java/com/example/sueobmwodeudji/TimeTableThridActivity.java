package com.example.sueobmwodeudji;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityTimeTableThridBinding;


public class TimeTableThridActivity extends AppCompatActivity {
    private ActivityTimeTableThridBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableThridBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String[] years = {"2023년", "2024년", "2025년", "2026년", "2027년"};
        final String[] semesters = {"1학기", "2학기"};
        Spinner spinner1 = findViewById(R.id.year_spin);
        Spinner spinner2 = findViewById(R.id.semester_spin);

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, semesters);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("고름", years[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("안고름", "안고름");
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("고름", semesters[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("안고름", "안고름");
            }
        });

    }
}