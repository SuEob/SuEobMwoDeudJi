package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.custom_view.CommunityCommentCommentViewHolder;
import com.example.sueobmwodeudji.custom_view.CommunitySearchViewHolder;
import com.example.sueobmwodeudji.model.CommunityCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySearchModel;

import java.util.List;

public class CommunitySearchAdapter extends RecyclerView.Adapter<CommunitySearchViewHolder> {

    private Context context;
    private List<CommunitySearchModel> searchModels;

    public CommunitySearchAdapter(Context context, List<CommunitySearchModel> searchModels) {
        this.context = context;
        this.searchModels = searchModels;
    }

    @NonNull
    @Override
    public CommunitySearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_search_list, parent, false);

        return new CommunitySearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySearchViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }
}
