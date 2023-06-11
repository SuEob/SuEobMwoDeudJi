package com.example.sueobmwodeudji.fragment.view_pager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.adapter.HomePopularPostAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeSubPopularPostBinding;
import com.example.sueobmwodeudji.dto.CommunitySubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomePopularPostFragment extends Fragment {
    private FragmentHomeSubPopularPostBinding binding;

    private String mCollection, mSchool, mEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentHomeSubPopularPostBinding.inflate(inflater, container, false);
        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // DB PATH Setting
        dbPathSetting();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선
    }

    private void dbPathSetting() {
        mCollection = "게시판";
        readSchool();
    }

    private void readSchool() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("사용자")
                .document(mEmail)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        mSchool = documentSnapshot.getString("school_name");
                        HomeViewPager();
                    }
                });
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
            colReferences.add(mFirestore.collection(mCollection)
                    .document(mSchool)
                    .collection(category));
        }
        return colReferences;
    }
}