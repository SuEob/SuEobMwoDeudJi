package com.example.sueobmwodeudji.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    // 프래그먼트 생명주기 > 뷰 생명주기 -> 그래서 없애야함
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}