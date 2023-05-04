package com.example.sueobmwodeudji.ui.sub_ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.adapter.BasicFrame;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.ItemBasicFrameBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommunityListFragment extends Fragment {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private ItemBasicFrameBinding binding;
    private List<BasicFrame> list = new ArrayList<BasicFrame>();
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ItemBasicFrameBinding.inflate(inflater, container, false);
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
        list = Arrays.asList(
                new BasicFrame("1학년 대화방", "글제목"),
                new BasicFrame("3학년 대화방", "글제목")
        );
        binding.homeSubFragment.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        binding.homeSubFragment.setAdapter(new BasicFrameAdapter(context, list));
        // binding.subFragment.setRecycledViewPool(viewPool);
        Log.e("getActivity(CommunityListFragment)", String.valueOf(getActivity()));
    }
}
