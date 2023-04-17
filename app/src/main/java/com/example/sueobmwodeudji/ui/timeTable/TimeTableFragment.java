package com.example.sueobmwodeudji.ui.timeTable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sueobmwodeudji.databinding.FragmentTimetableBinding;

public class TimeTableFragment extends Fragment {

    private FragmentTimetableBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TimeTableViewModel timeTableViewModel =
                new ViewModelProvider(this).get(TimeTableViewModel.class);

        binding = FragmentTimetableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTimeTable;
        timeTableViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
