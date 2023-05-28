package com.example.sueobmwodeudji.ui.sub_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.TimeTableSubAddAdapter;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableSubAddBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;

import java.util.LinkedList;
import java.util.List;

public class TimeTableAddFragment extends Fragment {
    private ActivityTimeTableSubAddBinding binding;
    private List<TimeTableSubFrameModel> list = new LinkedList<TimeTableSubFrameModel>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityTimeTableSubAddBinding.inflate(inflater, container, false);
        TimeTableAddView();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void TimeTableAddView() {
        list.add(new TimeTableSubFrameModel("수업명", "월요일", "1교시"));
        list.add(new TimeTableSubFrameModel("수업명", "화요일", "1교시"));
        list.add(new TimeTableSubFrameModel("수업명", "수요일", "1교시"));

        binding.timeTableSubAdd.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.timeTableSubAdd.setAdapter(new TimeTableSubAddAdapter(getContext(), list));
    }
}
