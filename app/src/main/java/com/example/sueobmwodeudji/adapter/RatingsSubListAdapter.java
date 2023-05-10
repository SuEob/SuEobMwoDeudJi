package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.custom_view.RatingsSubListViewHolder;
import com.example.sueobmwodeudji.model.RatingsSubListModel;

import java.util.List;

public class RatingsSubListAdapter extends RecyclerView.Adapter<RatingsSubListViewHolder> {
    private final Context context;
    private final List<RatingsSubListModel> ratingsSubListModel;

    public RatingsSubListAdapter(Context context, List<RatingsSubListModel> ratingsSubListModel) {
        this.context = context;
        this.ratingsSubListModel = ratingsSubListModel;
    }

    @NonNull
    @Override
    public RatingsSubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ratings_list, parent, false);
        RatingsSubListViewHolder viewHolder = new RatingsSubListViewHolder(view);

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


}