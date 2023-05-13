package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.databinding.ItemCommunityListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;

public class CommunitySubListViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private final TextView title;
    private final ConstraintLayout layout;

    public CommunitySubListViewHolder(Context _context, View itemView) {
        super(itemView);
        ItemCommunityListBinding binding = ItemCommunityListBinding.bind(itemView);
        context = _context;
        title = binding.titleTv;
        layout = binding.layout;
    }
    public void onBind(CommunitySubListModel data){
        title.setText(data.getTitle());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommunitySubPostActivity.class);
                intent.putExtra("subject", data.getTitle());
                context.startActivity(intent);
            }
        });
    }
}