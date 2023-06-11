package com.example.sueobmwodeudji;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.example.sueobmwodeudji.databinding.ActivityMainBinding;
import com.example.sueobmwodeudji.fragment.CommunityFragment;
import com.example.sueobmwodeudji.fragment.HomeFragment;
import com.example.sueobmwodeudji.fragment.RatingsFragment;
import com.example.sueobmwodeudji.fragment.SettingsFragment;
import com.example.sueobmwodeudji.fragment.TimeTableFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    public static boolean dark_mode;

    private ActivityMainBinding binding;
    private long backKeyPressedTime = 0L;

    private HomeFragment homeFragment;
    private TimeTableFragment timeTableFragment;
    private CommunityFragment communityFragment;
    private RatingsFragment ratingsFragment;
    private SettingsFragment settingsFragment;

    @Override
    public void onBackPressed() {
//        안드로이드에서 기본으로 제공하는 뒤로가기
//        super.onBackPressed();

//      2000 = 2초 체크
        if (System.currentTimeMillis() - backKeyPressedTime >= 2000 ) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
            return;
        }
        
//      2초 이내에 뒤로가기 버튼을 한번 더 누르면 종료
        if (System.currentTimeMillis() - backKeyPressedTime < 2000 ) {
            finish(); // 액티비티 종료
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeFragment = new HomeFragment();
        timeTableFragment = new TimeTableFragment();
        communityFragment = new CommunityFragment();
        ratingsFragment = new RatingsFragment();
        settingsFragment = new SettingsFragment();

        testAuth();

        // SharedPreferences 에서 저장한 값 불러오기
        String mode_text = SettingsFragment.modeLoad(getApplicationContext());
        if (mode_text.equals("light")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (mode_text.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        // 툴바
        Toolbar toolbar = binding.toolBar.mainToolBar;
        setSupportActionBar(toolbar);

        BottomNavBar(); // 네비게이션 바
    }

    // 네비게이션바 클릭시 프래크먼트 이동
    private void BottomNavBar() {

        if (dark_mode) {
            // 다크모드 switch 클릭시 설정화면
            getSupportFragmentManager().beginTransaction().replace(R.id.containers, settingsFragment).commit();
            getSupportActionBar().setTitle("설정");
        } else {
            // 기본화면 설정(홈 화면)
            getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();
            getSupportActionBar().setTitle("홈");
        }

        // 화면 바뀜
        binding.bottomNavView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, homeFragment)
                            .commit();
                    getSupportActionBar().setTitle("홈");
                    return true;
                case R.id.navigation_time_table:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, timeTableFragment)
                            .commit();
                    getSupportActionBar().setTitle("시간표");
                    return true;
                case R.id.navigation_community:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, communityFragment)
                            .commit();
                    getSupportActionBar().setTitle("커뮤니티");
                    return true;
                case R.id.navigation_ratings:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, ratingsFragment)
                            .commit();
                    getSupportActionBar().setTitle("평가");
                    return true;
                case R.id.navigation_settings:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containers, settingsFragment)
                            .commit();
                    getSupportActionBar().setTitle("설정");
                    return true;
            }
            return false;
        });
    }

    private void testAuth(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            String uid = user.getUid();

            Log.d("asdffads", name + email + uid);
        }
    }
}