package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.adapter.CommunitySearchAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySearchBinding;
import com.example.sueobmwodeudji.model.CommunitySearchModel;

import java.util.LinkedList;

public class CommunitySearchActivity extends AppCompatActivity {
    ActivityCommunitySearchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String result_text = intent.getStringExtra("query");
        result_text = "'"+result_text + "' 검색결과";
        binding.searchTv.setText(result_text);
        LinkedList<CommunitySearchModel> list = new LinkedList<>();
        list.add(new CommunitySearchModel("a"));
        list.add(new CommunitySearchModel("a"));
        list.add(new CommunitySearchModel("a"));

        CommunitySearchAdapter adapter = new CommunitySearchAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
