package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.RatingsSubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityRatingsSubListBinding;
import com.example.sueobmwodeudji.model.RatingsSubListModel;

import java.util.LinkedList;

public class RatingsSubListActivity extends AppCompatActivity {
    private ActivityRatingsSubListBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubListBinding.inflate(getLayoutInflater());

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String class_name = intent.getStringExtra("class_name");
        String teacher_name = intent.getStringExtra("teacher_name");

        getSupportActionBar().setTitle(class_name);
        getSupportActionBar().setSubtitle(teacher_name);

        LinkedList<RatingsSubListModel> list = new LinkedList<>();
        list.add(new RatingsSubListModel("제목1", "평가 내용1"));
        list.add(new RatingsSubListModel("제목2", "평가 내용2"));
        list.add(new RatingsSubListModel("제목3", "평가 내용3"));
        list.add(new RatingsSubListModel("제목3", "평가 내용3"));
        list.add(new RatingsSubListModel("제목3", "평가 내용3"));
        list.add(new RatingsSubListModel("제목3", "평가 내용3"));
        list.add(new RatingsSubListModel("제목3", "평가 내용3"));
        list.add(new RatingsSubListModel("제목3", "평가 내용3"));

        RatingsSubListAdapter adapter = new RatingsSubListAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
