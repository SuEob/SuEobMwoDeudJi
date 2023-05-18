package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;

import java.util.LinkedList;

public class CommunitySubPostActivity extends AppCompatActivity {
    private ActivityCommunitySubPostBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubPostBinding.inflate(getLayoutInflater());
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(binding.getRoot());
        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        CommunitySubListModel data = (CommunitySubListModel) intent.getSerializableExtra("data");
        getSupportActionBar().setTitle(data.getTitle());

        CommunitySubCommentAdapter adapter = new CommunitySubCommentAdapter(this, data);
        binding.recyclerView.setAdapter(adapter);
    }
}
