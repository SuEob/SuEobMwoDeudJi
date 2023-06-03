package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityRecentListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.CommunitySubRecentListModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommunitySubRecentListAdapter extends RecyclerView.Adapter<CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder> implements EventListener<QuerySnapshot> {
    protected final Context context;
    private ArrayList<Query> mQuerys;
    private final ArrayList<CommunitySubListModel> communitySubRecentListModels = new ArrayList<>();
    private static OnRecentItemClickLintener oricl;
    public void setOricl(OnRecentItemClickLintener onListener) {
        oricl = onListener;
    }
    public interface OnRecentItemClickLintener{
        void onClick(CommunitySubListModel data);
    }

    public CommunitySubRecentListAdapter(Context context, ArrayList<Query> querys) {
        this.context = context;
        mQuerys = querys;
        addQuerySnapshotListener();
    }

    private void addQuerySnapshotListener() {
        for(Query query : mQuerys){
            query.addSnapshotListener(this);
        }
    }

    @NonNull
    @Override
    public CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_recent_list, parent, false);

        return new CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder holder, int position) {
        holder.onBind(communitySubRecentListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return communitySubRecentListModels.size();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e != null){
            Log.w("list 에러","onEvent:error", e);
        }
        for(DocumentChange change : documentSnapshots.getDocumentChanges()){
            switch (change.getType()){
                case ADDED:
                    onDocumentAdded(change);
                    break;
            }
        }
    }

    private void onDocumentAdded(DocumentChange change) {
        communitySubRecentListModels.add(change.getDocument().toObject(CommunitySubListModel.class));
        Collections.reverse(communitySubRecentListModels);
        notifyDataSetChanged();
    }

    public static class CommunitySubRecentListViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private TextView title, nickname, content;
        private ConstraintLayout layout;

        public CommunitySubRecentListViewHolder(Context _context, View itemView) {
            super(itemView);
            ItemCommunityRecentListBinding binding = ItemCommunityRecentListBinding.bind(itemView);
            context = _context;
            title = binding.titleTv;
            nickname = binding.nicknameTv;
            content = binding.contentTv;
            layout = binding.layout;
        }

        public void onBind(CommunitySubListModel data){
            title.setText(data.getTitle());
            nickname.setText(data.getCategory());
            content.setText(data.getContent());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oricl.onClick(data);
                }
            });
        }
    }
}