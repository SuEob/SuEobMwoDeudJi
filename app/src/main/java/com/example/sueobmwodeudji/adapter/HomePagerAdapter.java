package com.example.sueobmwodeudji.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sueobmwodeudji.ui.view_pager_ui.HomePopularPostFragment;
import com.example.sueobmwodeudji.ui.view_pager_ui.HomeRatingFragment;

public class HomePagerAdapter extends FragmentStateAdapter {
    private final int selectCnt = 2;

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int fragmentPosition = getRealPosition(position);
        switch (fragmentPosition) {
            case 0: return new HomePopularPostFragment();
            case 1: return new HomeRatingFragment();
//            case 2: return new Fragment();
//            case 3: return new Fragment();
//            case 4: return new Fragment();
        }
        return new HomePopularPostFragment();
    }

    private int getRealPosition(int position) {
        Log.d("position", String.valueOf(position));
        Log.d("position % selectCnt", String.valueOf(position % selectCnt));
        return position % selectCnt;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

}

