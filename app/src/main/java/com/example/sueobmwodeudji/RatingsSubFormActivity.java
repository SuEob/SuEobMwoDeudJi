package com.example.sueobmwodeudji;

import android.app.ActivityManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.databinding.ActivityRatingsSubFormBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RatingsSubFormActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityRatingsSubFormBinding binding;

    private String class_name;

    private String subject;
    private String mCollection, mSchool, mEmail;
    private boolean isHoney = true;

    FirebaseFirestore mFirestore;

    private InputMethodManager imm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubFormBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        class_name = intent.getStringExtra("class_name");
        //teacher_name = intent.getStringExtra("teacher_name");
        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        mFirestore = FirebaseFirestore.getInstance();

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        // DB Path Setting
        dbPathSetting();

        binding.honeyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHoney) {
                    binding.honeyIv.setImageResource(R.drawable.honey_not);
                    isHoney = false;
                    Log.d("꿀첨가", "ㄴㄴ");
                } else {
                    binding.honeyIv.setImageResource(R.drawable.honey_yes);
                    isHoney = true;
                    Log.d("꿀첨가", "ㅇㅇ");
                }
            }
        });

        setContentView(binding.getRoot());
    }

    private void dbPathSetting() {
        mCollection = "평가";
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
        binding.classTv.setText(class_name);
        //binding.teacherTv.setText(teacher_name);
        binding.submitTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //imm.hideSoftInputFromWindow(binding.submitTv.getWindowToken(), 0); //키보드 내림

        AlertDialog.Builder dlg = new AlertDialog.Builder(RatingsSubFormActivity.this);
        dlg.setTitle("평가 작성");
        dlg.setMessage("정말로 평가를 작성하시겠습니까?");
        dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(RatingsSubFormActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createRating();
                //Toast.makeText(RatingsSubFormActivity.this, "확인 누름", Toast.LENGTH_SHORT).show();
            }
        });
        dlg.show();
    }

    private void createRating(){
        //데이터 생성
        RatingsSubListModel data = new RatingsSubListModel();
        data.setTitle(binding.titleEt.getText().toString());
        data.setEmail(mEmail);
        data.setContent(binding.contentEt.getText().toString());
        data.setTimestamp(Timestamp.now().toDate());
        data.setLike(new HashMap<>());
        data.setHoney(isHoney);
        RadioButton difi_rd = findViewById(binding.difficultyRg.getCheckedRadioButtonId());
        RadioButton type_rd = findViewById(binding.typeRg.getCheckedRadioButtonId());
        data.setDifficulty(difi_rd.getText().toString());
        data.setType(type_rd.getText().toString());
        data.setClassName(class_name);


        //파베 Create
        mFirestore.collection(mCollection)
                .document(mSchool)
                .collection(class_name)
                .document(mEmail + data.getTimestamp())
                .set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //Toast.makeText(RatingsSubFormActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                        addSueob();
                    }
                });
    }

    private void addSueob() {
        //파베 수업 목록 추가
        mFirestore.collection("수업")
                .document(mSchool)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ArrayList<String> data = (ArrayList<String>) documentSnapshot.get("categorys");
                        data = (data != null)? data : new ArrayList<>();
                        data.add(class_name);
                        HashSet<String> hs = new HashSet<String>(data);
                        data.clear();
                        data.addAll(hs);

                        HashMap<String, ArrayList<String>> map = new HashMap<>();
                        map.put("categorys", data);

                        mFirestore.collection("수업")
                                .document(mSchool)
                                .set(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(RatingsSubFormActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                    }
                });
    }
}