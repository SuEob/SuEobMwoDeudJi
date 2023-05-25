package com.example.sueobmwodeudji.ui.view_pager_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.HomePopularPostAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeSubPopularPostBinding;
import com.example.sueobmwodeudji.model.HomePopularPostData;

import java.util.ArrayList;
import java.util.List;

public class HomePopularPostFragment extends Fragment {
    private FragmentHomeSubPopularPostBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeSubPopularPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선

        HomeViewPager();
    }


    public void HomeViewPager() {
        List<HomePopularPostData> list = new ArrayList<>();
        list.add(new HomePopularPostData("흠..", "05.23"));
        list.add(new HomePopularPostData("헉!", "05.13"));

        HomePopularPostAdapter adapter = new HomePopularPostAdapter(getContext(), list);
        binding.homePopularPost.setAdapter(adapter);
        binding.homePopularPost.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
