package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.model.CommunitySubSearchModel;

import java.util.List;

public class CommunitySubSearchAdapter extends RecyclerView.Adapter<CommunitySubSearchAdapter.CommunitySubSearchViewHolder> {

    private final Context context;
    private final List<CommunitySubSearchModel> searchModels;

    public CommunitySubSearchAdapter(Context context, List<CommunitySubSearchModel> searchModels) {
        this.context = context;
        this.searchModels = searchModels;
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
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    public static class CommunitySubSearchViewHolder extends RecyclerView.ViewHolder {
        public CommunitySubSearchViewHolder(Context _context, @NonNull View itemView) {
            super(itemView);
        }
    }
}
