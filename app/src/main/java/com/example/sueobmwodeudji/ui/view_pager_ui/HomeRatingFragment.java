package com.example.sueobmwodeudji.ui.view_pager_ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.RatingsSubPostActivity;
import com.example.sueobmwodeudji.adapter.HomeRatingsPostAdapter;
import com.example.sueobmwodeudji.adapter.RatingsSubRecentListAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeSubRatingsBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.HomePopularRatingsData;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeRatingFragment extends Fragment {
    private FragmentHomeSubRatingsBinding binding;
    private FirebaseFirestore mFirestore;
    private ArrayList<String> categorys;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeSubRatingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선

        mFirestore = FirebaseFirestore.getInstance();

        HomeViewPager();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void HomeViewPager() {
        readCategory();
    }

    private void readCategory() {
        mFirestore.collection("수업")
                .document("가 고등학교")
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (getActivity() == null) return;
                        categorys = (ArrayList<String>) documentSnapshot.get("categorys");
                        setAdapter();
                    }
                });
    }

    private void setAdapter() {
        HomeRatingsPostAdapter adapter = new HomeRatingsPostAdapter(getContext(), readData());
        binding.homePopularRatings.setAdapter(adapter);
        adapter.setOrcl(new HomeRatingsPostAdapter.OnRatingsListClickListener() {
            @Override
            public void onClick(RatingsSubListModel data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RatingsSubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("class_name", data.getClassName());
                startActivity(intent);
            }
        });
        binding.homePopularRatings.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private ArrayList<CollectionReference> readData() {
        ArrayList<CollectionReference> colReferences = new ArrayList<>();
        for (String category : categorys) {
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            colReferences.add(mFirestore.collection("testRating")
                    .document("first")
                    .collection(category));
        }
        return colReferences;
    }
}
