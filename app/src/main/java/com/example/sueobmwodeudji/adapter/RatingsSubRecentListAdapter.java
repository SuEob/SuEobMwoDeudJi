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
import com.example.sueobmwodeudji.databinding.ItemRatingsRecentListBinding;
import com.example.sueobmwodeudji.model.RatingsSubRecentListModel;

import java.util.List;

public class RatingsSubRecentListAdapter  extends RecyclerView.Adapter<RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder> {
    protected final Context context;
    private final List<RatingsSubRecentListModel> ratingsSubRecentListModels;

    public RatingsSubRecentListAdapter(Context context, List<RatingsSubRecentListModel> ratingsSubRecentListModels) {
        this.context = context;
        this.ratingsSubRecentListModels = ratingsSubRecentListModels;
    }

    @NonNull
    @Override
    public RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_ratings_recent_list, parent, false);

        return new RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingsSubRecentListAdapter.RatingsSubRecentListViewHolder holder, int position) {
        holder.onBind(ratingsSubRecentListModels.get(position));
    }

    @Override
    public int getItemCount() {
        return ratingsSubRecentListModels.size();
    }

    public static class RatingsSubRecentListViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private TextView class_name, teacher_name, content;
        private ConstraintLayout layout;

        public RatingsSubRecentListViewHolder(Context _context, View itemView) {
            super(itemView);
            ItemRatingsRecentListBinding binding = ItemRatingsRecentListBinding.bind(itemView);
            context = _context;
            class_name = binding.titleTv;
            teacher_name = binding.teacherTv;
            content = binding.contentTv;
            layout = binding.layout;
        }

        public void onBind(RatingsSubRecentListModel data){
            class_name.setText(data.getClassName());
            teacher_name.setText(data.getTeacherName());
            content.setText(data.getContent());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RatingsSubPostActivity.class);

                    intent.putExtra("class_name", data.getClassName());
                    intent.putExtra("teacher_name", data.getTeacherName());

                    context.startActivity(intent);
                }
            });
        }
    }
}