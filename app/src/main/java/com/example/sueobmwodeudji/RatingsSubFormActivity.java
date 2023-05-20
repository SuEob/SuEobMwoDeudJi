package com.example.sueobmwodeudji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.firestore.FirebaseFirestore;

public class RatingsSubFormActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityRatingsSubFormBinding binding;

    private String class_name, teacher_name;

    private String firstCP, firstDP, secondCP;
    private String subject, id;

    private InputMethodManager imm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingsSubFormBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        class_name = intent.getStringExtra("class_name");
        teacher_name = intent.getStringExtra("teacher_name");
        secondCP = class_name + teacher_name;

        id = "test";

        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        /********** DB Path Setting **********/
        firstCP = "testRating";
        firstDP = "first";
        //secondCP = "수업코드";

        showItem();

        setContentView(binding.getRoot());
    }

    private void showItem() {
        binding.classTv.setText(class_name);
        binding.teacherTv.setText(teacher_name);
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
        data.setName(id);
        data.setContent(binding.contentEt.getText().toString());
        data.setTimestamp(Timestamp.now().toDate());
        RadioButton difi_rd = findViewById(binding.difficultyRg.getCheckedRadioButtonId());
        RadioButton type_rd = findViewById(binding.typeRg.getCheckedRadioButtonId());
        data.setDifficulty(difi_rd.getText().toString());
        data.setType(type_rd.getText().toString());


        //파베 Create
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection(firstCP)
                .document(firstDP)
                .collection(secondCP)
                .document(id + data.getTimestamp())
                .set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(RatingsSubFormActivity.this, "작성되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}