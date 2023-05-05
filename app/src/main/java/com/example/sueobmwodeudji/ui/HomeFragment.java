package com.example.sueobmwodeudji.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.ActivityMainBinding;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private ActivityMainBinding mainBinding;
    private List<BasicFrameModel> list = new ArrayList<BasicFrameModel>();

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = ActivityMainBinding.inflate(inflater);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        HomeItemView();
    }

    // 프래그먼트 생명주기 > 뷰 생명주기 -> 그래서 없애야함
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void HomeItemView() {
        list = Arrays.asList(
                new BasicFrameModel("오늘 시간표", mainBinding.containers),
                new BasicFrameModel("인기 게시글", mainBinding.containers)
        );
        binding.homeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeFragment.setAdapter(new BasicFrameAdapter(getContext(), list));
    }
}