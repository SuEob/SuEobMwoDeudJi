package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;

import java.util.List;

public class CommunitySubCommentCommentAdapter extends RecyclerView.Adapter<CommunitySubCommentCommentAdapter.CommunitySubCommentCommentViewHolder> {

    private final Context context;
    private final List<CommunitySubCommentCommentModel> commentModels;

    public CommunitySubCommentCommentAdapter(Context context, List<CommunitySubCommentCommentModel> commentModels) {
        this.context = context;
        this.commentModels = commentModels;
    }

    @NonNull
    @Override
    public CommunitySubCommentCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_comment_comment, parent, false);

        return new CommunitySubCommentCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubCommentCommentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    public static class CommunitySubCommentCommentViewHolder extends RecyclerView.ViewHolder{
        public CommunitySubCommentCommentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
