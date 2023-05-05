package com.example.sueobmwodeudji.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.ActivityMainBinding;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;
import com.example.sueobmwodeudji.ui.sub_ui.HomeSub2Fragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private ActivityMainBinding mainBinding;
    private List<BasicFrameModel> list = new ArrayList<BasicFrameModel>();
    private int mNumber = 0;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = ActivityMainBinding.inflate(inflater);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        HomeItemView();
        return binding.getRoot();
    }


    // 프래그먼트 생명주기 > 뷰 생명주기 -> 그래서 없애야함
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void HomeItemView() {
        HomeSub2Fragment homeSub2Fragment = new HomeSub2Fragment();
        list.add(new BasicFrameModel("오늘 시간표", R.layout.item_fragment_home_sub_1));
        list.add(new BasicFrameModel("인기 게시글", R.layout.item_fragment_home_sub_2));
        binding.homeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeFragment.setAdapter(new BasicFrameAdapter(getContext(), list));
    }

}