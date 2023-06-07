package com.example.sueobmwodeudji.ui.view_pager_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.HomeRatingsPostAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeSubRatingsBinding;
import com.example.sueobmwodeudji.model.HomePopularRatingsData;

import java.util.ArrayList;
import java.util.List;

public class HomeRatingFragment extends Fragment {
    private FragmentHomeSubRatingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeSubRatingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선

        HomeViewPager();
    }

    public void HomeViewPager() {
        List<HomePopularRatingsData> list = new ArrayList<>();
        list.add(new HomePopularRatingsData("", "별로임"));
        list.add(new HomePopularRatingsData("헉!", "낫 배드"));

        HomeRatingsPostAdapter adapter = new HomeRatingsPostAdapter(list);
        binding.homePopularRatings.setAdapter(adapter);
        binding.homePopularRatings.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
