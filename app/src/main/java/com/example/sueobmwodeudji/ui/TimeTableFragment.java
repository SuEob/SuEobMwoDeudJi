package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.TimeTableSubActivity;
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
        setHasOptionsMenu(true);

        binding.timeTable.friday1.setText("class_name");
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.time_table_tool_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getContext(), TimeTableSubActivity.class);

        if (item.getItemId() == R.id.add) {
            intent.putExtra("Code", 0);
            startActivity(intent);
        } else if (item.getItemId() == R.id.list) {
            intent.putExtra("Code", 1);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onChangeTimeTable(String class_name) {
        Log.d("class_title", class_name);
        binding.timeTable.friday1.setText(class_name);// 여기 값이 없음
    }
}