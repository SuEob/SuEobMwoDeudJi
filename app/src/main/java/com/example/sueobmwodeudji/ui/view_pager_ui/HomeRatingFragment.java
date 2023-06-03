package com.example.sueobmwodeudji.ui.view_pager_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.databinding.FragmentHomeSubRatingsBinding;

public class HomeRatingFragment extends Fragment {
    private FragmentHomeSubRatingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeSubRatingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setInit(View view) {

    }
}
