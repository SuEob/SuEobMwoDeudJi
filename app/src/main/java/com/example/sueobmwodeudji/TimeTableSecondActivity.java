package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.TimeTableSubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableSecondBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TimeTableSecondActivity extends AppCompatActivity {
    private ActivityTimeTableSecondBinding binding;
    private List<TimeTableSubFrameModel> list = new LinkedList<TimeTableSubFrameModel>();

    private String mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // 툴바
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        TimeTableListView();
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

    private void TimeTableListView() {
        //list.add(new TimeTableSubFrameModel("기본 시간표"));
        binding.timeTableSubList.setLayoutManager(new LinearLayoutManager(this));
        TimeTableSubListAdapter adapter = new TimeTableSubListAdapter(this, readTimeTable());
        //adapter.setOlcl();
        binding.timeTableSubList.setAdapter(adapter);
    }

    private Query readTimeTable() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        return mFirestore.collection("시간표")
                .whereEqualTo("email", mEmail);
    }


}