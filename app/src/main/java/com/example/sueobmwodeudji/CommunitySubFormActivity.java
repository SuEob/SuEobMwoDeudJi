package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityCommunitySubFormBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CommunitySubFormActivity  extends AppCompatActivity implements View.OnClickListener {
    ActivityCommunitySubFormBinding binding;
    private String mCollection, subject, mName, mSchool;
    private String mEmail;

    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommunitySubFormBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        subject = intent.getStringExtra("subject");
        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        mName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        //id = "test";

        binding.subjectTv.setText(subject);

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        // DB PATH Setting
        dbPathSetting();


        //createPost();
        setContentView(binding.getRoot());
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
        binding.submitTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        imm.hideSoftInputFromWindow(binding.contentEt.getWindowToken(), 0); //키보드 내림

        AlertDialog.Builder dlg = new AlertDialog.Builder(CommunitySubFormActivity.this);
        dlg.setTitle("게시글 작성");
        dlg.setMessage("정말로 작성하시겠습니까?");
        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CommunitySubFormActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createPost();
                //Toast.makeText(CommunitySubFormActivity.this, "확인 누름", Toast.LENGTH_SHORT).show();
            }
        });
        dlg.show();
    }

    private void createPost(){
        //데이터 생성
        CommunitySubListModel data = new CommunitySubListModel();
        data.setTitle(binding.titleEt.getText().toString());
        data.setEmail(mEmail);
        data.setContent(binding.contentEt.getText().toString());
        data.setTimestamp(Timestamp.now().toDate());
        data.setLike(new HashMap<>());
        data.setCategory(subject);

        //파베 Create
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection(mCollection)
                .document(mSchool)
                .collection(subject)
                .document(mEmail + data.getTimestamp())
                .set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CommunitySubFormActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}