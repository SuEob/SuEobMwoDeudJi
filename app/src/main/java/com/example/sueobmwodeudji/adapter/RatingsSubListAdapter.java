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

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.RatingsSubPostActivity;
import com.example.sueobmwodeudji.databinding.ItemRatingsListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RatingsSubListAdapter extends RecyclerView.Adapter<RatingsSubListAdapter.RatingsSubListViewHolder> implements EventListener<QuerySnapshot> {
    private final Context context;
    private final Query mQuery;
    private ArrayList<RatingsSubListModel> ratingsSubListModel = new ArrayList<>();

    private static OnRatingClickListener orcl;

    public RatingsSubListAdapter(Context context, Query query) {
        this.context = context;
        this.mQuery = query;
        mQuery.addSnapshotListener(this);
    }

    @NonNull
    @Override
    public RatingsSubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ratings_list, parent, false);
        RatingsSubListViewHolder viewHolder = new RatingsSubListViewHolder(context, view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RatingsSubListViewHolder holder, int position) {
        holder.onBind(ratingsSubListModel.get(position));
    }

    @Override
    public int getItemCount() {
        return ratingsSubListModel.size();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e != null){
            Log.w("list 에러","onEvent:error", e);
        }
        int i =0;
        ratingsSubListModel.clear();
        for(DocumentSnapshot doc : documentSnapshots.getDocuments()){
            ratingsSubListModel.add(doc.toObject(RatingsSubListModel.class));
            notifyDataSetChanged();
        }
    }

    public void setOrcl(OnRatingClickListener orcl) {
        RatingsSubListAdapter.orcl = orcl;
    }
    public interface OnRatingClickListener{
        void onClick(RatingsSubListModel data);
    }

    public static class RatingsSubListViewHolder extends RecyclerView.ViewHolder {
        Context context;
        private final TextView title;
        private final TextView sub_title;
        private final ConstraintLayout layout;

        public RatingsSubListViewHolder(Context _context, View itemView) {
            super(itemView);
            ItemRatingsListBinding binding = ItemRatingsListBinding.bind(itemView);

            context = _context;
            title = binding.titleTv;
            sub_title = binding.subTitleTv;
            layout = binding.layout;
        }
        public void onBind(RatingsSubListModel data){
            title.setText(data.getTitle());
            sub_title.setText(data.getContent());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orcl.onClick(data);
                }
            });
        }
    }
}