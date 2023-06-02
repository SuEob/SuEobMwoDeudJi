package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class RatingsSubPostActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityRatingsSubPostBinding binding;

    private Intent intent;
    private RatingsSubListModel data;
    private InputMethodManager imm;
    private String class_name, teacher_name;

    private String firstCP, firstDP, secondCP;
    private String subject, id;
    private String mEmail, mName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubPostBinding.inflate(getLayoutInflater());

        intent = getIntent();
        data = (RatingsSubListModel) intent.getSerializableExtra("data");
        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        mName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");



        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /********** DB Path Setting **********/
        firstCP = "testRating";
        firstDP = "first";
        //secondCP = "모바일캡스톤조범석";

        binding.submitBtn.setOnClickListener(this);
        binding.ratingLayout.setOnClickListener(new LikeBtnOnClickListener());

        setContentView(binding.getRoot());

        showItem();
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

    private void showItem() {
        getSupportActionBar().setTitle(class_name);

        changeText();
        setCommentRecyclerView();
        //getSupportActionBar().setSubtitle(teacher_name);
        if (data.getEmail().equals(mEmail)) myPost();
    }
    private void changeText() {
        int like_count = likeCounting();
        int comment_count = commentCounting();

        class_name = data.getClassName();
        //teacher_name = intent.getStringExtra("teacher_name");

        binding.dateTv.setText(data.getTimestamp().toString());
        binding.titleTv.setText(data.getTitle());
        binding.contentTv.setText(data.getContent());
        binding.difficultyTv.setText(data.getDifficulty());
        binding.typeTv.setText(data.getType());
        binding.likeTv.setText(like_count + "");
        binding.commentTv.setText(comment_count + "");
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
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
                //imm.showSoftInput(binding.commentEt, 0);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private void myPost() {
        binding.etcIv.setVisibility(View.VISIBLE);
        binding.etcIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(RatingsSubPostActivity.this);
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
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(class_name)
                .document(data.getEmail() + data.getTimestamp())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                    }
                });
    }

    private DocumentReference readData(){
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        return mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(class_name)
                .document(data.getEmail() + data.getTimestamp());
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
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(class_name)
                .document(mEmail + data.getTimestamp())
                .update("like", data.getLike());
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

    private void createComment() {
        String comment = binding.commentEt.getText().toString();

        initComment();

        CommunitySubCommentModel comment_model = new CommunitySubCommentModel();
        comment_model.setContent(comment);
        comment_model.setTimestamp(Timestamp.now().toDate());
        comment_model.setName(mName);

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
        comment_model.setName(mName);

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
                .collection(class_name)
                .document(data.getEmail() + data.getTimestamp())
                .update("comments", data.getComments());
        Toast.makeText(RatingsSubPostActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}