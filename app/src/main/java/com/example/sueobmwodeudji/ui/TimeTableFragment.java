package com.example.sueobmwodeudji.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.FragmentTimeTableBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.LinkedList;
import java.util.List;

public class TimeTableFragment extends Fragment {
    private FragmentTimeTableBinding binding;
    private List<BasicFrameModel> list = new LinkedList<BasicFrameModel>();

    public static TimeTableFragment getInstance() {
        return new TimeTableFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}