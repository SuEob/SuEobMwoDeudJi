package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ActivityCommunitySubSearchBinding;

public class CommunitySubSearchViewHolder extends RecyclerView.ViewHolder {
    private final Context context;
    private final ActivityCommunitySubSearchBinding binding;
    public CommunitySubSearchViewHolder(Context _context, @NonNull View itemView) {
        super(itemView);

        context = _context;
        binding = ActivityCommunitySubSearchBinding.bind(itemView);
    }
}
