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
import com.example.sueobmwodeudji.databinding.FragmentTimeTableBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.ArrayList;
import java.util.List;

public class TimeTableFragment extends Fragment {
    private FragmentTimeTableBinding binding;
    private ActivityMainBinding mainBinding;
    private List<BasicFrameModel> list = new ArrayList<BasicFrameModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainBinding = ActivityMainBinding.inflate(inflater);
        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        TimeTableItemView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void TimeTableItemView() {
//        list = Arrays.asList(
//                new BasicFrameModel("시간표", mainBinding.containers),
//                new BasicFrameModel("수강학점", mainBinding.containers)
//        );
        binding.timeTableFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.timeTableFragment.setAdapter(new BasicFrameAdapter(getContext(), list));
    }
}