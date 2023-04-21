package com.example.sueobmwodeudji.ClickEvent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.sueobmwodeudji.Fragment.FragPage;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
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
            case R.id.navigation_home:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragPage(R.layout.frag_home)).commit();
                break;
            case R.id.navigation_time_table:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragPage(R.layout.frag_time_table)).commit();
                break;
            case R.id.navigation_community:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragPage(R.layout.frag_community)).commit();
                break;
            case R.id.navigation_ratings:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragPage(R.layout.frag_ratings)).commit();
                break;
            case R.id.navigation_settings:
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragPage(R.layout.fragment_settings)).commit();
                break;
        }
        return true;
    }
}
