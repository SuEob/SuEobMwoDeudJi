package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.RatingsSubPostActivity;
import com.example.sueobmwodeudji.SearchActivity;
import com.example.sueobmwodeudji.adapter.CommunitySubRecentListAdapter;
import com.example.sueobmwodeudji.adapter.RatingsSubRecentListAdapter;
import com.example.sueobmwodeudji.adapter.ViewPagerAdapter;
import com.example.sueobmwodeudji.databinding.FragmentRatingsBinding;
import com.example.sueobmwodeudji.dto.RatingMyClassData;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.CommunitySubRecentListModel;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.example.sueobmwodeudji.model.RatingsSubRecentListModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class RatingsFragment extends Fragment {
    private FragmentRatingsBinding binding;

    public static RatingsFragment getInstance() {
        return new RatingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRatingsBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선
        String tool_bar_title = "평가";
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(tool_bar_title);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);

        ratingsItemView();
        setRecentListRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu(); // onCreateOptionsMenu() 재호출
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tool_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ratingsItemView() {
        ArrayList<ArrayList<RatingMyClassData>> listData = new ArrayList<>();
        ArrayList<RatingMyClassData> list1 = new ArrayList<>();
        list1.add(new RatingMyClassData("인공지능", "김세돌"));
        list1.add(new RatingMyClassData("자료구조", "이면봉"));
        list1.add(new RatingMyClassData("모바일캡스톤", "김이박"));

        ArrayList<RatingMyClassData> list2 = new ArrayList<>();
        list2.add(new RatingMyClassData("모바일캡스톤", "김이박"));
        list2.add(new RatingMyClassData("모바일캡스톤", "김이박"));
        list2.add(new RatingMyClassData("모바일캡스톤", "김이박"));

        ArrayList<RatingMyClassData> list3 = new ArrayList<>();
        list3.add(new RatingMyClassData("모바일캡스톤", "김이박"));

        listData.add(list1);
        listData.add(list2);
        listData.add(list3);

        binding.viewPager2.setAdapter(new ViewPagerAdapter(listData));
    }

    private void setRecentListRecyclerView(){
        RatingsSubRecentListAdapter adapter = new RatingsSubRecentListAdapter(getContext(), createRatingsQuery());
        adapter.setOricl(new RatingsSubRecentListAdapter.OnRecentItemClickLintener() {
            @Override
            public void onClick(RatingsSubListModel data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RatingsSubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("class_name", data.getClassName());
                startActivity(intent);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private ArrayList<Query> createRatingsQuery(){
        String[] categorys = {"모바일캡스톤", "인공지능", "자료구조"};
        ArrayList<Query> query = new ArrayList<>();
        for (String category : categorys) {
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            query.add(mFirestore.collection("testRating")
                    .document("first")
                    .collection(category)
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(3));
        }
        return query;
    }
}
