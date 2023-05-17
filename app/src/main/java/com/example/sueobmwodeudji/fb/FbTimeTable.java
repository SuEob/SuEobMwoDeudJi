package com.example.sueobmwodeudji.fb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FbTimeTable extends AppCompatActivity {

    int tableNUM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_time_table);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ccc = db.collection("시간표");
        // C 문법 CollectionReference ccc = db.collection("users");
        // D 문법 DocumentReference ddd = db.collection("users").document("user2");

        EditText cText1 = findViewById(R.id.cText1);
        EditText cText2 = findViewById(R.id.cText2);
        EditText cText3 = findViewById(R.id.cText3);
        EditText cText4 = findViewById(R.id.cText4);
        EditText cText5 = findViewById(R.id.cText5);
        EditText rText1 = findViewById(R.id.rText1);
        EditText uText1 = findViewById(R.id.uText1);
        EditText uText2 = findViewById(R.id.uText2);
        EditText uText3 = findViewById(R.id.uText3);
        EditText uText4 = findViewById(R.id.uText4);
        EditText uText5 = findViewById(R.id.uText5);
        EditText dText1 = findViewById(R.id.dText1);
        Button aButton = findViewById(R.id.aButton);
        Button cButton = findViewById(R.id.cButton);
        Button rButton = findViewById(R.id.rButton);
        Button uButton = findViewById(R.id.uButton);
        Button uuButton = findViewById(R.id.uuButton);
        Button dButton = findViewById(R.id.dButton);
        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);
        TextView tv4 = findViewById(R.id.tv4);
        TextView tv5 = findViewById(R.id.tv5);
        RadioButton monday = findViewById(R.id.monday);
        RadioButton tueday = findViewById(R.id.tueday);
        RadioButton wedday = findViewById(R.id.wedday);
        RadioButton thuday = findViewById(R.id.thuday);
        RadioButton friday = findViewById(R.id.friday);
        RadioButton time1 = findViewById(R.id.time1);
        RadioButton time2 = findViewById(R.id.time2);
        RadioButton time3 = findViewById(R.id.time3);
        RadioButton time4 = findViewById(R.id.time4);
        RadioButton time5 = findViewById(R.id.time5);
        RadioButton time6 = findViewById(R.id.time6);
        RadioButton time7 = findViewById(R.id.time7);

        if (tableNUM > 0)
            Log.d("값있음", "bbb: " + String.valueOf(tableNUM));
        else {
            Log.d("값없음", "bbb: " + String.valueOf(tableNUM) + ", bbb에 1 대입");
            tableNUM = 1;
            Log.d("수정된 b값", String.valueOf(tableNUM));
        }


        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FbTimeTable.this, FbMainActivity.class);
                startActivity(intent);
            }
        });

        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> list = new ArrayList<>();
                for (int k=0; k<7; k++) {
                    list.add("");
                }

                TimeTableDTO dto = new TimeTableDTO();
                dto.setTimeTableName(cText1.getText().toString());
                dto.setYear(Integer.parseInt(cText2.getText().toString()));
                dto.setSemester(Integer.parseInt(cText3.getText().toString()));
                dto.setNowCredit(Integer.parseInt(cText4.getText().toString()));
                dto.setTotalCredit(Integer.parseInt(cText5.getText().toString()));
                dto.setMon(list);
                dto.setTue(list);
                dto.setWed(list);
                dto.setThu(list);
                dto.setFri(list);

                ccc.document("T" + tableNUM).set(dto);
                tableNUM++;
            }
        }); // cButton

        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccc.document(rText1.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        TimeTableDTO dto = documentSnapshot.toObject(TimeTableDTO.class);
                        tv1.setText(tv1.getText().toString() + dto.getTimeTableName());
                        tv2.setText(tv2.getText().toString() + dto.getYear());
                        tv3.setText(tv3.getText().toString() + dto.getSemester());
                        tv4.setText(tv4.getText().toString() + dto.getNowCredit());
                        tv5.setText(tv5.getText().toString() + dto.getTotalCredit());
                    }
                });
            }
        }); // rButton

        uButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ddd = uText1.getText().toString();   // document 명
                String kkk = uText2.getText().toString();   // key 값
                String vvv = uText3.getText().toString();   // value 값
                DocumentReference dddd = ccc.document(ddd);
                if (kkk.equals("timeTableName"))
                    dddd.update(kkk, vvv);  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
                else
                    dddd.update(kkk, Integer.parseInt(vvv));  // 필드의 키값이 kkk인 것의 밸류를 vvv로 변경
            }
        }); // uButton

        uuButton.setOnClickListener(new View.OnClickListener() {
            ArrayList<String> listt = new ArrayList<>();
            int time;
            @Override
            public void onClick(View view) {
                CollectionReference ccc = db.collection("시간표");
                String ddd = uText4.getText().toString();   // document 명
                String vvv = uText5.getText().toString();   // value 값
                DocumentReference dddd = ccc.document(ddd);
                dddd.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        TimeTableDTO dto = documentSnapshot.toObject(TimeTableDTO.class);
                        String kkk = "";
                        if(monday.isChecked()) {
                            listt = dto.getMon();
                            kkk = "mon";
                        }
                        else if(tueday.isChecked()) {
                            listt = dto.getTue();
                            kkk = "tue";
                        }
                        else if(wedday.isChecked()) {
                            listt = dto.getWed();
                            kkk = "wed";
                        }
                        else if(thuday.isChecked()) {
                            listt = dto.getThu();
                            kkk = "thu";
                        }
                        else if(friday.isChecked()) {
                            listt = dto.getFri();
                            kkk = "fri";
                        }

                        if(time1.isChecked())
                            time = 1;
                        else if(time2.isChecked())
                            time = 2;
                        else if(time3.isChecked())
                            time = 3;
                        else if(time4.isChecked())
                            time = 4;
                        else if(time5.isChecked())
                            time = 5;
                        else if(time6.isChecked())
                            time = 6;
                        else if(time7.isChecked())
                            time = 7;

                        listt.set(time-1, vvv);
                        dddd.update(kkk, listt);
                    }
                });
            }
        }); // uuButton

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
        });

    }
}
