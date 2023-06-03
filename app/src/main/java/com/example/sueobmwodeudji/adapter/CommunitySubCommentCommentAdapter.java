package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentCommentBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;

import java.util.ArrayList;

public class CommunitySubCommentCommentAdapter extends RecyclerView.Adapter<CommunitySubCommentCommentAdapter.CommunitySubCommentCommentViewHolder> {

    private final Context context;
    private final ArrayList<CommunitySubCommentCommentModel> commentModels;

    public CommunitySubCommentCommentAdapter(Context context, ArrayList<CommunitySubCommentCommentModel> commentModels) {
        this.context = context;
        this.commentModels = (commentModels != null)? commentModels : new ArrayList<>();
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
        holder.onBind(commentModels.get(position));
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    public static class CommunitySubCommentCommentViewHolder extends RecyclerView.ViewHolder{
        ItemCommunityCommentCommentBinding binding;
        public CommunitySubCommentCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCommunityCommentCommentBinding.bind(itemView);
        }

        public void onBind(CommunitySubCommentCommentModel data){
            binding.idTv.setText(data.getName());
            binding.dateTv.setText(data.getTimestamp().toString());
            binding.contentTv.setText(data.getContent());
        }
    }
}
