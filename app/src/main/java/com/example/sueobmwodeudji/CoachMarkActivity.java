package com.example.sueobmwodeudji;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sueobmwodeudji.adapter.CoachMarkPagerAdapter;
import com.example.sueobmwodeudji.databinding.ActivityCoachMarkBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CoachMarkActivity extends AppCompatActivity {
    private ActivityCoachMarkBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoachMarkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CoachMarkPagerAdapter adapter = new CoachMarkPagerAdapter(this);
        binding.viewpager2.setAdapter(adapter);
        binding.viewpager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.viewpager2.setOffscreenPageLimit(5);

        new TabLayoutMediator(binding.tabLayout, binding.viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        }).attach();
    }

}
