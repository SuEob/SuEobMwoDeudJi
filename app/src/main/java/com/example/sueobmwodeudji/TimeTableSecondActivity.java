package com.example.sueobmwodeudji;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.sueobmwodeudji.adapter.SmallFrameAdapter;

import com.example.sueobmwodeudji.databinding.ActivityTimeTableSecondBinding;
import com.example.sueobmwodeudji.model.SmallFrameModel;

import java.util.LinkedList;
import java.util.List;

public class TimeTableSecondActivity extends AppCompatActivity {
    private ActivityTimeTableSecondBinding binding;
    private List<SmallFrameModel> list = new LinkedList<SmallFrameModel>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeTableSecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TimeTableItemView();
    }

    private void TimeTableItemView() {
        list.add(new SmallFrameModel("시간표 이름1", R.layout.item_ratings_my_class_list));
        list.add(new SmallFrameModel("시간표 이름2", R.layout.item_ratings_my_class_list));
        list.add(new SmallFrameModel("시간표 이름3", R.layout.item_ratings_my_class_list));
        list.add(new SmallFrameModel("시간표 이름4", R.layout.item_ratings_my_class_list));
        list.add(new SmallFrameModel("시간표 이름5", R.layout.item_ratings_my_class_list));
        list.add(new SmallFrameModel("시간표 이름6", R.layout.item_ratings_my_class_list));
        list.add(new SmallFrameModel("시간표 이름7", R.layout.item_ratings_my_class_list));
        SmallFrameAdapter adapter = new SmallFrameAdapter(this, list);
        binding.timeTableFragment.setAdapter(adapter);
    }
}