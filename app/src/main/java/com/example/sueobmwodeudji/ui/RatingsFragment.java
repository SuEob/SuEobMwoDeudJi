package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.RatingsSubPostActivity;
import com.example.sueobmwodeudji.SearchActivity;
import com.example.sueobmwodeudji.adapter.CommunitySubRecentListAdapter;
import com.example.sueobmwodeudji.adapter.RatingsSubRecentListAdapter;
import com.example.sueobmwodeudji.adapter.ViewPagerAdapter;
import com.example.sueobmwodeudji.databinding.FragmentRatingsBinding;
import com.example.sueobmwodeudji.dto.RatingMyClassData;
import com.example.sueobmwodeudji.dto.TimeTableDTO;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.CommunitySubRecentListModel;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.example.sueobmwodeudji.model.RatingsSubRecentListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RatingsFragment extends Fragment {
    private FragmentRatingsBinding binding;

    private String mCollection, mSchool, mEmail;

    private ArrayList<ArrayList<String>> sueobs = new ArrayList<>();

    private FirebaseFirestore mFirestore;
    private ArrayList<String> categorys;

    public static RatingsFragment getInstance() {
        return new RatingsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRatingsBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선
        String tool_bar_title = "평가";
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(tool_bar_title);
        mFirestore = FirebaseFirestore.getInstance();

        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // DB PATH Setting
        dbPathSetting();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu(); // onCreateOptionsMenu() 재호출
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.tool_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void dbPathSetting() {
        mCollection = "평가";
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
                        readTimeTable();
                        readCategory();
                        //ratingsItemView();
                        //setRecentListRecyclerView();
                    }
                });
    }

    private void ratingsItemView() {
        readTimeTable();
//        ArrayList<ArrayList<RatingMyClassData>> listData = new ArrayList<>();
//        ArrayList<RatingMyClassData> list1 = new ArrayList<>();
//        list1.add(new RatingMyClassData("인공지능"));
//        list1.add(new RatingMyClassData("자료구조"));
//        list1.add(new RatingMyClassData("모바일캡스톤"));
//
//        ArrayList<RatingMyClassData> list2 = new ArrayList<>();
//        list2.add(new RatingMyClassData("모바일캡스톤"));
//        list2.add(new RatingMyClassData("모바일캡스톤"));
//        list2.add(new RatingMyClassData("모바일캡스톤"));
//
//        ArrayList<RatingMyClassData> list3 = new ArrayList<>();
//        list3.add(new RatingMyClassData("모바일캡스톤"));
//
//        listData.add(list1);
//        listData.add(list2);
//        listData.add(list3);
//
//        binding.viewPager2.setAdapter(new ViewPagerAdapter(listData));
    }

    private void readTimeTable() {
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("시간표")
                .whereEqualTo("email", mEmail)
                .whereEqualTo("selected", true)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if(documentSnapshots.isEmpty() || getActivity() == null) return;
                        TimeTableDTO dto = documentSnapshots.getDocuments().get(0).toObject(TimeTableDTO.class);

                        createSueobs(dto);

                    }
                });
        //ArrayList<ArrayList<RatingMyClassData>> listData = new ArrayList<>();
    }

    private void createSueobs(TimeTableDTO dto) {
        sueobs.add(dto.getMon());
        sueobs.add(dto.getTue());
        sueobs.add(dto.getWed());
        sueobs.add(dto.getThu());
        sueobs.add(dto.getFri());

        ArrayList<String> s = new ArrayList<>();

        for (ArrayList<String> list : sueobs) {
            for (String string : list) {
                if (!string.equals("")) {
                    s.add(string);
                }
            }
        }

        HashSet<String> set = new HashSet<>(s);
        s = new ArrayList<>(set);
        ArrayList<ArrayList<String>> data = new ArrayList<>();

        ArrayList<String> d = new ArrayList<>();

        for(int i = 1; i <= s.size(); i++){
            d.add((s.get(i-1)));
            if(i%3 == 0) {
                data.add(d);
                d = new ArrayList<>();
            }
        }
        if(d.size() != 0) data.add(d);

        binding.viewPager2.setAdapter(new ViewPagerAdapter(data));
    }

    private void readCategory() {
        mFirestore.collection("수업")
                .document(mSchool)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (getActivity() == null) return;
                        categorys = (ArrayList<String>) documentSnapshot.get("categorys");
                        setRecentListRecyclerView();
                    }
                });
    }

    private void setRecentListRecyclerView() {
        RatingsSubRecentListAdapter adapter = new RatingsSubRecentListAdapter(getContext(), createRatingsQuery());
        adapter.setOricl(new RatingsSubRecentListAdapter.OnRecentItemClickLintener() {
            @Override
            public void onClick(RatingsSubListModel data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), RatingsSubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("class_name", data.getClassName());
                startActivity(intent);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private ArrayList<Query> createRatingsQuery() {
        ArrayList<Query> query = new ArrayList<>();
        for (String category : categorys) {
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            query.add(mFirestore.collection(mCollection)
                    .document(mSchool)
                    .collection(category)
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(3));
        }
        return query;
    }
}
