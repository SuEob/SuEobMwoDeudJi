package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubSearchAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubSearchBinding;
import com.example.sueobmwodeudji.model.CommunitySubSearchModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.LinkedList;

import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class CommunitySubSearchActivity extends AppCompatActivity {
    ActivityCommunitySubSearchBinding binding;
    String search_text, search_word;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCommunitySubSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
/*        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);*/

        showItem();

    }

    private void showItem() {
        Intent intent = getIntent();
        search_word = intent.getStringExtra("query");
        search_text = "'"+search_word + "' 검색결과";
        binding.searchTv.setText(search_text);

        CommunitySubSearchAdapter adapter = new CommunitySubSearchAdapter(this, createCategoryQuery());
        binding.recyclerView.setAdapter(adapter);
    }

    private ArrayList<Query> createCategoryQuery(){
        String[] categorys = {"1학년 대화방", "2학년 대화방", "3학년 대화방", "게임 게시판", "공부 게시판", "운동 게시판"};
        ArrayList<Query> query = new ArrayList<>();
        for (String category : categorys) {
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            query.add(mFirestore.collection("testPost")
                    .document("first")
                    .collection(category)
                    .orderBy("title")
                    .startAt(search_word)
                    .endAt(search_word + "\uf8ff"));
        }
        return query;
    }

}