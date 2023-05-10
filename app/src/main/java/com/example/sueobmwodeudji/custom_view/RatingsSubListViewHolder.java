package com.example.sueobmwodeudji.custom_view;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemRatingsListBinding;
import com.example.sueobmwodeudji.model.RatingsSubListModel;

public class RatingsSubListViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView sub_title;

    public RatingsSubListViewHolder(View itemView) {
        super(itemView);
        ItemRatingsListBinding binding = ItemRatingsListBinding.bind(itemView);

        title = binding.titleTv;
        sub_title = binding.subTitleTv;
    }
    public void onBind(RatingsSubListModel data){
        title.setText(data.getTitle());
        sub_title.setText(data.getSubTitle());
    }
}