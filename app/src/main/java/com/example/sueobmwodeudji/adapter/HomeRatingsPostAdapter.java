package com.example.sueobmwodeudji.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemHomePopularRatingsBinding;
import com.example.sueobmwodeudji.model.HomePopularRatingsData;

import java.util.List;

public class HomeRatingsPostAdapter extends RecyclerView.Adapter<HomeRatingsPostAdapter.HomeRatingsPostViewHolder> {
    private List<HomePopularRatingsData> list;

    public HomeRatingsPostAdapter(List<HomePopularRatingsData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HomeRatingsPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomePopularRatingsBinding binding = ItemHomePopularRatingsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        HomeRatingsPostViewHolder viewHolder = new HomeRatingsPostViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRatingsPostViewHolder holder, int position) {
        holder.postTitle.setText(list.get(position).getPost_title());
        holder.postContent.setText(list.get(position).getPost_content());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeRatingsPostViewHolder extends RecyclerView.ViewHolder {
        private ItemHomePopularRatingsBinding binding;
        public TextView postTitle;
        public TextView postContent;

        public HomeRatingsPostViewHolder(@NonNull ItemHomePopularRatingsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            postTitle = binding.homePostTitle;
            postContent = binding.homePostContent;

        }
    }
}
