package com.example.sueobmwodeudji.ui.sub_ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeSub2Binding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.ArrayList;
import java.util.List;

public class HomeSub2Fragment extends Fragment {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private FragmentHomeSub2Binding binding;
    FrameLayout frameLayout;
    private List<BasicFrameModel> list = new ArrayList<BasicFrameModel>();


    public static HomeSub2Fragment getInstance(int num) {
        HomeSub2Fragment homeSub2Fragment = new HomeSub2Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        homeSub2Fragment.setArguments(bundle);
        return homeSub2Fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_home_sub_2, container, false);
        binding = FragmentHomeSub2Binding.inflate(inflater, container, false);
        SubCommunityItemView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("LongLogTag")
    private void SubCommunityItemView() {
//        list = Arrays.asList(
//                new BasicFrameModel("1학년 대화방", "글제목"),
//                new BasicFrameModel("3학년 대화방", "글제목")
//        );
        binding.homeSub2Fragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeSub2Fragment.setAdapter(new BasicFrameAdapter(getContext(), list));
        // binding.subFragment.setRecycledViewPool(viewPool);
    }
}
