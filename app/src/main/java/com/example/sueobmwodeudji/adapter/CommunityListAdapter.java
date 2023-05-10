package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.custom_view.CommunityListViewHolder;
import com.example.sueobmwodeudji.custom_view.RatingsListViewHolder;
import com.example.sueobmwodeudji.model.CommunityListModel;
import com.example.sueobmwodeudji.model.RatingsListModel;

import java.util.List;

public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListViewHolder> {
    private Context context;
    private List<CommunityListModel> communityListModels;

    public CommunityListAdapter(Context context, List<CommunityListModel> communityListModels) {
        this.context = context;
        this.communityListModels = communityListModels;
    }

    @NonNull
    @Override
    public CommunityListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_list, parent, false);

        return new CommunityListViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityListViewHolder holder, int position) {
        holder.onBind(communityListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return communityListModels.size();
    }

}
