package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.CommunitySubListActivity;
import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.adapter.CommunitySubRecentListAdapter;
import com.example.sueobmwodeudji.databinding.FragmentCommunityBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        CommunitySubRecentListAdapter adapter = new CommunitySubRecentListAdapter(getContext(), createCategoryQuery());
        adapter.setOricl(new CommunitySubRecentListAdapter.OnRecentItemClickLintener() {
            @Override
            public void onClick(CommunitySubListModel data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CommunitySubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("subject", data.getCategory());
                startActivity(intent);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private ArrayList<Query> createCategoryQuery(){
        String[] categorys = {"1학년 대화방", "2학년 대화방", "3학년 대화방", "게임 게시판", "공부 게시판", "운동 게시판"};
        ArrayList<Query> query = new ArrayList<>();
        for (String category : categorys) {
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            query.add(mFirestore.collection("testPost")
                    .document("first")
                    .collection(category)
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(3));
        }
        return query;
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