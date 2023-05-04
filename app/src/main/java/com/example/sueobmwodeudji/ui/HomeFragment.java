package com.example.sueobmwodeudji.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.adapter.BasicFrame;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;
import com.example.sueobmwodeudji.ui.sub_ui.HomeSub2Fragment;
import com.example.sueobmwodeudji.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<BasicFrame> list = new ArrayList<BasicFrame>();
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        HomeItemView();
        // 시발 도와줘
        getChildFragmentManager().beginTransaction().replace(R.id.basic_frame_content, new HomeSub2Fragment()).addToBackStack(null).commit();
        return binding.getRoot();
    }

    // 프래그먼트 생명주기 > 뷰 생명주기 -> 그래서 없애야함
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("LongLogTag")
    private void HomeItemView() {
        list = Arrays.asList(
                new BasicFrame("CEX")
        );
        binding.homeFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.homeFragment.setAdapter(new BasicFrameAdapter(context, list));
        Log.e("getActivity(HomeFregment)", String.valueOf(getActivity()));
    }

}