package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunitySearchListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.CommunitySubSearchModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CommunitySubSearchAdapter extends RecyclerView.Adapter<CommunitySubSearchAdapter.CommunitySubSearchViewHolder> implements EventListener<QuerySnapshot> {

    private final Context context;
    private final ArrayList<CommunitySubListModel> searchModels = new ArrayList<>();
    private final ArrayList<Query> mQuery;

/*    public CommunitySubSearchAdapter(Context context, List<CommunitySubSearchModel> searchModels) {
        this.context = context;
        this.searchModels = searchModels;
    }*/

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
        holder.category.setText(searchModels.get(position).getCategory());
        holder.title.setText(searchModels.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    private void addQuerySnapshotListener() {
        for (Query query :mQuery) {
            query.addSnapshotListener(this);
        }
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
        if(e != null) {
            Log.w("list 에러","onEvent:error", e);
        }
        for(DocumentSnapshot doc : value.getDocuments()) {
            //model.add(change.getDocument().toObject(CommunitySubListModel.class));
            searchModels.add(doc.toObject(CommunitySubListModel.class));
            Log.d("fsdafasd", searchModels.get(0).getName());
            notifyDataSetChanged();
        }
    }



    public static class CommunitySubSearchViewHolder extends RecyclerView.ViewHolder {
        TextView category, title;

        public CommunitySubSearchViewHolder(Context _context, @NonNull View itemView) {
            super(itemView);
            ItemCommunitySearchListBinding binding = ItemCommunitySearchListBinding.bind(itemView);
            category = binding.categoryTv;
            title = binding.titleTv;
        }
    }
}