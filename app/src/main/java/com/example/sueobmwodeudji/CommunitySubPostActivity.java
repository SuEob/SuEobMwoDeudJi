package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
        binding = ActivityCommunitySubPostBinding.inflate(getLayoutInflater());

        intent = getIntent();
        data = (CommunitySubListModel) intent.getSerializableExtra("data");
        subject = intent.getStringExtra("subject");

        Log.d("fsadfasdfsd", data.getComments().toString());


        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /********** DB Path Setting **********/
        firstCP = "testPost";
        firstDP = "first";
        secondCP = "1학년 대화방";

        binding.submitBtn.setOnClickListener(this);

        showItem();

        setContentView(binding.getRoot());
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (binding.commentEt.getHint().equals("댓글을 입력하세요.")) finish();
            initComment();
        }
        return false;
    }

    private void showItem() {
        getSupportActionBar().setTitle(data.getTitle());

        binding.idTv.setText(data.getName());
        binding.dateTv.setText(data.getTimestamp().toString());
        binding.titleTv.setText(data.getTitle());
        binding.contentTv.setText(data.getContent());

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
        Log.d("asdffdsa", firstCP + firstCP + subject + data.getName() + data.getTimestamp());
        return mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(subject)
                .document(data.getName() + data.getTimestamp());
    }


    private class CocomentSubmitBtnClickListener implements View.OnClickListener {
        private final int mPosition;

        public CocomentSubmitBtnClickListener(int positon) {
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

        //파베 Update
        updateComment();
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
                .collection(subject)
                .document(data.getName() + data.getTimestamp())
                .update("comments", data.getComments());
        Toast.makeText(CommunitySubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
    }
}
