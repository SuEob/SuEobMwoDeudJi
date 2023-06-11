package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemRatingsRecentListBinding;
import com.example.sueobmwodeudji.dto.RatingsSubListModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RatingsSubRecentListAdapter  extends RecyclerView.Adapter<RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder> implements EventListener<QuerySnapshot> {
    protected final Context context;
    private ArrayList<Query> mQuerys;
    private final ArrayList<RatingsSubListModel> ratingsSubRecentListModels = new ArrayList<>();
    private static OnRecentItemClickLintener oricl;
    public void setOricl(OnRecentItemClickLintener onListener) {
        oricl = onListener;
    }
    public interface OnRecentItemClickLintener{
        void onClick(RatingsSubListModel data);
    }

    public RatingsSubRecentListAdapter(Context context, ArrayList<Query> querys) {
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
    public RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ratings_recent_list, parent, false);

        return new RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder holder, int position) {
        holder.onBind(ratingsSubRecentListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return ratingsSubRecentListModels.size();
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
        ratingsSubRecentListModels.add(change.getDocument().toObject(RatingsSubListModel.class));
        //Collections.reverse(ratingsSubRecentListModels);
        notifyDataSetChanged();
    }

    public static class RatingsSubRecentListViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private TextView class_name, teacher_name, content;
        private ConstraintLayout layout;
        private ImageView honeyIV;

        public RatingsSubRecentListViewHolder(Context _context, View itemView) {
            super(itemView);
            ItemRatingsRecentListBinding binding = ItemRatingsRecentListBinding.bind(itemView);
            context = _context;
            class_name = binding.titleTv;
            content = binding.contentTv;
            layout = binding.layout;
            honeyIV = binding.honeyIv;
        }

        public void onBind(RatingsSubListModel data){
            if(context.getApplicationContext() == null) return;
            class_name.setText(data.getTitle());
            //teacher_name.setText(data.getTeacherName());
            content.setText(data.getContent());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    oricl.onClick(data);
                }
            });

            if (data.getHoney())
                honeyIV.setVisibility(View.VISIBLE);
        }
    }
}