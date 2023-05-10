package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.custom_view.CommunityCommentCommentViewHolder;
import com.example.sueobmwodeudji.custom_view.CommunityCommentViewHolder;
import com.example.sueobmwodeudji.model.CommunityCommentCommentModel;

import java.util.List;

public class CommunityCommentCommentAdapter extends RecyclerView.Adapter<CommunityCommentCommentViewHolder> {

    private Context context;
    private List<CommunityCommentCommentModel> commentModels;

    public CommunityCommentCommentAdapter(Context context, List<CommunityCommentCommentModel> commentModels) {
        this.context = context;
        this.commentModels = commentModels;
    }

    @NonNull
    @Override
    public CommunityCommentCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_comment_comment, parent, false);

        return new CommunityCommentCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityCommentCommentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }
}
