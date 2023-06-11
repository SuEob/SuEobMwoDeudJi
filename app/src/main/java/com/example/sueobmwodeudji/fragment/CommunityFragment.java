package com.example.sueobmwodeudji.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.CommunitySubListActivity;
import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.SearchActivity;
import com.example.sueobmwodeudji.adapter.CommunitySubRecentListAdapter;
import com.example.sueobmwodeudji.databinding.FragmentCommunityBinding;
import com.example.sueobmwodeudji.dto.CommunitySubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;


public class CommunityFragment extends Fragment {
    private FragmentCommunityBinding binding;

    private String mCollection, mSchool, mEmail;

    public static CommunityFragment getInstance() {
        return new CommunityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommunityBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선
        String tool_bar_title = "커뮤니티";
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(tool_bar_title);

        mEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        // DB PATH Setting
        dbPathSetting();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
        Log.d("afsdsdaf", getActivity().getSupportFragmentManager().getFragments().toString());
        //addSearchViewEvent();
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
        inflater.inflate(R.menu.community_tool_bar, menu);
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
                        if(getActivity() == null) return;
                        mSchool = documentSnapshot.getString("school_name");
                        addCategoryButtonEvent();
                        setRecentListRecyclerView();
                    }
                });
    }

    private void addCategoryButtonEvent() {
        Button[] buttons = { binding.class1Btn, binding.class2Btn, binding.class3Btn,
                binding.gameBtn, binding.bookBtn, binding.anchorBtn};
        for (Button button : buttons) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), CommunitySubListActivity.class);
                    intent.putExtra("subject", button.getText().toString());

                    startActivity(intent);
                }
            });
        }
    }

    private void setRecentListRecyclerView(){
        CommunitySubRecentListAdapter adapter = new CommunitySubRecentListAdapter(getContext(), createCategoryQuery());
        adapter.setOricl(new CommunitySubRecentListAdapter.OnRecentItemClickLintener() {
            @Override
            public void onClick(CommunitySubListModel data) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CommunitySubPostActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("subject", data.getCategory());
                startActivity(intent);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    private ArrayList<Query> createCategoryQuery(){
        String[] categorys = {"1학년 대화방", "2학년 대화방", "3학년 대화방", "게임 게시판", "공부 게시판", "운동 게시판"};
        ArrayList<Query> query = new ArrayList<>();
        for (String category : categorys) {
            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
            query.add(mFirestore.collection(mCollection)
                    .document(mSchool)
                    .collection(category)
                    .orderBy("timestamp", Query.Direction.DESCENDING)
                    .limit(10));
        }
        return query;
    }

   /* private void addSearchViewEvent(){
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getActivity(), CommunitySubSearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }*/

}