package com.example.sueobmwodeudji.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.BasicFrame;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<BasicFrame> list = new ArrayList<BasicFrame>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

    private void HomeItemView() {
        list = Arrays.asList(
                new BasicFrame("CEX")
        );
        binding.homeFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.homeFragment.setAdapter(new BasicFrameAdapter(getActivity(), list));
    }

}