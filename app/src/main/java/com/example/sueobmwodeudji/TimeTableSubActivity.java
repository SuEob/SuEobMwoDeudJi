package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

        // 툴바
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}