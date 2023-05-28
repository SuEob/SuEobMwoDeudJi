package com.example.sueobmwodeudji.fb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;

public class JsonToFabe extends AppCompatActivity {
    String[][] sueobData = {{"수학", "한국사", "통합과학", "국어", "기술가정", "기술가정", ""},
            {"국어", "영어", "한국사", "통합과학", "수학", "진로와 직업", "진로활동"},
            {"통합사회", "영어", "과학탐구실험", "음악", "통합과학", "수학", "통합사회"},
            {"1","2","3","4","5","6","7"},
            {"1","1","1","11","11","11","11"}};
    TimeTableDTO dto = new TimeTableDTO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_query);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ccc = db.collection("cities");

        ArrayList<String> list;

        for (int k=0; k<5; k++) {
            list = new ArrayList<>();
            for (int j=0; j<7; j++) {
                list.add(sueobData[k][j]);
            }
            switch (k) {    // 월~금에 배열 데이터 넣기
                case 0: dto.setMon(list); break;
                case 1: dto.setTue(list); break;
                case 2: dto.setWed(list); break;
                case 3: dto.setThu(list); break;
                case 4: dto.setFri(list); break;
            }
        }
        ccc.document("T" + 3).set(dto); }}