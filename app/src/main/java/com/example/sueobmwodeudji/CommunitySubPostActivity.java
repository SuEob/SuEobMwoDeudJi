package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class CommunitySubPostActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityCommunitySubPostBinding binding;
    private Intent intent;
    private CommunitySubListModel data;

    private InputMethodManager imm;
    private String subject, name;
    private String mEmail;

    private String mCollection, mSchool;

    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubPostBinding.inflate(getLayoutInflater());
        mFirestore = FirebaseFirestore.getInstance();
        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        intent = getIntent();
        data = (CommunitySubListModel) intent.getSerializableExtra("data");
        subject = intent.getStringExtra("subject");

        //Log.d("fsadfasdfsd", data.getComments().toString());


        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        // DB PATH Setting
        dbPathSetting();

        binding.submitBtn.setOnClickListener(this);
        binding.ratingLayout.setOnClickListener(new LikeBtnOnClickListener());

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

    private void dbPathSetting() {
        mCollection = "게시판";
        readSchool();
    }

    private void readSchool() {
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
        getSupportActionBar().setTitle(data.getCategory());

        changeText();
        setCommentRecyclerView();

        if (data.getEmail().equals(mEmail)) myPost();
    }

    private void changeText() {
        int like_count = likeCounting();
        int comment_count = commentCounting();

        binding.dateTv.setText(data.getTimestamp().toString());
        binding.titleTv.setText(data.getTitle());
        binding.contentTv.setText(data.getContent());
        binding.likeTv.setText(like_count + "");
        binding.commentTv.setText(comment_count + "");

        mFirestore.collection("사용자")
                .document(data.getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        binding.idTv.setText(documentSnapshot.getString("name"));
                    }
                });
    }

    private int likeCounting() {
        if (data.getLike() == null) return 0;

        int total = 0;
        Map<String, Boolean> map = data.getLike();

        for (String key : map.keySet()) {
            total += (map.get(key)) ? 1 : 0;
        }

        return total;
    }

    private int commentCounting() {
        int total = data.getComments().size();
        for (CommunitySubCommentModel data : data.getComments()) {
            total += data.getCommentModels().size();
        }
        return total;
    }

    private void setCommentRecyclerView() {
        CommunitySubCommentAdapter adapter = new CommunitySubCommentAdapter(this, readData());
        adapter.setOclp(new CommunitySubCommentAdapter.OnCocommentPositiveListener() {
            @Override
            public void onPositive(int positon) {
                binding.commentEt.setHint("대댓글을 입력하세요.");
                binding.commentEt.setText(null);
                binding.submitBtn.setOnClickListener(new CocomentSubmitBtnClickListener(positon));
                imm.showSoftInput(binding.commentEt, 0);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private void myPost() {
        binding.etcIv.setVisibility(View.VISIBLE);
        binding.etcIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(CommunitySubPostActivity.this);
                dlg.setTitle("글 삭제")
                        .setMessage("게시글을 삭제하시겠습니까?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(CommunitySubPostActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletePost();
                                //Toast.makeText(CommunitySubPostActivity.this, "게시글 수정ㄱㄱ", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }

    private void deletePost() {
        mFirestore.collection(mCollection)
                .document(mSchool)
                .collection(subject)
                .document(data.getEmail() + data.getTimestamp())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                    }
                });
    }

    private DocumentReference readData() {
        return mFirestore.collection(mCollection)
                .document(mSchool)
                .collection(subject)
                .document(data.getEmail() + data.getTimestamp());
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

    private class LikeBtnOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            boolean like;
            int like_count = Integer.parseInt(binding.likeTv.getText().toString());
            if (data.getLike().get(mEmail) != null) {
                like = !data.getLike().get(mEmail);
                if (data.getLike().get(mEmail)) {
                    binding.likeTv.setText(--like_count + "");
                    //Toast.makeText(CommunitySubPostActivity.this, "트루처리", Toast.LENGTH_SHORT).show();
                } else {
                    binding.likeTv.setText(++like_count + "");
                    //Toast.makeText(CommunitySubPostActivity.this, "펄스처리", Toast.LENGTH_SHORT).show();
                }
            } else {
                like = true;
                binding.likeTv.setText(++like_count + "");
                //Toast.makeText(CommunitySubPostActivity.this, "널처리", Toast.LENGTH_SHORT).show();
            }
            data.getLike().put(mEmail, like);
            updateLike();
        }
    }

    private void updateLike() {
        mFirestore.collection(mCollection)
                .document(mSchool)
                .collection(subject)
                .document(data.getEmail() + data.getTimestamp())
                .update("like", data.getLike());
    }

    private void createComment() {
        String comment = binding.commentEt.getText().toString();

        initComment();

        CommunitySubCommentModel comment_model = new CommunitySubCommentModel();
        comment_model.setContent(comment);
        comment_model.setTimestamp(Timestamp.now().toDate());
        comment_model.setName(name);

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
        comment_model.setName(name);

        data.getComments().get(position).getCommentModels().add(comment_model);

        //파베 Update
        updateComment();
    }

    private void initComment() {
        binding.commentEt.setText(null);
        binding.commentEt.setHint("댓글을 입력하세요.");
        binding.submitBtn.setOnClickListener(this);
    }

    private void updateComment() {
        mFirestore.collection(mCollection)
                .document(mSchool)
                .collection(subject)
                .document(data.getEmail() + data.getTimestamp())
                .update("comments", data.getComments());
        Toast.makeText(CommunitySubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
    }
}