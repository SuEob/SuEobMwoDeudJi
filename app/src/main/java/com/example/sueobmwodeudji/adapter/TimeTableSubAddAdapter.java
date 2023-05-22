package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemTimeTableAddBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;

import java.util.List;

public class TimeTableSubAddAdapter extends RecyclerView.Adapter<TimeTableSubAddAdapter.TimeTableSubAddViewHolder>{
    private Context context;
    private List<TimeTableSubFrameModel> timeTableSubFrameModel;

    public TimeTableSubAddAdapter(Context context, List<TimeTableSubFrameModel> timeTableSubFrameModel) {
        this.context = context;
        this.timeTableSubFrameModel = timeTableSubFrameModel;
    }

    @NonNull
    @Override
    public TimeTableSubAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimeTableAddBinding binding = ItemTimeTableAddBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TimeTableSubAddViewHolder viewHolder = new TimeTableSubAddViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableSubAddViewHolder holder, int position) {
        holder.addTitle.setText(timeTableSubFrameModel.get(position).addTitle);
        holder.addTeacher.setText(timeTableSubFrameModel.get(position).teacher);
        holder.addContent.setText(timeTableSubFrameModel.get(position).content);
    }

    @Override
    public int getItemCount() {
        return timeTableSubFrameModel.size();
    }

    public static class TimeTableSubAddViewHolder extends RecyclerView.ViewHolder {
        private ItemTimeTableAddBinding binding;
        public TextView addTitle;
        public TextView addTeacher;
        public TextView addContent;
        public ImageButton addBtn;

        public TimeTableSubAddViewHolder(@NonNull ItemTimeTableAddBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            addTitle = binding.timeTableAddTitle;
            addTeacher = binding.timeTableAddTeacher;
            addContent = binding.timeTableAddContent;
            addBtn = binding.timeTableAddBtn;
        }
    }
}
