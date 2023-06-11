package com.example.sueobmwodeudji.adapter;

import android.content.Context;
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
import com.example.sueobmwodeudji.databinding.ItemCommunitySearchListBinding;
import com.example.sueobmwodeudji.dto.CommunitySubListModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CommunitySubSearchAdapter extends RecyclerView.Adapter<CommunitySubSearchAdapter.CommunitySubSearchViewHolder> implements EventListener<QuerySnapshot> {

    private final Context context;
    private ArrayList<DocumentSnapshot> mSnapshots  = new ArrayList<>();
    private final ArrayList<Query> mQuery;
    private static CommunitySubListAdapter.OnPostClickListener opcl;

    public CommunitySubSearchAdapter(Context context, ArrayList<Query> query) {
        this.context = context;
        this.mQuery = query;

        addQuerySnapshotListener();
    }

    @NonNull
    @Override
    public CommunitySubSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_search_list, parent, false);

        return new CommunitySubSearchViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubSearchViewHolder holder, int position) {
        holder.onBind(mSnapshots.get(position).toObject(CommunitySubListModel.class));
    }

    @Override
    public int getItemCount() {
        return mSnapshots.size();
    }

    private void addQuerySnapshotListener() {
        for (Query query :mQuery) {
            query.addSnapshotListener(this);
        }
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e != null) {
            Log.w("list 에러","onEvent:error", e);
        }
        for(DocumentSnapshot doc : documentSnapshots.getDocuments()) {
            mSnapshots.add(doc);
            notifyDataSetChanged();
        }
    }

    public void setOpcl(CommunitySubListAdapter.OnPostClickListener onListener) {
        opcl = onListener;
    }
    public interface OnPostClickListener{
        void onClick(CommunitySubListModel data);
    }

    public static class CommunitySubSearchViewHolder extends RecyclerView.ViewHolder {
        private final TextView category, title;
        private final ConstraintLayout layout;
        public CommunitySubSearchViewHolder(Context _context, @NonNull View itemView) {
            super(itemView);
            ItemCommunitySearchListBinding binding = ItemCommunitySearchListBinding.bind(itemView);
            category = binding.categoryTv;
            title = binding.titleTv;
            layout = binding.layout;
        }

        public void onBind(CommunitySubListModel data){
            category.setText(data.getCategory());
            title.setText(data.getTitle());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opcl.onClick(data);
                }
            });
        }
    }
}