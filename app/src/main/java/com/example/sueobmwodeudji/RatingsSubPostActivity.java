package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityRatingsSubPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;

import java.util.LinkedList;

public class RatingsSubPostActivity extends AppCompatActivity {
    ActivityRatingsSubPostBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubPostBinding.inflate(getLayoutInflater());

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem() {
        Intent intent = getIntent();
        String class_name = intent.getStringExtra("class_name");
        String teacher_name = intent.getStringExtra("teacher_name");

        getSupportActionBar().setTitle(class_name);
        getSupportActionBar().setSubtitle(teacher_name);

        LinkedList<CommunitySubCommentModel> list = new LinkedList<>();
        list.add(new CommunitySubCommentModel("s","a"));
        list.add(new CommunitySubCommentModel("s","a"));
        list.add(new CommunitySubCommentModel("s","a"));

        CommunitySubCommentAdapter adapter = new CommunitySubCommentAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}