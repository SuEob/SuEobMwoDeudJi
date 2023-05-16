package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.CommunitySubListActivity;
import com.example.sueobmwodeudji.CommunitySubSearchActivity;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.adapter.CommunitySubRecentListAdapter;
import com.example.sueobmwodeudji.databinding.ActivityMainBinding;
import com.example.sueobmwodeudji.databinding.FragmentCommunityBinding;
import com.example.sueobmwodeudji.dto.RatingMyClassData;
import com.example.sueobmwodeudji.model.CommunitySubRecentListModel;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;


public class CommunityFragment extends Fragment {
    private FragmentCommunityBinding binding;

    public static CommunityFragment getInstance() {
        return new CommunityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommunityBinding.inflate(inflater, container, false);

        String tool_bar_title = "커뮤니티";
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(tool_bar_title);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        addCategoryButtonEvent();
        setRecentListRecyclerView();
        //addSearchViewEvent();
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
                    Intent intent = new Intent(getActivity(), CommunitySubListActivity.class);
                    intent.putExtra("subject", button.getText().toString());

                    startActivity(intent);
                }
            });

        }
    }

    private void setRecentListRecyclerView(){
        ArrayList<CommunitySubRecentListModel> list = new ArrayList<>();
        list.add(new CommunitySubRecentListModel("제목1", "닉네임1", "내용내용내용내용내용"));
        list.add(new CommunitySubRecentListModel("제목2", "닉네임2", "내용내용내용내용내용내용내용내용내용내용"));
        list.add(new CommunitySubRecentListModel("제목3", "닉네임3", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용"));
        list.add(new CommunitySubRecentListModel("제목4", "닉네임4", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용"));
        list.add(new CommunitySubRecentListModel("제목5", "닉네임5", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용"));
        list.add(new CommunitySubRecentListModel("제목6", "닉네임6", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용"));
        list.add(new CommunitySubRecentListModel("제목7", "닉네임7", "내용내용내용내용내용내용내용내용내용내용내용내용내용내용내용"));
        list.add(new CommunitySubRecentListModel("제목8", "닉네임8", "내용내용내용내용내용내용내용내용내용내용"));

        CommunitySubRecentListAdapter adapter = new CommunitySubRecentListAdapter(getContext(), list);
        binding.recyclerView.setAdapter(adapter);
    }

   /* private void addSearchViewEvent(){
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
    }*/

}