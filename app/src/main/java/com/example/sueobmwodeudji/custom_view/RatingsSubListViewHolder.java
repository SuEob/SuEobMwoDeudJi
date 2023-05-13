package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.RatingsSubPostActivity;
import com.example.sueobmwodeudji.databinding.ItemRatingsListBinding;
import com.example.sueobmwodeudji.model.RatingsSubListModel;

public class RatingsSubListViewHolder extends RecyclerView.ViewHolder {
    Context context;
    private final TextView title;
    private final TextView sub_title;
    private final ConstraintLayout layout;

    public RatingsSubListViewHolder(Context _context, View itemView) {
        super(itemView);
        ItemRatingsListBinding binding = ItemRatingsListBinding.bind(itemView);

        context = _context;
        title = binding.titleTv;
        sub_title = binding.subTitleTv;
        layout = binding.layout;
    }
    public void onBind(RatingsSubListModel data){
        title.setText(data.getTitle());
        sub_title.setText(data.getSubTitle());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RatingsSubPostActivity.class);
                context.startActivity(intent);
            }
        });
    }
}