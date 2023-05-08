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
import com.example.sueobmwodeudji.databinding.FragmentRatingsBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.LinkedList;
import java.util.List;

public class RatingsFragment extends Fragment {
    private FragmentRatingsBinding binding;
    private List<BasicFrameModel> list = new LinkedList<BasicFrameModel>();

    public static RatingsFragment getInstance() {
        return new RatingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        list.add(new BasicFrameModel("나의 수업", R.layout.item_ratings_sub_1));
        list.add(new BasicFrameModel("최근 수강평", R.layout.item_ratings_sub_2));
        binding.ratingsFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.ratingsFragment.setAdapter(new BasicFrameAdapter(getContext(), list));
    }

}
