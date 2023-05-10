package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.custom_view.CommunitySubListViewHolder;
import com.example.sueobmwodeudji.model.CommunitySubListModel;

import java.util.List;

public class CommunitySubListAdapter extends RecyclerView.Adapter<CommunitySubListViewHolder> {
    private final Context context;
    private final List<CommunitySubListModel> communitySubListModels;

    public CommunitySubListAdapter(Context context, List<CommunitySubListModel> communitySubListModels) {
        this.context = context;
        this.communitySubListModels = communitySubListModels;
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

}
