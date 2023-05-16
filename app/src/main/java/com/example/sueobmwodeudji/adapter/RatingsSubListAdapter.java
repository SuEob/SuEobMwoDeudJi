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

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.RatingsSubPostActivity;
import com.example.sueobmwodeudji.databinding.ItemRatingsListBinding;
import com.example.sueobmwodeudji.model.RatingsSubListModel;

import java.util.List;

public class RatingsSubListAdapter extends RecyclerView.Adapter<RatingsSubListAdapter.RatingsSubListViewHolder> {
    private final Context context;
    private final List<RatingsSubListModel> ratingsSubListModel;

    public RatingsSubListAdapter(Context context, List<RatingsSubListModel> ratingsSubListModel) {
        this.context = context;
        this.ratingsSubListModel = ratingsSubListModel;
    }

    @NonNull
    @Override
    public RatingsSubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ratings_list, parent, false);
        RatingsSubListViewHolder viewHolder = new RatingsSubListViewHolder(context, view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RatingsSubListViewHolder holder, int position) {
        holder.onBind(ratingsSubListModel.get(position));
    }

    @Override
    public int getItemCount() {
        return ratingsSubListModel.size();
    }

    public static class RatingsSubListViewHolder extends RecyclerView.ViewHolder {
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

                    intent.putExtra("title", data.getTitle());
                    intent.putExtra("sub_title", data.getSubTitle());

                    context.startActivity(intent);
                }
            });
        }
    }
}