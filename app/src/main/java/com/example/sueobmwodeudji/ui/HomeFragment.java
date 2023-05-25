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
import androidx.viewpager2.widget.ViewPager2;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.SearchActivity;
import com.example.sueobmwodeudji.adapter.HomePagerAdapter;
import com.example.sueobmwodeudji.adapter.HomeTimeTableAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;
import com.example.sueobmwodeudji.dto.HomeTimaTableData;
import com.example.sueobmwodeudji.ui.view_pager_ui.HomePopularPostFragment;
import com.example.sueobmwodeudji.ui.view_pager_ui.HomeRatingFragment;
import com.google.android.material.tabs.TabLayout;

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


        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("인기 게시글"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("인기 평가글"));
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    new HomePopularPostFragment();
                } else if (position == 1) {
                    new HomeRatingFragment();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        HomePagerAdapter adapter = new HomePagerAdapter(getActivity());
        binding.homePostPager.setAdapter(adapter);
        binding.homePostPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.homePostPager.setOffscreenPageLimit(2);
//        binding.homePostPager.setCurrentItem(1000);


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
    }


}