package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.LinkedList;

public class CommunitySubPostActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityCommunitySubPostBinding binding;
    private Intent intent;
    private CommunitySubListModel data;

    private InputMethodManager imm;

    private String firstCP, firstDP, secondCP;
    private String subject, id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = getIntent();
        data = (CommunitySubListModel) intent.getSerializableExtra("data");

        binding = ActivityCommunitySubPostBinding.inflate(getLayoutInflater());
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /********** DB Path Setting **********/
        firstCP = "testPost";
        firstDP = "first";
        secondCP = "1학년 게시판";

        binding.submitBtn.setOnClickListener(this);

        showItem();

        setContentView(binding.getRoot());
    }

    private void showItem() {
        getSupportActionBar().setTitle(data.getTitle());

        CommunitySubCommentAdapter adapter = new CommunitySubCommentAdapter(this, data);
        adapter.setSibal(new CommunitySubCommentAdapter.OnCocommentPositiveListener() {
            @Override
            public void onPositive(int positon) {
                binding.commentEt.setHint("대댓글을 입력하세요.");
                binding.commentEt.setText(null);
                binding.submitBtn.setOnClickListener(new cocomentSubmitBtnClickListener(positon));
                //imm.showSoftInput(binding.commentEt, 0);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if ( binding.commentEt.getHint().equals("대댓글을 입력하세요.")){
                binding.commentEt.setHint("댓글을 입력하세요.");
                binding.commentEt.setText(null);
                binding.submitBtn.setOnClickListener(this);
            }else finish();
        }
        return false;
    }

    private class cocomentSubmitBtnClickListener implements View.OnClickListener{
        private final int mPosition;
        public cocomentSubmitBtnClickListener(int positon){
            this.mPosition = positon;
        }
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(CommunitySubPostActivity.this);
                dlg.setTitle("대댓글 작성")
                        .setMessage("대댓글을 작성하시겠습니까?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(CommunitySubPostActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
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

        AlertDialog.Builder dlg = new AlertDialog.Builder(CommunitySubPostActivity.this);
        dlg.setTitle("댓글 작성");
        dlg.setMessage("댓글을 작성하시겠습니까?");
        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CommunitySubPostActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
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
                .update("comments", data.getComments()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CommunitySubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                        mFirestore.collection("testPost")
                                .document("first")
                                .collection("1학년 게시판")
                                .document(data.getName() + data.getTimestamp())
                                .get().onSuccessTask(new SuccessContinuation<DocumentSnapshot, Object>() {
                                    @NonNull
                                    @Override
                                    public Task<Object> then(DocumentSnapshot documentSnapshot) throws Exception {
                                        Intent _intent = new Intent(CommunitySubPostActivity.this, CommunitySubPostActivity.class);
                                        CommunitySubListModel _data = documentSnapshot.toObject(CommunitySubListModel.class);
                                        _intent.putExtra("data", _data);
                                        finish();
                                        startActivity(_intent);
                                        return null;
                                    }
                                });


                    }
                });
    }
    private void createCocomment(int position){
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
                .collection(secondCP)
                .document(data.getName() + data.getTimestamp())
                .update("comments", data.getComments()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Toast.makeText(CommunitySubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
                        mFirestore.collection("testPost")
                                .document("first")
                                .collection("1학년 게시판")
                                .document(data.getName() + data.getTimestamp())
                                .get().onSuccessTask(new SuccessContinuation<DocumentSnapshot, Object>() {
                                    @NonNull
                                    @Override
                                    public Task<Object> then(DocumentSnapshot documentSnapshot) throws Exception {
                                        Intent _intent = new Intent(CommunitySubPostActivity.this, CommunitySubPostActivity.class);
                                        CommunitySubListModel _data = documentSnapshot.toObject(CommunitySubListModel.class);
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
