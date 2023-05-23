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
    private ArrayList<DocumentSnapshot> mSnapshots  = new ArrayList<>();

    private static OnPostClickListener opcl;

    public void setOpcl(OnPostClickListener onListener) {
        opcl = onListener;
    }
    public interface OnPostClickListener{
        void onClick(CommunitySubListModel data);
    }


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

        return new CommunitySubListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubListViewHolder holder, int position) {
        holder.onBind(mSnapshots.get(position).toObject(CommunitySubListModel.class), position);
    }

    @Override
    public int getItemCount() {
        return mSnapshots.size();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e != null){
            Log.w("list 에러","onEvent:error", e);
        }
        mSnapshots.clear();
        for(DocumentSnapshot doc : documentSnapshots.getDocuments()){
            mSnapshots.add(doc);
            notifyDataSetChanged();
        }
    }

    public static class CommunitySubListViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, content;
        private final ConstraintLayout layout;

        public CommunitySubListViewHolder(View itemView) {
            super(itemView);
            ItemCommunityListBinding binding = ItemCommunityListBinding.bind(itemView);
            title = binding.titleTv;
            content = binding.contentTv;
            layout = binding.layout;
        }
        public void onBind(CommunitySubListModel data, int positon){
            title.setText(data.getTitle());
            content.setText(data.getContent());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opcl.onClick(data);
                }
            });
        }
    }
}
