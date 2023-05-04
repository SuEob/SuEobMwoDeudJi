package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemListBinding;

import java.util.List;

public class CommunityTitleAdapter extends RecyclerView.Adapter<CommunityTitleAdapter.CommunityTitleViewHolder>{
    private Context context;
    private List<BasicFrame> basicFrame;

    public CommunityTitleAdapter(Context context, List<BasicFrame> basicFrame) {
        this.context = context;
        this.basicFrame = basicFrame;
    }

    @NonNull
    @Override
    public CommunityTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        CommunityTitleViewHolder viewHolder = new CommunityTitleViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityTitleViewHolder holder, int position) {
        //holder.communityTitleBind(basicFrame.get(position));
        holder.community_title.setText(basicFrame.get(position).community_title);
        holder.community_title.setText(basicFrame.get(position).post_title);
    }

    @Override
    public int getItemCount() {
        return basicFrame.size();
    }

    public static class CommunityTitleViewHolder extends RecyclerView.ViewHolder {
        private ItemListBinding binding;
        public TextView community_title;
        public TextView post_title;
        public ImageView item_ratings;
        public ImageView item_chating;

        public CommunityTitleViewHolder(@NonNull ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            community_title = binding.itemCommunityTitle;
            post_title = binding.itemPostTitle;
            item_ratings = binding.itemRatings;
            item_chating = binding.itemChating;
        }
    }
}
