package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.RatingsSubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityRatingsSubListBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RatingsSubListActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityRatingsSubListBinding binding;
    private String mSubject;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubListBinding.inflate(getLayoutInflater());

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(this);

        //createRating();
        showItem();
    }

    private void showItem(){
        Intent intent = getIntent();
        String class_name = intent.getStringExtra("class_name");
        //String teacher_name = intent.getStringExtra("teacher_name");

        mSubject = class_name;

        getSupportActionBar().setTitle(class_name);
        //getSupportActionBar().setSubtitle(teacher_name);


        RatingsSubListAdapter adapter = new RatingsSubListAdapter(this, readPostData());
        adapter.setOrcl(new RatingsSubListAdapter.OnRatingClickListener() {
            @Override
            public void onClick(RatingsSubListModel data) {
                Intent intent = new Intent(getApplicationContext(), RatingsSubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("class_name", class_name);
                //intent.putExtra("teacher_name", teacher_name);
                startActivity(intent);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private Query readPostData(){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        return mFirestore.collection("testRating")
                .document("first")
                .collection(mSubject)
                .orderBy("timestamp",Query.Direction.DESCENDING)
                .limit(10);
    }
    private void createRating(){
        String docName = "4";
        RatingsSubListModel data = createData(docName);

        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("testRating")
                .document("first")
                .collection("수업코드")
                .document(docName)
                .set(data);
    }
    private RatingsSubListModel createData(String docName){
        RatingsSubListModel data = new RatingsSubListModel();
        data.setContent("글내용" + docName);
        //data.setName("작성자" + docName);
        data.setTimestamp(Timestamp.now().toDate());
        data.setTitle("글제목" + docName);
        data.setDifficulty("상");
        data.setType("유인물 위주");
        data.setHoney(true);
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
        Intent intent = new Intent(getApplicationContext(), RatingsSubFormActivity.class);
        intent.putExtra("class_name", mSubject);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
