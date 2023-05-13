package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;

import java.util.LinkedList;

public class CommunitySubPostActivity extends AppCompatActivity {
    ActivityCommunitySubPostBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        binding.subjectTv.setText(subject);
        getSupportActionBar().setTitle(subject);


        LinkedList<CommunitySubCommentModel> list = new LinkedList<>();
        list.add(new CommunitySubCommentModel("s"));
        list.add(new CommunitySubCommentModel("s"));
        list.add(new CommunitySubCommentModel("s"));

        CommunitySubCommentAdapter adapter = new CommunitySubCommentAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
