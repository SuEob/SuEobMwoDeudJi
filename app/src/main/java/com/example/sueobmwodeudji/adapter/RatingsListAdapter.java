package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.custom_view.RatingsListViewHolder;
import com.example.sueobmwodeudji.model.RatingsListModel;

import java.util.List;

public class RatingsListAdapter extends RecyclerView.Adapter<RatingsListViewHolder> {
    private Context context;
    private List<RatingsListModel> ratingsListModel;

    public RatingsListAdapter(Context context, List<RatingsListModel> ratingsListModel) {
        this.context = context;
        this.ratingsListModel = ratingsListModel;
    }

    @NonNull
    @Override
    public RatingsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ratings_list, parent, false);
        RatingsListViewHolder viewHolder = new RatingsListViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RatingsListViewHolder holder, int position) {
        holder.onBind(ratingsListModel.get(position));
    }

    @Override
    public int getItemCount() {
        return ratingsListModel.size();
    }


}