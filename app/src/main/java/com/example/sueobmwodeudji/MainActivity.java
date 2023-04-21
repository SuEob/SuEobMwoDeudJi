package com.example.sueobmwodeudji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sueobmwodeudji.Fragment.FragPage;
import com.example.sueobmwodeudji.ui.CommunityFragment;
import com.example.sueobmwodeudji.ui.HomeFragment;
import com.example.sueobmwodeudji.ui.RatingsFragment;
import com.example.sueobmwodeudji.ui.SettingsFragment;
import com.example.sueobmwodeudji.ui.TimeTableFragment;
import com.google.android.material.navigation.NavigationBarView;

import com.example.sueobmwodeudji.ClickEvent.NavigationVeiwListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationBarView nbv = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, new FragPage(R.layout.frag_home)).commit();
        nbv.setOnItemSelectedListener(new NavigationVeiwListener(this));

    }
}