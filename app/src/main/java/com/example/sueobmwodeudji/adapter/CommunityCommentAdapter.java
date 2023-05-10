package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.custom_view.CommunityCommentViewHolder;
import com.example.sueobmwodeudji.model.CommunityCommentModel;

import java.util.List;

public class CommunityCommentAdapter extends RecyclerView.Adapter<CommunityCommentViewHolder>{
    private Context context;
    private List<CommunityCommentModel> commentModels;

    public CommunityCommentAdapter(Context context, List<CommunityCommentModel> commentModels) {
        this.context = context;
        this.commentModels = commentModels;
    }

    @NonNull
    @Override
    public CommunityCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_comment, parent, false);

        return new CommunityCommentViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityCommentViewHolder holder, int position) {
        holder.onBind(commentModels.get(position));
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }
}
