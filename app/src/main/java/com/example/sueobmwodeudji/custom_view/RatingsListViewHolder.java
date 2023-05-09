package com.example.sueobmwodeudji.custom_view;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemRatingsListBinding;
import com.example.sueobmwodeudji.model.RatingsListModel;

public class RatingsListViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView sub_title;

    public RatingsListViewHolder(View itemView) {
        super(itemView);
        ItemRatingsListBinding binding = ItemRatingsListBinding.bind(itemView);

        title = binding.titleTv;
        sub_title = binding.subTitleTv;
    }
    public void onBind(RatingsListModel data){
        title.setText(data.getTitle());
        sub_title.setText(data.getSubTitle());
    }
}