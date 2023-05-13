package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubSearchAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubSearchBinding;
import com.example.sueobmwodeudji.model.CommunitySubSearchModel;

import java.util.LinkedList;

public class CommunitySubSearchActivity extends AppCompatActivity {
    ActivityCommunitySubSearchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
/*        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);*/

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        /*String result_text = intent.getStringExtra("query");
        result_text = "'"+result_text + "' 검색결과";
        binding.searchTv.setText(result_text);*/
        LinkedList<CommunitySubSearchModel> list = new LinkedList<>();
        list.add(new CommunitySubSearchModel("a"));
        list.add(new CommunitySubSearchModel("a"));
        list.add(new CommunitySubSearchModel("a"));

        CommunitySubSearchAdapter adapter = new CommunitySubSearchAdapter(this, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
