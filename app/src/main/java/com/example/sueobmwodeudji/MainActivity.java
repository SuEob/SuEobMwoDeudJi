package com.example.sueobmwodeudji;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.ActivityMainBinding;
import com.example.sueobmwodeudji.ui.CommunityFragment;
import com.example.sueobmwodeudji.ui.HomeFragment;
import com.example.sueobmwodeudji.ui.RatingsFragment;
import com.example.sueobmwodeudji.ui.SettingsFragment;
import com.example.sueobmwodeudji.ui.TimeTableFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    TimeTableFragment timeTableFragment;
    CommunityFragment communityFragment;
    RatingsFragment ratingsFragment;
    SettingsFragment settingsFragment;

    private ActivityMainBinding binding;
    public BasicFrameAdapter basicFrameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavBar();

    }

    public void nameChange() {
        Log.d ("함수불러짐", "ㅁㄴㅇ");
    }

    private void BottomNavBar() {
        // 기본화면 설정(홈 화면)
        getSupportFragmentManager().beginTransaction().replace(R.id.containers, new HomeFragment()).commit();

        // 화면 바뀜
        binding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, new HomeFragment()).addToBackStack("home").commit();
                        return true;
                    case R.id.navigation_time_table:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers,  new TimeTableFragment()).addToBackStack("time_table").commit();
                        return true;
                    case R.id.navigation_community:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers,  new CommunityFragment()).addToBackStack("community").commit();
                        return true;
                    case R.id.navigation_ratings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, new RatingsFragment()).addToBackStack("ratings").commit();
                        return true;
                    case R.id.navigation_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, new SettingsFragment()).addToBackStack("settings").commit();
                        return true;
                }
                return false;
            }
        });
    }
}