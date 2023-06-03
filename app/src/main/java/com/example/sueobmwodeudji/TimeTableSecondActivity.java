package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.TimeTableSubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableSecondBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;

import java.util.LinkedList;
import java.util.List;

public class TimeTableSecondActivity extends AppCompatActivity {
    private ActivityTimeTableSecondBinding binding;
    private List<TimeTableSubFrameModel> list = new LinkedList<TimeTableSubFrameModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 툴바
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        TimeTableListView();

    }

    private void TimeTableListView() {
        list.add(new TimeTableSubFrameModel("기본 시간표"));
        binding.timeTableSubList.setLayoutManager(new LinearLayoutManager(this));
        binding.timeTableSubList.setAdapter(new TimeTableSubListAdapter(this, list));
    }

    // 툴바 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.time_table_sub_tool_bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add:
                Intent intent = new Intent(this, TimeTableThridActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}