package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.adapter.CommunityListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunityListBinding;
import com.example.sueobmwodeudji.databinding.ActivityMainBinding;
import com.example.sueobmwodeudji.decoration.RatingsRecyclerViewDecoration;
import com.example.sueobmwodeudji.model.CommunityListModel;
import com.example.sueobmwodeudji.model.RatingsListModel;

import java.util.LinkedList;

public class CommunityActivity extends AppCompatActivity {
    private ActivityCommunityListBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String subject = intent.getStringExtra("subject");
        binding.subjectTv.setText(subject);

        LinkedList<CommunityListModel> list = new LinkedList<>();
        list.add(new CommunityListModel("제목1"));
        list.add(new CommunityListModel("제목2"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));
        list.add(new CommunityListModel("제목3"));

        CommunityListAdapter adapter = new CommunityListAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.addItemDecoration(new RatingsRecyclerViewDecoration(40));
    }
}
