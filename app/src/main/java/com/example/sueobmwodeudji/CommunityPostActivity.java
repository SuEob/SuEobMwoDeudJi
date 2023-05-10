package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.adapter.CommunityCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunityPostBinding;
import com.example.sueobmwodeudji.model.CommunityCommentModel;

import java.util.LinkedList;

public class CommunityPostActivity extends AppCompatActivity {
    ActivityCommunityPostBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        binding.subjectTv.setText(subject);

        LinkedList<CommunityCommentModel> list = new LinkedList<>();
        list.add(new CommunityCommentModel("s"));
        list.add(new CommunityCommentModel("s"));
        list.add(new CommunityCommentModel("s"));

        CommunityCommentAdapter adapter = new CommunityCommentAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
