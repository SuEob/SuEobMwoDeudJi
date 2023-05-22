package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityRatingsSubPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RatingsSubPostActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityRatingsSubPostBinding binding;

    private Intent intent;
    private RatingsSubListModel data;
    private InputMethodManager imm;
    private String class_name, teacher_name;

    private String firstCP, firstDP, secondCP;
    private String subject, id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubPostBinding.inflate(getLayoutInflater());

        intent = getIntent();

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /********** DB Path Setting **********/
        firstCP = "testRating";
        firstDP = "first";
        //secondCP = "모바일캡스톤조범석";

        binding.submitBtn.setOnClickListener(this);

        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem() {
        data = (RatingsSubListModel) intent.getSerializableExtra("data");
        class_name = intent.getStringExtra("class_name");
        teacher_name = intent.getStringExtra("teacher_name");

        secondCP = class_name + teacher_name;

        getSupportActionBar().setTitle(class_name);
        getSupportActionBar().setSubtitle(teacher_name);

        CommunitySubCommentAdapter adapter = new CommunitySubCommentAdapter(this, readData());
        adapter.setOclp(new CommunitySubCommentAdapter.OnCocommentPositiveListener() {
            @Override
            public void onPositive(int positon) {
                binding.commentEt.setHint("대댓글을 입력하세요.");
                binding.commentEt.setText(null);
                binding.submitBtn.setOnClickListener(new CocomentSubmitBtnClickListener(positon));
                //imm.showSoftInput(binding.commentEt, 0);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private DocumentReference readData(){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        return mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(secondCP)
                .document(data.getName() + data.getTimestamp());
    }

    private class CocomentSubmitBtnClickListener implements View.OnClickListener {
        private final int mPosition;

        public CocomentSubmitBtnClickListener(int positon) {
            this.mPosition = positon;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(RatingsSubPostActivity.this);
            dlg.setTitle("대댓글 작성")
                    .setMessage("대댓글을 작성하시겠습니까?")
                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(RatingsSubPostActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            createCocomment(mPosition);
                            //Toast.makeText(CommunitySubPostActivity.this, "대대대댓글 작성", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        }
    }

    @Override
    public void onClick(View v) {
        imm.hideSoftInputFromWindow(binding.commentEt.getWindowToken(), 0); //키보드 내림

        AlertDialog.Builder dlg = new AlertDialog.Builder(RatingsSubPostActivity.this);
        dlg.setTitle("댓글 작성");
        dlg.setMessage("댓글을 작성하시겠습니까?");
        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(RatingsSubPostActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createComment();
                //Toast.makeText(CommunitySubPostActivity.this, "댓글 작성", Toast.LENGTH_SHORT).show();
            }
        });
        dlg.show();
    }

    private void createComment() {
        String comment = binding.commentEt.getText().toString();

        initComment();

        CommunitySubCommentModel comment_model = new CommunitySubCommentModel();
        comment_model.setContent(comment);
        comment_model.setTimestamp(Timestamp.now().toDate());
        comment_model.setName(id);

        data.getComments().add(comment_model);

        //파베 Update
        updateComment();
    }

    private void createCocomment(int position) {
        String comment = binding.commentEt.getText().toString();

        initComment();

        CommunitySubCommentCommentModel comment_model = new CommunitySubCommentCommentModel();
        comment_model.setContent(comment);
        comment_model.setTimestamp(Timestamp.now().toDate());
        comment_model.setName(id);

        data.getComments().get(position).getCommentModels().add(comment_model);

        updateComment();
        //파베 Create
    }

    private void initComment(){
        binding.commentEt.setText(null);
        binding.commentEt.setHint("댓글을 입력하세요.");
        binding.submitBtn.setOnClickListener(this);
    }

    private void updateComment(){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(secondCP)
                .document(data.getName() + data.getTimestamp())
                .update("comments", data.getComments());
        Toast.makeText(RatingsSubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
    }
}