package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommunitySubCommentAdapter extends RecyclerView.Adapter<CommunitySubCommentAdapter.CommunitySubCommentViewHolder>{
    private final Context context;
    private final CommunitySubListModel communitySubListModels;
    private ArrayList<CommunitySubCommentModel> commentModels = new ArrayList<>();

    public CommunitySubCommentAdapter(Context context, CommunitySubListModel communitySubListModels) {
        this.context = context;
        this.communitySubListModels = communitySubListModels;
        commentModels = communitySubListModels.getComents();
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
            binding.idTv.setText(data.getName());
            binding.dateTv.setText(data.getTimestamp().toString());
            binding.contentTv.setText(data.getContent());

            CommunitySubCommentCommentAdapter adapter = new CommunitySubCommentCommentAdapter(context, data.getCommentModels());
            binding.recyclerView.setAdapter(adapter);
        }
    }
}
