package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemHomePopularPostBinding;
import com.example.sueobmwodeudji.model.HomePopularPostData;

import java.util.List;

public class HomePopularPostAdapter extends RecyclerView.Adapter<HomePopularPostAdapter.HomePopularPostViewHolder> {
    private Context context;
    private List<HomePopularPostData> list;

    public HomePopularPostAdapter(Context context, List<HomePopularPostData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomePopularPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomePopularPostBinding binding = ItemHomePopularPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        HomePopularPostViewHolder viewHolder = new HomePopularPostViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePopularPostAdapter.HomePopularPostViewHolder holder, int position) {
        holder.postTitle.setText(list.get(position).post_title);
        holder.postDate.setText(list.get(position).post_date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HomePopularPostViewHolder extends RecyclerView.ViewHolder {
        private ItemHomePopularPostBinding binding;
        public TextView postTitle;
        public TextView postDate;

        public HomePopularPostViewHolder(@NonNull ItemHomePopularPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            postTitle = binding.homePostTitle;;
            postDate = binding.homePostDate;;
        }
    }
}
