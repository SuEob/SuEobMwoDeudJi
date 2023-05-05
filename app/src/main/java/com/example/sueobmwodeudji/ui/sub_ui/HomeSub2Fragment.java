package com.example.sueobmwodeudji.ui.sub_ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeSub2Binding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.ArrayList;
import java.util.List;

public class HomeSub2Fragment extends Fragment {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private FragmentHomeSub2Binding binding;
    private List<BasicFrameModel> list = new ArrayList<BasicFrameModel>();

    public static HomeSub2Fragment getInstance(int no) {
        HomeSub2Fragment fragment = new HomeSub2Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ARG_NO", no);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeSub2Binding.inflate(inflater, container, false);
        SubCommunityItemView();
        return binding.getRoot();
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
    }
}
