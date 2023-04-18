package com.example.sueobmwodeudji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sueobmwodeudji.ui.CommunityFragment;
import com.example.sueobmwodeudji.ui.HomeFragment;
import com.example.sueobmwodeudji.ui.RatingsFragment;
import com.example.sueobmwodeudji.ui.SettingsFragment;
import com.example.sueobmwodeudji.ui.TimeTableFragment;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarView;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    TimeTableFragment timeTableFragment;
    CommunityFragment communityFragment;
    RatingsFragment ratingsFragment;
    SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        timeTableFragment = new TimeTableFragment();
        communityFragment = new CommunityFragment();
        ratingsFragment = new RatingsFragment();
        settingsFragment = new SettingsFragment();

        // 기본화면 설정(메인화면
        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();

        //
        NavigationBarView navigationBarView = findViewById(R.id.bottom_nav_view);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();
                        return true;
                    case R.id.navigation_time_table:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, timeTableFragment).commit();
                        return true;
                    case R.id.navigation_community:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, communityFragment).commit();
                        return true;
                    case R.id.navigation_ratings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, ratingsFragment).commit();
                        return true;
                    case R.id.navigation_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, settingsFragment).commit();
                        return true;
                }
                return false;
            }
        });


    }
}