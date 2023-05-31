package com.example.sueobmwodeudji.ui;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.SearchActivity;
import com.example.sueobmwodeudji.adapter.HomePagerAdapter;
import com.example.sueobmwodeudji.adapter.HomeTimeTableAdapter;
import com.example.sueobmwodeudji.databinding.FragmentHomeBinding;
import com.example.sueobmwodeudji.dto.HomeTimeTableData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private boolean check = true;
    private int currentItemIndex;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentItemIndex++;
            binding.homeSubTitleViewPager.setCurrentItem(currentItemIndex);
        }
    };

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView","onCreateView");
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true); // Activity 보다 Fragment 우선
        Log.d("onViewCreated","onViewCreated");

        HomeTimeTableView();

        HomePagerAdapter adapter = new HomePagerAdapter(getActivity());
        binding.homePostViewPager.setAdapter(adapter);
        binding.homePostViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.homePostViewPager.setOffscreenPageLimit(1);

        List<String> list = Arrays.asList("인기 게시글", "인기 평가글");

        // TabLayout와 ViewPager2 연결
        new TabLayoutMediator(binding.tabLayout, binding.homePostViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(getContext());
                textView.setText(list.get(position));
                textView.setTextSize(20);
                textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tab.setCustomView(textView);
            }
        }).attach();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("사용자")
                .document(user.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String school_name = documentSnapshot.getString("school_name");
                        String school_username = school_name +" / " +  user.getDisplayName();

                        binding.infoTv.setText(school_username);
                    }
                });

        binding.infoTv.setText(user.getDisplayName());
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu(); // onCreateOptionsMenu() 재호출
        handler.postDelayed(runnable, 4000);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.frame_tool_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.profile) {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void HomeTimeTableView() {
        List<HomeTimeTableData> list = new ArrayList<>();

        list.add(new HomeTimeTableData("1교시 수학"));
        list.add(new HomeTimeTableData("2교시 수학"));
        list.add(new HomeTimeTableData("3교시 수학"));

        HomeTimeTableAdapter adapter = new HomeTimeTableAdapter(getContext(), list);
        binding.homeSubTitleViewPager.setAdapter(adapter);

        float currentScale = getResources().getDisplayMetrics().density;
        int currentVisibleItemPx = (int) (currentScale);
        float nextScale = getResources().getDisplayMetrics().density;
        int nextVisibleItemPxInt = (int) (-8f * nextScale);
        int pageTranslationX = nextVisibleItemPxInt + currentVisibleItemPx;

        // 페이지의 왼쪽, 오른쪽 여백 만듬 -> 겹치지 않게 하기 위해서
        binding.homeSubTitleViewPager.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.right = (int) currentVisibleItemPx;
                outRect.left = (int) currentVisibleItemPx;
            }
        });

        // 값만큼 현재 페이지 기준으로 양옆에 페이지를 미리만듬
        binding.homeSubTitleViewPager.setOffscreenPageLimit(1);

        // 양 옆 페이지들을 현재 페이지가 있는 곳으로 (-pageTranslationX * position) 만큼 이동
        binding.homeSubTitleViewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setTranslationX(-pageTranslationX * position);
            }
        });

        // 오버 스크롤 효과 비 활성화
        binding.homeSubTitleViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        // 왜 2번 실행됨? 진짜 모름
        // 현재 보이는 페이지, 999 % 3 = 0 -> index가 0인 것 부터 보여짐
        if (check) {
            binding.homeSubTitleViewPager.setCurrentItem(999, false);
            check = false;
        }

        // 자동 스크롤
        binding.homeSubTitleViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentItemIndex = position;
                Log.d("currentItemIndex", String.valueOf(currentItemIndex));
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 4000);
            }
        });

    }


}