package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityRatingsSubPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.LinkedList;

public class RatingsSubPostActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityRatingsSubPostBinding binding;

    private Intent intent;
    private RatingsSubListModel data;
    private InputMethodManager imm;
    private String firstCP, firstDP, secondCP;
    private String subject, id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubPostBinding.inflate(getLayoutInflater());

        intent = getIntent();
        data = (RatingsSubListModel) intent.getSerializableExtra("data");
        //subject = intent.getStringExtra("subject");

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /********** DB Path Setting **********/
        firstCP = "testRating";
        firstDP = "first";
        secondCP = "모바일캡스톤조범석";

        binding.submitBtn.setOnClickListener(this);

        setContentView(binding.getRoot());

        showItem();
    }

    private void showItem() {
        String class_name = intent.getStringExtra("class_name");
        String teacher_name = intent.getStringExtra("teacher_name");

        getSupportActionBar().setTitle(class_name);
        getSupportActionBar().setSubtitle(teacher_name);

        CommunitySubCommentAdapter adapter = new CommunitySubCommentAdapter(this, data);
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
        CommunitySubCommentModel comment_model = new CommunitySubCommentModel();
        comment_model.setContent(comment);
        comment_model.setTimestamp(Timestamp.now().toDate());
        comment_model.setName(id);

        data.getComments().add(comment_model);

        //파베 Create
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(secondCP)
                .document(data.getName() + data.getTimestamp())
                .update("comments", data.getComments())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RatingsSubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                        mFirestore.collection(firstCP)
                                .document(firstDP)
                                .collection(secondCP)
                                .document(data.getName() + data.getTimestamp())
                                .get().onSuccessTask(new SuccessContinuation<DocumentSnapshot, Object>() {
                                    @NonNull
                                    @Override
                                    public Task<Object> then(DocumentSnapshot documentSnapshot) throws Exception {
                                        Intent _intent = new Intent(RatingsSubPostActivity.this, RatingsSubPostActivity.class);
                                        RatingsSubListModel _data = documentSnapshot.toObject(RatingsSubListModel.class);
                                        _intent.putExtra("data", _data);
                                        finish();
                                        startActivity(_intent);
                                        return null;
                                    }
                                });
                    }
                });
    }

    private void createCocomment(int position) {
        String comment = binding.commentEt.getText().toString();
        CommunitySubCommentCommentModel comment_model = new CommunitySubCommentCommentModel();
        comment_model.setContent(comment);
        comment_model.setTimestamp(Timestamp.now().toDate());
        comment_model.setName(id);

        data.getComments().get(position).getCommentModels().add(comment_model);

        //파베 Create
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(subject)
                .document(data.getName() + data.getTimestamp())
                .update("comments", data.getComments())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Toast.makeText(CommunitySubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                        mFirestore.collection("testPost")
                                .document("first")
                                .collection(subject)
                                .document(data.getName() + data.getTimestamp())
                                .get().onSuccessTask(new SuccessContinuation<DocumentSnapshot, Object>() {
                                    @NonNull
                                    @Override
                                    public Task<Object> then(DocumentSnapshot documentSnapshot) throws Exception {
                                        Intent _intent = new Intent(RatingsSubPostActivity.this, RatingsSubPostActivity.class);
                                        RatingsSubListModel _data = documentSnapshot.toObject(RatingsSubListModel.class);
                                        _intent.putExtra("data", _data);
                                        finish();
                                        startActivity(_intent);
                                        return null;
                                    }
                                });
                    }
                });
    }
}