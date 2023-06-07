package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.TimeTableSubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableSecondBinding;
import com.example.sueobmwodeudji.dto.TimeTableDTO;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;
import com.example.sueobmwodeudji.ui.TimeTableFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TimeTableSecondActivity extends AppCompatActivity {
    private ActivityTimeTableSecondBinding binding;
    private List<TimeTableSubFrameModel> list = new LinkedList<TimeTableSubFrameModel>();

    private String mEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // 툴바
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        TimeTableListView();
    }
    // 툴바 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.time_table_sub_tool_bar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.add:
                Intent intent = new Intent(this, TimeTableThridActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void TimeTableListView() {
        list.add(new TimeTableSubFrameModel("기본 시간표"));
        binding.timeTableSubList.setLayoutManager(new LinearLayoutManager(this));
        TimeTableSubListAdapter adapter = new TimeTableSubListAdapter(this, readTimeTable());
        adapter.setOlcl(new TimeTableSubListAdapter.OnListClickListener() {
            @Override
            public void onClick(TimeTableDTO data) {
                //finish();

                // 선택한 시간표를 true로, 나머지 시간표를 false로 변경
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference ccc = db.collection("시간표");
                Log.d ("ㅂㅈㄷㅂㅈㄷ", "들어옴");
                ccc.whereEqualTo("selected", true).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot value) {
                        ccc.document(data.getEmail() + " " + data.getYear() + " - " + data.getSemester()).update("selected", true);

                        if (value.getDocuments().size() != 0) {
                            TimeTableDTO dto = value.getDocuments().get(0).toObject(TimeTableDTO.class);
                            ccc.document(dto.getEmail() + " " + dto.getYear() + " - " + dto.getSemester()).update("selected", false);
                        }
                        finish();
                    }
                });
            }
        });
        binding.timeTableSubList.setAdapter(adapter);
    }

    private Query readTimeTable() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        return mFirestore.collection("시간표")
                .whereEqualTo("email", mEmail);
    }


}