package com.example.sueobmwodeudji.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.adapter.ViewPagerAdapter;
import com.example.sueobmwodeudji.databinding.FragmentRatingsBinding;
import com.example.sueobmwodeudji.dto.RatingMyClassData;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RatingsFragment extends Fragment {
    private FragmentRatingsBinding binding;
    private List<BasicFrameModel> list = new LinkedList<BasicFrameModel>();

    public static RatingsFragment getInstance() {
        return new RatingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRatingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);

        RatingsItemView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void RatingsItemView() {
        ArrayList<ArrayList<RatingMyClassData>> listData = new ArrayList<>();
        ArrayList<RatingMyClassData> list1 = new ArrayList<>();
        list1.add(new RatingMyClassData("인공지능", "조범석"));
        list1.add(new RatingMyClassData("자료구조", "조범석"));
        list1.add(new RatingMyClassData("모바일캡스톤", "조범석"));

        ArrayList<RatingMyClassData> list2 = new ArrayList<>();
        list2.add(new RatingMyClassData("모바일캡스톤", "조범석"));
        list2.add(new RatingMyClassData("모바일캡스톤", "조범석"));
        list2.add(new RatingMyClassData("모바일캡스톤", "조범석"));

        ArrayList<RatingMyClassData> list3 = new ArrayList<>();
        list3.add(new RatingMyClassData("모바일캡스톤", "조범석"));

        listData.add(list1);
        listData.add(list2);
        listData.add(list3);

        binding.viewPager2.setAdapter(new ViewPagerAdapter(listData));
    }
}
