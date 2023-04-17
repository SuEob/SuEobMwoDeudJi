package com.example.sueobmwodeudji.ui.rating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sueobmwodeudji.databinding.FragmentRatingBinding;

public class RatingFragment extends Fragment {

    private FragmentRatingBinding binding;

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RatingViewModel ratingViewModel =
                new ViewModelProvider(this).get(RatingViewModel.class);

        binding = FragmentRatingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRating;
        ratingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
