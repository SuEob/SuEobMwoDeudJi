package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;

import java.util.LinkedList;
import java.util.List;

public class CommunitySubCommentAdapter extends RecyclerView.Adapter<CommunitySubCommentAdapter.CommunitySubCommentViewHolder>{
    private final Context context;
    private final List<CommunitySubCommentModel> commentModels;

    public CommunitySubCommentAdapter(Context context, List<CommunitySubCommentModel> commentModels) {
        this.context = context;
        this.commentModels = commentModels;
    }

    @NonNull
    @Override
    public CommunitySubCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_comment, parent, false);

        return new CommunitySubCommentViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubCommentViewHolder holder, int position) {
        holder.onBind(commentModels.get(position));
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    public static class CommunitySubCommentViewHolder extends RecyclerView.ViewHolder{
        private final Context context;
        private final ItemCommunityCommentBinding binding;
        public CommunitySubCommentViewHolder(Context _context, @NonNull View itemView) {
            super(itemView);
            context = _context;
            binding = ItemCommunityCommentBinding.bind(itemView);
        }

        public void onBind(CommunitySubCommentModel data){
            LinkedList<CommunitySubCommentCommentModel> list = new LinkedList<>();
            list.add(new CommunitySubCommentCommentModel("a"));
            list.add(new CommunitySubCommentCommentModel("a"));
            list.add(new CommunitySubCommentCommentModel("a"));
            CommunitySubCommentCommentAdapter adapter = new CommunitySubCommentCommentAdapter(context, list);
            binding.recyclerView.setAdapter(adapter);
        }
    }
}
