package com.example.sueobmwodeudji.ui.view_pager_ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.adapter.CommunitySubRecentListAdapter;
import com.example.sueobmwodeudji.adapter.HomePopularPostAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeSubPopularPostBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.HomePopularPostData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomePopularPostFragment extends Fragment {
    private FragmentHomeSubPopularPostBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeSubPopularPostBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선

        HomeViewPager();
    }


    public void HomeViewPager() {
        HomePopularPostAdapter adapter = new HomePopularPostAdapter(getContext(), readData());
        binding.homePopularPost.setAdapter(adapter);
        adapter.setOpcl(new HomePopularPostAdapter.OnPopularPostClickListener() {
            @Override
            public void onClick(CommunitySubListModel data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CommunitySubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("subject", data.getCategory());
                startActivity(intent);
            }
        });
        binding.homePopularPost.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<CollectionReference> readData() {
        String[] categorys = {"1학년 대화방", "2학년 대화방", "3학년 대화방", "게임 게시판", "공부 게시판", "운동 게시판"};
        ArrayList<CollectionReference> colReferences = new ArrayList<>();
        for (String category : categorys) {
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            colReferences.add(mFirestore.collection("testPost")
                    .document("first")
                    .collection(category));
        }
        return colReferences;
    }
}