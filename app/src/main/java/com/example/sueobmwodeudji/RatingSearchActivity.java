package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.adapter.SmallFrameAdapter;
import com.example.sueobmwodeudji.databinding.ActivityRatingSearchBinding;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableSecondBinding;
import com.example.sueobmwodeudji.model.SmallFrameModel;

import java.util.LinkedList;
import java.util.List;

public class RatingSearchActivity extends AppCompatActivity {
    private ActivityRatingSearchBinding binding;
    private List<SmallFrameModel> list = new LinkedList<SmallFrameModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRatingSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TimeTableItemView();

        binding.searchRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TimeTableThridActivity.class);
                startActivity(intent);
            }
        });
    }

    private void TimeTableItemView() {
        list.add(new SmallFrameModel("1", R.layout.item_rating_search));
        list.add(new SmallFrameModel("2", R.layout.item_rating_search));
        list.add(new SmallFrameModel("이름3", R.layout.item_rating_search));
        list.add(new SmallFrameModel("이름4", R.layout.item_rating_search));
        SmallFrameAdapter adapter = new SmallFrameAdapter(this, list);
        binding.ratingResult.setAdapter(adapter);
    }
}