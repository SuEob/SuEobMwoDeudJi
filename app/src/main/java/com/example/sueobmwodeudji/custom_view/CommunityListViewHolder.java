package com.example.sueobmwodeudji.custom_view;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemCommunityListBinding;
import com.example.sueobmwodeudji.databinding.ItemRatingsListBinding;
import com.example.sueobmwodeudji.model.CommunityListModel;
import com.example.sueobmwodeudji.model.RatingsListModel;

public class CommunityListViewHolder extends RecyclerView.ViewHolder {
    public TextView title;

    public CommunityListViewHolder(View itemView) {
        super(itemView);
        ItemCommunityListBinding binding = ItemCommunityListBinding.bind(itemView);

        title = binding.titleTv;
    }
    public void onBind(CommunityListModel data){
        title.setText(data.getTitle());
    }
}