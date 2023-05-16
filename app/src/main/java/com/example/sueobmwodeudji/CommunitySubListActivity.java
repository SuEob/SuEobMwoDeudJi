package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;

import java.util.LinkedList;

public class CommunitySubListActivity extends AppCompatActivity {
    private ActivityCommunitySubListBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubListBinding.inflate(getLayoutInflater());
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");


        getSupportActionBar().setTitle(subject);

        LinkedList<CommunitySubListModel> list = new LinkedList<>();
        list.add(new CommunitySubListModel("제목1"));
        list.add(new CommunitySubListModel("제목2"));
        list.add(new CommunitySubListModel("제목3"));
        list.add(new CommunitySubListModel("제목3"));
        list.add(new CommunitySubListModel("제목3"));
        list.add(new CommunitySubListModel("제목3"));

        CommunitySubListAdapter adapter = new CommunitySubListAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
