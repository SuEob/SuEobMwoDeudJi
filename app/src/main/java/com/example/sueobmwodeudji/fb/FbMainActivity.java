package com.example.sueobmwodeudji.fb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.sueobmwodeudji.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class FbMainActivity extends AppCompatActivity {
    int userNUM;

    UserDTO data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ccc = db.collection("users");

        ccc.document("user1").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                t(documentSnapshot);
            }
        });
/*
        // C 문법 CollectionReference ccc = db.collection("users");
        // D 문법 DocumentReference ddd = db.collection("users").document("user2");

        EditText cText1 = findViewById(R.id.cText1);
        EditText cText2 = findViewById(R.id.cText2);
        EditText cText3 = findViewById(R.id.cText3);
        EditText cText4 = findViewById(R.id.cText4);
        EditText rText1 = findViewById(R.id.rText1);
        EditText uText1 = findViewById(R.id.uText1);
        EditText uText2 = findViewById(R.id.uText2);
        EditText uText3 = findViewById(R.id.uText3);
        EditText dText1 = findViewById(R.id.dText1);
        Button aButton = findViewById(R.id.aButton);
        Button aaButton = findViewById(R.id.aaButton);
        Button aaaButton = findViewById(R.id.aaaButton);
        Button cButton = findViewById(R.id.cButton);
        Button rButton = findViewById(R.id.rButton);
        Button uButton = findViewById(R.id.uButton);
        Button dButton = findViewById(R.id.dButton);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);

        if (userNUM > 0)
            Log.d("값있음", "bbb: " + String.valueOf(userNUM));
        else {
            Log.d("값없음", "bbb: " + String.valueOf(userNUM) + ", bbb에 1 대입");
            userNUM = 1;
            Log.d("수정된 b값", String.valueOf(userNUM));
        }

        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FbMainActivity.this, FbTimeTable.class);
                startActivity(intent);
            }
        });

        aaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FbMainActivity.this, FbSueob.class);
                startActivity(intent);
            }
        });

        aaaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FbMainActivity.this, FbPost.class);
                startActivity(intent);
            }
        });

        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDTO dto = new UserDTO();
                dto.setId(cText1.getText().toString());
                dto.setPw(Integer.parseInt(cText2.getText().toString()));
                dto.setSchoolName(cText3.getText().toString());
                dto.setEMail(cText4.getText().toString());
                ccc.document("user" + userNUM).set(dto);    // 추가
                userNUM++;
            }
        });

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ccc.document(rText1.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserDTO dto = documentSnapshot.toObject(UserDTO.class);
                        tv1.setText(tv1.getText().toString() + dto.getId());
                        tv2.setText(tv2.getText().toString() + dto.getPw());
                        tv3.setText(tv3.getText().toString() + dto.getSchoolName());
                        tv4.setText(tv4.getText().toString() + dto.getEMail());
                    }
                });
            }
        });

        uButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ddd = uText1.getText().toString();   // document 명
                String kkk = uText2.getText().toString();   // key 값
                String vvv = uText3.getText().toString();   // value 값
                DocumentReference dddd = ccc.document(ddd);
                if(kkk.equals("pw"))
                    dddd.update(kkk, Integer.parseInt(vvv));  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
                else
                    dddd.update(kkk, vvv);  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
            }
        });

        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ddd = dText1.getText().toString();   // document 명
                ccc.document(ddd).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("삭제 성공", "document 값이" + ddd + "인 것 삭제완료");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.d("삭제 실패", "document 값이" + ddd + "인 것 삭제실패");
                    }
                });
            }
        });*/
    }

    protected void t(DocumentSnapshot documentSnapshot){
        data =  documentSnapshot.toObject(UserDTO.class);
        Log.d("asdfasd",data.getId());
    }
}