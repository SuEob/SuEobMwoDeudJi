package com.example.sueobmwodeudji.ClickEvent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.Fragment.FragPage;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationVeiwListener implements NavigationBarView.OnItemSelectedListener {
    MainActivity mainActivity;
    public  NavigationVeiwListener(Context context){
        mainActivity = (MainActivity) context;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.naviHome:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.navigationBarTest, new FragPage(R.layout.activity_home)).commit();
                break;
            case R.id.naviTime:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.navigationBarTest, new FragPage(R.layout.activity_time)).commit();
                break;
            case R.id.naviCommunity:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.navigationBarTest, new FragPage(R.layout.activity_community)).commit();
                break;
            case R.id.naviGood:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.navigationBarTest, new FragPage(R.layout.activity_good)).commit();
                break;
            case R.id.naviSetting:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.navigationBarTest, new FragPage(R.layout.activity_settings)).commit();
                break;
        }
        return true;
    }
}
