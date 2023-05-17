package com.example.sueobmwodeudji;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.databinding.ActivityMainBinding;
import com.example.sueobmwodeudji.fb.FbMainActivity;
import com.example.sueobmwodeudji.ui.CommunityFragment;
import com.example.sueobmwodeudji.ui.HomeFragment;
import com.example.sueobmwodeudji.ui.RatingsFragment;
import com.example.sueobmwodeudji.ui.SettingsFragment;
import com.example.sueobmwodeudji.ui.TimeTableFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FbMainActivity.class);
                startActivity(intent);
            }
        });

        BottomNavBar();
    }

    // 툴바 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.frame_tool_bar, menu);
        return true;
    }

    // 툴바 아이콘 클릭이 새 창으로 이동
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("title", getSupportActionBar().getTitle());

                startActivity(intent);

        }
        return false;
    }

    // 네비게이션바 클릭시 프래크먼트 이동
    private void BottomNavBar() {
        // 기본화면 설정(홈 화면)
        getSupportFragmentManager().beginTransaction().replace(R.id.containers, new HomeFragment()).commit();
        getSupportActionBar().setTitle("홈");

        // 화면 바뀜
        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, new HomeFragment())
                            .commit();
                    getSupportActionBar().setTitle("홈");
                    return true;
                case R.id.navigation_time_table:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers,  new TimeTableFragment())
                            .commit();
                    getSupportActionBar().setTitle("시간표");
                    return true;
                case R.id.navigation_community:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers,  new CommunityFragment())
                            .commit();
                    return true;
                case R.id.navigation_ratings:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, new RatingsFragment())
                            .commit();
                    return true;
                case R.id.navigation_settings:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, new SettingsFragment())
                            .commit();
                    getSupportActionBar().setTitle("설정");
                    return true;
            }
            return false;
        });
    }

}