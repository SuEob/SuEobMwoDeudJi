package com.example.sueobmwodeudji;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityTimeTableThridBinding;


public class TimeTableThridActivity extends AppCompatActivity {
    private ActivityTimeTableThridBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableThridBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final String[] years = {"2023년"};
        final String[] semesters = {"1학기", "2학기"};
        final String[] grades = {"1학년", "2학년", "3학년"};
        final String[] className = {"1반", "2반", "3반", "4반", "5반", "6반", "7반", "8반", "9반", "10반"};

        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.yearSpin.setAdapter(adapter1);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, semesters);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.semesterSpin.setAdapter(adapter2);

        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grades);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.gradeSpin.setAdapter(adapter3);

        ArrayAdapter adapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, className);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.classNameSpin.setAdapter(adapter4);

//        binding.yearSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("고름", years[i]);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                Log.d("안고름", "안고름");
//            }
//        });
//
//        binding.semesterSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("고름", semesters[i]);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                Log.d("안고름", "안고름");
//            }
//        });

        binding.tableAddBtn.setOnClickListener(v -> {
//            SchoolCallTimeTable();
            finish();
        });

    }


}