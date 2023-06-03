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

import com.example.sueobmwodeudji.MainActivity;
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

public class FbPost extends AppCompatActivity {

    String cccccc = "";
    int postNUM, commentNUM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_post);

        EditText ccText1 = findViewById(R.id.ccText1);
        EditText cText0 = findViewById(R.id.cText0);
        EditText cText1 = findViewById(R.id.cText1);
        EditText cText2 = findViewById(R.id.cText2);
        EditText cccText1 = findViewById(R.id.cccText1);
        EditText cccText2 = findViewById(R.id.cccText2);
        EditText rText1 = findViewById(R.id.rText1);
        EditText uText1 = findViewById(R.id.uText1);
        EditText uText2 = findViewById(R.id.uText2);
        EditText uText3 = findViewById(R.id.uText3);
        EditText uText4 = findViewById(R.id.uText4);
        EditText uText5 = findViewById(R.id.uText5);
        EditText uText6 = findViewById(R.id.uText6);
        EditText dText1 = findViewById(R.id.dText1);
        EditText dText2 = findViewById(R.id.dText2);
        Button aButton = findViewById(R.id.aButton);
        Button cButton = findViewById(R.id.cButton);
        Button ccButton = findViewById(R.id.ccButton);
        Button cccButton = findViewById(R.id.cccButton);
        Button rButton = findViewById(R.id.rButton);
        Button uButton = findViewById(R.id.uButton);
        Button uuButton = findViewById(R.id.uuButton);
        Button dButton = findViewById(R.id.dButton);
        Button ddButton = findViewById(R.id.ddButton);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        RadioGroup postRG = findViewById(R.id.postRG);

        if (postNUM > 0)
            Log.d("값있음", "postNUM: " + String.valueOf(postNUM));
        else {
            Log.d("값없음", "postNUM: " + String.valueOf(postNUM) + ", postNUM에 1 대입");
            postNUM = 1;
            Log.d("수정된 postNUM값", String.valueOf(postNUM));
        }

        if (commentNUM > 0)
            Log.d("값있음", "commentNUM: " + String.valueOf(commentNUM));
        else {
            Log.d("값없음", "commentNUM: " + String.valueOf(commentNUM) + ", commentNUM에 1 대입");
            commentNUM = 1;
            Log.d("수정된 commentNUM값", String.valueOf(commentNUM));
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ccc = db.collection("게시글");
        // C 문법 CollectionReference ccc = db.collection("users");
        // D 문법 DocumentReference ddd = db.collection("users").document("user2");

        postRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.post_grade1: cccccc = "1학년 게시판"; break;
                    case R.id.post_grade2: cccccc = "2학년 게시판"; break;
                    case R.id.post_grade3: cccccc = "3학년 게시판"; break;
                    case R.id.post_game: cccccc = "게임 게시판"; break;
                    case R.id.post_study: cccccc = "공부 게시판"; break;
                    case R.id.post_shop: cccccc = "쇼핑 게시판"; break;
                    case R.id.post_exercise: cccccc = "운동 게시판"; break;
                    default: ;
                }
            }
        }); // 라디오버튼(cccccc)와 cText0은 계속 씀

        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FbPost.this, MainActivity.class);
                startActivity(intent);
            }
        }); // aButton

        ccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostDTO dto = new PostDTO();
                ccc.document(ccText1.getText().toString()).set(dto);    // 학교 추가

                DocumentReference docRef = ccc.document(ccText1.getText().toString());
                Map<String, Object> updates = new HashMap<>();
                // 나머지 값들 지우기
                updates.put("post_title", FieldValue.delete());
                updates.put("post_contents", FieldValue.delete());
                updates.put("post_commentNUM", FieldValue.delete());
                updates.put("post_recommendNUM", FieldValue.delete());
                updates.put("replys", FieldValue.delete());
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
            public void onClick(View v) {
                String ddd = cText0.getText().toString();
                String dddddd = "" + postNUM;
                Map<Integer, Map<String, String>> map = null;

                PostDTO dto = new PostDTO();
                dto.setPost_title(cText1.getText().toString());
                dto.setPost_contents(cText2.getText().toString());
                dto.setReplys(map);
                ccc.document(ddd).collection(cccccc).document(dddddd).set(dto);
                postNUM++;
                // 나머지 값들 지우기
            }
        }); // cButton

        cccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ddd = cText0.getText().toString();
                String dddddd = cccText1.getText().toString(); // document 명

                PostReplyDTO dto = new PostReplyDTO();
                dto.setReply_contents(cccText2.getText().toString());
                ccc.document(ddd).collection(cccccc).document(dddddd).set(dto);
                commentNUM++;
            }
        }); // cccButton

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ddd = cText0.getText().toString();
                ccc.document(ddd).collection(cccccc).document(rText1.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        PostDTO dto = documentSnapshot.toObject(PostDTO.class);
                        tv1.setText(tv1.getText().toString() + dto.getPost_title());
                        tv2.setText(tv2.getText().toString() + dto.getPost_contents());
                        tv3.setText(tv3.getText().toString() + dto.getPost_commentNUM());
                        tv4.setText(tv4.getText().toString() + dto.getPost_recommendNUM());
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
                if (kkk.equals("post_commentNUM") || kkk.equals("post_recommendNUM"))
                    dddd.update(kkk, Integer.parseInt(vvv));  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
                else
                    dddd.update(kkk, vvv);  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
            }
        }); // uButton

        uuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ddd = cText0.getText().toString();
                String dddddd = uText1.getText().toString();    // document 명(게시글)
                String ddddddddd = uText4.getText().toString(); // document 명(댓글)
                String kkk = uText5.getText().toString();    // key 값
                String vvv = uText6.getText().toString();    // value 값
                DocumentReference dddd = ccc.document(ddd).collection(cccccc).document(dddddd);
                if (kkk.equals("reply_recommendNUM"))
                    dddd.update(kkk, Integer.parseInt(vvv));  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
                else
                    dddd.update(kkk, vvv);  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
            }
        }); // uuButton

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
