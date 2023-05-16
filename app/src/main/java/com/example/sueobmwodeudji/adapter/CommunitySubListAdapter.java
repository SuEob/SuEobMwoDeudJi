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
import com.example.sueobmwodeudji.databinding.ItemCommunityListBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;

import java.util.List;

public class CommunitySubListAdapter extends RecyclerView.Adapter<CommunitySubListAdapter.CommunitySubListViewHolder> {
    private final Context context;
    private final List<CommunitySubListModel> communitySubListModels;

    public CommunitySubListAdapter(Context context, List<CommunitySubListModel> communitySubListModels) {
        this.context = context;
        this.communitySubListModels = communitySubListModels;
    }

    @NonNull
    @Override
    public CommunitySubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_list, parent, false);

        return new CommunitySubListViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubListViewHolder holder, int position) {
        holder.onBind(communitySubListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return communitySubListModels.size();
    }

    public static class CommunitySubListViewHolder extends RecyclerView.ViewHolder {
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
}
