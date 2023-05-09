package com.example.sueobmwodeudji.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<BasicFrameModel> list = new LinkedList<BasicFrameModel>();

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        HomeItemView();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void HomeItemView() {
        list.add(new BasicFrameModel("오늘 시간표", R.layout.item_home_sub_1));
        list.add(new BasicFrameModel("인기 게시글", R.layout.item_home_sub_2));
        binding.homeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeFragment.setAdapter(new BasicFrameAdapter(getContext(), list));
    }

}