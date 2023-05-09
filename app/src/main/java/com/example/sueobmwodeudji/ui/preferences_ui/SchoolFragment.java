package com.example.sueobmwodeudji.ui.preferences_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.databinding.PreferencesSchoolBinding;

public class SchoolFragment extends Fragment {
    private PreferencesSchoolBinding binding;

    public static SchoolFragment getInstance() {
        return new SchoolFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PreferencesSchoolBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
