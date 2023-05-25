package com.example.sueobmwodeudji.ui.sub_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.TimeTableSubListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableSubListBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;

import java.util.LinkedList;
import java.util.List;

public class TimeTableListFragment extends Fragment {
    private ActivityTimeTableSubListBinding binding;
    private List<TimeTableSubFrameModel> list = new LinkedList<TimeTableSubFrameModel>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityTimeTableSubListBinding.inflate(inflater, container, false);
        TimeTableListView();
        return binding.getRoot();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        list.add(new TimeTableSubFrameModel("1"));
//        binding.timeTableSubList.setLayoutManager(new LinearLayoutManager(getContext()));
//        binding.timeTableSubList.setAdapter(new TimeTableSubListAdapter(getContext(), list));
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void TimeTableListView() {
//        List<HomeTimaTableData>
        list.add(new TimeTableSubFrameModel("수업명1"));
        list.add(new TimeTableSubFrameModel("수업명2"));
        list.add(new TimeTableSubFrameModel("수업명3"));
        list.add(new TimeTableSubFrameModel("수업명4"));
        binding.timeTableSubList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.timeTableSubList.setAdapter(new TimeTableSubListAdapter(getContext(), list));
    }
}
