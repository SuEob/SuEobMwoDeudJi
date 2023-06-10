package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubListAdapter;
import com.example.sueobmwodeudji.adapter.CommunitySubSearchAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubSearchBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.CommunitySubSearchModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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

    private String mCollection, mSchool, mEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubSearchBinding.inflate(getLayoutInflater());

        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        setContentView(binding.getRoot());
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);

        // DB PATH Setting
        dbPathSetting();



    }

    private void dbPathSetting() {
        mCollection = "게시판";
        readSchool();
    }

    private void readSchool() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("사용자")
                .document(mEmail)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        mSchool = documentSnapshot.getString("school_name");
                        showItem();
                    }
                });
    }

    private void showItem() {
        Intent intent = getIntent();
        search_word = intent.getStringExtra("query");
        search_text = "'"+search_word + "' 검색결과";
        getSupportActionBar().setTitle(search_text);

        CommunitySubSearchAdapter adapter = new CommunitySubSearchAdapter(this, createCategoryQuery());
        adapter.setOpcl(new CommunitySubListAdapter.OnPostClickListener() {
            @Override
            public void onClick(CommunitySubListModel data) {
                Intent intent = new Intent(getApplicationContext(), CommunitySubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("subject", data.getCategory());
                startActivity(intent);
            }
        });
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