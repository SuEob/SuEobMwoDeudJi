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
import com.example.sueobmwodeudji.databinding.ItemCommunityListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CommunitySubListAdapter extends RecyclerView.Adapter<CommunitySubListAdapter.CommunitySubListViewHolder> implements EventListener<QuerySnapshot> {
    private final Context context;
    private final Query mQuery;
    private ArrayList<CommunitySubListModel> communitySubListModels = new ArrayList<>();

    public CommunitySubListAdapter(Context context, Query query) {
        this.context = context;
        this.mQuery = query;
        mQuery.addSnapshotListener(this);
    }

    @NonNull
    @Override
    public CommunitySubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_list, parent, false);

        return new CommunitySubListViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubListViewHolder holder, int position) {
        holder.onBind(communitySubListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return communitySubListModels.size();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e != null){
            Log.w("list 에러","onEvent:error", e);
        }
        int i =0;
        communitySubListModels.clear();
        for(DocumentSnapshot doc : documentSnapshots.getDocuments()){
            communitySubListModels.add(doc.toObject(CommunitySubListModel.class));
            notifyDataSetChanged();
        }
    }

    public static class CommunitySubListViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final TextView title, content;
        private final ConstraintLayout layout;

        public CommunitySubListViewHolder(Context _context, View itemView) {
            super(itemView);
            ItemCommunityListBinding binding = ItemCommunityListBinding.bind(itemView);
            context = _context;
            title = binding.titleTv;
            content = binding.contentTv;
            layout = binding.layout;
        }
        public void onBind(CommunitySubListModel data){
            title.setText(data.getTitle());
            content.setText(data.getContent());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CommunitySubPostActivity.class);
                    intent.putExtra("data", data);
                    context.startActivity(intent);
                }
            });
        }
    }
}
