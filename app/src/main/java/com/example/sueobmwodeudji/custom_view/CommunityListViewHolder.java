package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.CommunityPostActivity;
import com.example.sueobmwodeudji.databinding.ItemCommunityListBinding;
import com.example.sueobmwodeudji.databinding.ItemRatingsListBinding;
import com.example.sueobmwodeudji.model.CommunityListModel;
import com.example.sueobmwodeudji.model.RatingsListModel;

public class CommunityListViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private final TextView title;
    private final ConstraintLayout layout;

    public CommunityListViewHolder(Context _context, View itemView) {
        super(itemView);
        ItemCommunityListBinding binding = ItemCommunityListBinding.bind(itemView);
        context = _context;
        title = binding.titleTv;
        layout = binding.layout;
    }
    public void onBind(CommunityListModel data){
        title.setText(data.getTitle());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommunityPostActivity.class);
                context.startActivity(intent);
            }
        });
    }
}