package com.example.sueobmwodeudji.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sueobmwodeudji.fragment.coach_mark.Community;
import com.example.sueobmwodeudji.fragment.coach_mark.Home;
import com.example.sueobmwodeudji.fragment.coach_mark.Ratings;
import com.example.sueobmwodeudji.fragment.coach_mark.Settings;
import com.example.sueobmwodeudji.fragment.coach_mark.TimeTable;

public class CoachMarkPagerAdapter extends FragmentStateAdapter {
    private final int selectCnt = 5;

    public CoachMarkPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int fragmentPosition = getRealPosition(position);
        switch (fragmentPosition) {
            case 0: return new Home();
            case 1: return new TimeTable();
            case 2: return new Community();
            case 3: return new Ratings();
            case 4: return new Settings();
        }
        return new Home();
    }

    private int getRealPosition(int position) {
        return position % selectCnt;
    }

    @Override
    public int getItemCount() {
        return selectCnt;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
