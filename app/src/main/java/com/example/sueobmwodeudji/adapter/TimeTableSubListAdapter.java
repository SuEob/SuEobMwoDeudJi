package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemTimeTableListBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;

import java.util.List;

public class TimeTableSubListAdapter extends RecyclerView.Adapter<TimeTableSubListAdapter.TimeTableSubListViewHolder>{
    private Context context;
    private List<TimeTableSubFrameModel> timeTableSubFrameModel;

    public TimeTableSubListAdapter(Context context, List<TimeTableSubFrameModel> timeTableSubFrameModel) {
        this.context = context;
        this.timeTableSubFrameModel = timeTableSubFrameModel;
    }

    @NonNull
    @Override
    public TimeTableSubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimeTableListBinding binding = ItemTimeTableListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TimeTableSubListViewHolder viewHolder = new TimeTableSubListViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableSubListViewHolder holder, int position) {
        holder.listTitle.setText(timeTableSubFrameModel.get(position).title);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TimeTableSubListViewHolder extends RecyclerView.ViewHolder {
        private ItemTimeTableListBinding binding;
        public TextView listTitle;

        public TimeTableSubListViewHolder(@NonNull ItemTimeTableListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            listTitle = binding.timeTableListTitle;
        }
    }
}
