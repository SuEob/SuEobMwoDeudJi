package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityRecentListBinding;
import com.example.sueobmwodeudji.model.CommunitySubRecentListModel;

import java.util.List;

public class CommunitySubRecentListAdapter extends RecyclerView.Adapter<CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder> {
    protected final Context context;
    private final List<CommunitySubRecentListModel> communitySubRecentListModels;

    public CommunitySubRecentListAdapter(Context context, List<CommunitySubRecentListModel> communitySubListModels) {
        this.context = context;
        this.communitySubRecentListModels = communitySubListModels;
    }

    @NonNull
    @Override
    public CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_recent_list, parent, false);

        return new CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubRecentListAdapter.CommunitySubRecentListViewHolder holder, int position) {
        holder.onBind(communitySubRecentListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return communitySubRecentListModels.size();
    }

    public static class CommunitySubRecentListViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private TextView title, nickname, content;
        private ConstraintLayout layout;

        public CommunitySubRecentListViewHolder(Context _context, View itemView) {
            super(itemView);
            ItemCommunityRecentListBinding binding = ItemCommunityRecentListBinding.bind(itemView);
            context = _context;
            title = binding.titleTv;
            nickname = binding.nicknameTv;
            content = binding.contentTv;
            layout = binding.layout;
        }

        public void onBind(CommunitySubRecentListModel data){
            title.setText(data.getTitle());
            nickname.setText(data.getNickname());
            content.setText(data.getContent());
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
}