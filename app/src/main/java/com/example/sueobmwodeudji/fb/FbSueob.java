package com.example.sueobmwodeudji.fb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FbSueob extends AppCompatActivity {

    String cccccc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_sueob);

        EditText ccText1 = findViewById(R.id.ccText1);
        EditText cText0 = findViewById(R.id.cText0);
        EditText cText1 = findViewById(R.id.cText1);
        EditText cText2 = findViewById(R.id.cText2);
        EditText cText3 = findViewById(R.id.cText3);
        EditText cText4 = findViewById(R.id.cText4);
        EditText cText5 = findViewById(R.id.cText5);
        EditText rText1 = findViewById(R.id.rText1);
        EditText uText1 = findViewById(R.id.uText1);
        EditText uText2 = findViewById(R.id.uText2);
        EditText uText3 = findViewById(R.id.uText3);
        EditText dText1 = findViewById(R.id.dText1);
        Button aButton = findViewById(R.id.aButton);
        Button ccButton = findViewById(R.id.ccButton);
        Button cButton = findViewById(R.id.cButton);
        Button rButton = findViewById(R.id.rButton);
        Button uButton = findViewById(R.id.uButton);
        Button dButton = findViewById(R.id.dButton);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        RadioGroup classRG = findViewById(R.id.classRG);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ccc = db.collection("수업");
        // C 문법 CollectionReference ccc = db.collection("users");
        // D 문법 DocumentReference ddd = db.collection("users").document("user2");

        classRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.kor: cccccc = "국어"; break;
                    case R.id.math: cccccc = "수학"; break;
                    case R.id.eng: cccccc = "영어"; break;
                    case R.id.soc: cccccc = "사회탐구"; break;
                    case R.id.sci: cccccc = "과학탐구"; break;
                    case R.id.art: cccccc = "예체능"; break;
                    default: ;
                }
            }
        }); // 라디오버튼(cccccc)와 cText0은 계속 씀

        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FbSueob.this, FbMainActivity.class);
                startActivity(intent);
            }
        });

        ccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SueobDTO dto = new SueobDTO();
                ccc.document(ccText1.getText().toString()).set(dto);    // 학교 추가

                // 나머지 값들 지우기
                DocumentReference docRef = ccc.document(ccText1.getText().toString());
                Map<String, Object> updates = new HashMap<>();
                updates.put("day", FieldValue.delete());
                updates.put("sueobName", FieldValue.delete());
                updates.put("teacherName", FieldValue.delete());
                updates.put("time", FieldValue.delete());
                docRef.update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("성공", "학교 생성 및 빈 필드들 삭제 완료");
                    }
                });
            }
        });

        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ddd = cText0.getText().toString();
                String dddddd = cText1.getText().toString();

                // dto에 값들 담기
                SueobDTO dto = new SueobDTO();
                dto.setSueobName(cText2.getText().toString());
                dto.setTeacherName(cText3.getText().toString());
                dto.setDay(cText4.getText().toString());
                dto.setTime(Integer.parseInt(cText5.getText().toString()));
                ccc.document(ddd).collection(cccccc).document(dddddd).set(dto);    // 추가
            }
        }); // cButton

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ddd = cText0.getText().toString();

                // 가져올 document 연결
                ccc.document(ddd).collection(cccccc).document(rText1.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        SueobDTO dto = documentSnapshot.toObject(SueobDTO.class);
                        tv1.setText(tv1.getText().toString() + dto.getSueobName());
                        tv2.setText(tv2.getText().toString() + dto.getTeacherName());
                        tv3.setText(tv3.getText().toString() + dto.getDay());
                        tv4.setText(tv4.getText().toString() + dto.getTime());
                    }
                });
            }
        }); // rButton

        uButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ddd = cText0.getText().toString();
                String dddddd = uText1.getText().toString(); // document 명
                String kkk = uText2.getText().toString();    // key 값
                String vvv = uText3.getText().toString();    // value 값
                DocumentReference dddd = ccc.document(ddd).collection(cccccc).document(dddddd);
                if (kkk.equals("time"))
                    dddd.update(kkk, Integer.parseInt(vvv));  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
                else
                    dddd.update(kkk, vvv);  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
            }
        }); // uButton

        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ddd = cText0.getText().toString();
                String dddddd = dText1.getText().toString();   // document 명
                ccc.document(ddd).collection(cccccc).document(dddddd).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("삭제 성공", "document 값이" + dddddd + "인 것 삭제완료");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("삭제 실패", "document 값이" + dddddd + "인 것 삭제실패");
                    }
                });
            }
        }); // dButton

    }
}
