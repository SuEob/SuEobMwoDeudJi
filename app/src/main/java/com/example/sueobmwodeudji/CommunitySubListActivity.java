package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubListBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CommunitySubListActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityCommunitySubListBinding binding;
    private String subject;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubListBinding.inflate(getLayoutInflater());
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(this);

        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");

        getSupportActionBar().setTitle(subject);

        CommunitySubListAdapter adapter = new CommunitySubListAdapter(this, readPostData());
        binding.recyclerView.setAdapter(adapter);
        //readPostData().addSnapshotListener(this);
        //createPost();
        //deleteData();

//        CommunitySubListAdapter adapter = new CommunitySubListAdapter(this, list);
//        binding.recyclerView.setAdapter(adapter);
    }
    private Query readPostData(){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        return mFirestore.collection("testPost")
                .document("first")
                .collection("1학년 게시판")
                .orderBy("timestamp",Query.Direction.DESCENDING)
                .limit(10);
    }

    private void createPost(){
        String docName = "1";
        CommunitySubListModel data = createData(docName);

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("게시글")
                .document("첫 고등학교")
                .collection("2학년 게시판")
                .document(docName)
                .set(data);
    }
    private CommunitySubListModel createData(String docName){
        CommunitySubListModel data = new CommunitySubListModel();
        data.setContent("글내용" + docName);
        data.setName("작성자" + docName);
        data.setTimestamp(Timestamp.now().toDate());
        data.setTitle("글제목" + docName);
        Map<String, Boolean> like = new HashMap<>();
        like.put("a",true);
        like.put("b",false);
        ArrayList<CommunitySubCommentModel> d = new ArrayList<>();
        ArrayList<CommunitySubCommentCommentModel> dd = new ArrayList<>();
        dd.add(new CommunitySubCommentCommentModel("작성자1","댓글내용1",Timestamp.now().toDate(), like));
        dd.add(new CommunitySubCommentCommentModel("작성자2","댓글내용2",Timestamp.now().toDate(), like));
        d.add(new CommunitySubCommentModel("작성자1","댓글내용1",Timestamp.now().toDate(), like, dd));
        d.add(new CommunitySubCommentModel("작성자2","댓글내용2",Timestamp.now().toDate(), like, dd));
        data.setComments(d);

        return data;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), CommunitySubFormActivity.class);
        intent.putExtra("subject", subject);
        startActivity(intent);
    }
//    private void deleteData(){
//        String docName = "3";
//        CommunitySubListModel data = createData(docName);
//
//        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
//        mFirestore.collection("testPost")
//                .document("first")
//                .collection("게시판1")
//                .document(docName)
//                .delete();
//    }
}
