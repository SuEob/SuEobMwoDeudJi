package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.CommunityActivity;
import com.example.sueobmwodeudji.CommunitySubSearchActivity;
import com.example.sueobmwodeudji.databinding.FragmentCommunityBinding;


public class CommunityFragment extends Fragment {
    private FragmentCommunityBinding binding;

    public static CommunityFragment getInstance() {
        return new CommunityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommunityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        addCategoryButtonEvent();
        addSearchViewEvent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addCategoryButtonEvent() {
        Button[] buttons = { binding.class1Btn, binding.class2Btn, binding.class3Btn,
                binding.gameBtn, binding.bookBtn, binding.anchorBtn};
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CommunityActivity.class);
                    intent.putExtra("subject", button.getText().toString());

                    startActivity(intent);
                }
            });

        }
    }

    private void addSearchViewEvent(){
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getActivity(), CommunitySubSearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}