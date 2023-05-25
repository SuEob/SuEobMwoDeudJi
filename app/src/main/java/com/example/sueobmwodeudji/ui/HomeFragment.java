package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.SearchActivity;
import com.example.sueobmwodeudji.adapter.HomePopularPostAdapter;
import com.example.sueobmwodeudji.adapter.HomeTimeTableAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;
import com.example.sueobmwodeudji.dto.HomeTimaTableData;
import com.example.sueobmwodeudji.model.HomePopularPostData;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private LinearLayout indicators;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선
        HomeTimeTableView();
        HomeIndicators();
        HomePopularPost();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu(); // onCreateOptionsMenu() 재호출
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.frame_tool_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void HomeTimeTableView() {
        List<List<HomeTimaTableData>> list = new ArrayList<>();
        List<HomeTimaTableData> monList = new ArrayList<>();
        monList.add(new HomeTimaTableData( "1교시 수학"));
        monList.add(new HomeTimaTableData( "2교시 수학"));
        monList.add(new HomeTimaTableData("3교시 수학"));

        List<HomeTimaTableData> tueList = new ArrayList<>();
        tueList.add(new HomeTimaTableData( "1교시 국어"));
        tueList.add(new HomeTimaTableData( "2교시 국어"));
        tueList.add(new HomeTimaTableData("3교시 국어"));

        list.add(monList);
        list.add(tueList);


        HomeTimeTableAdapter adapter = new HomeTimeTableAdapter(getContext(), list);
        binding.homeTimeTableViewPager.setAdapter(adapter);


//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        binding.homeTimeTableViewPager.setClipToPadding(false);
//        binding.homeTimeTableViewPager.setPadding(100,0,100,0);




    }

    public void HomeIndicators() {

    }



    public void HomePopularPost() {
        List<HomePopularPostData> list = new ArrayList<>();
        list.add(new HomePopularPostData("흠..", "05.23"));
        list.add(new HomePopularPostData("헉!", "05.13"));

        HomePopularPostAdapter adapter = new HomePopularPostAdapter(getContext(), list);
        binding.homePopularPost.setAdapter(adapter);
        binding.homePopularPost.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}