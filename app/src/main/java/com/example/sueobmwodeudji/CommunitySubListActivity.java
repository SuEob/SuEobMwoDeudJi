package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CommunitySubListActivity extends AppCompatActivity implements EventListener<QuerySnapshot> {
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

        //readPostData().addSnapshotListener(this);


        //createPost();

//        CommunitySubListAdapter adapter = new CommunitySubListAdapter(this, list);
//        binding.recyclerView.setAdapter(adapter);
    }
    private Query readPostData(){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        return mFirestore.collection("testPost")
                .document("first")
                .collection("게시판1")
                .orderBy("timestamp",Query.Direction.DESCENDING)
                .limit(10);
    }

    private void createPost(){
        String docName = "2";
        Map<String, Object> data = new HashMap<>();
        data.put("content", "글내용" + docName);
        data.put("name", "작성자" + docName);
        data.put("timestamp", Timestamp.now().toDate());
        data.put("title", "글제목" + docName);

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("testPost")
                .document("first")
                .collection("게시판1")
                .document(docName)
                .set(data);
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e != null){
            Log.w("list 에러","onEvent:error", e);
        }

        Log.d("list onEvent", "size : " + documentSnapshots.getDocuments());
        for(DocumentSnapshot doc : documentSnapshots.getDocuments()){
            CommunitySubListModel model = doc.toObject(CommunitySubListModel.class);
        }
    }
}
