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
import com.example.sueobmwodeudji.databinding.FragmentRatingsBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RatingsFragment extends Fragment {
    private FragmentRatingsBinding binding;
    private ActivityMainBinding mainBinding;
    private List<BasicFrameModel> list = new ArrayList<BasicFrameModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = ActivityMainBinding.inflate(inflater);
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

    private void RatingsItemView() {
        list = Arrays.asList(
                new BasicFrameModel("나의 수업", mainBinding.containers),
                new BasicFrameModel("최근 수강평", mainBinding.containers)
        );
        binding.ratingsFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.ratingsFragment.setAdapter(new BasicFrameAdapter(getContext(), list));
    }

}
