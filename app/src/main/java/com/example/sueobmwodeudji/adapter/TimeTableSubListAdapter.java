package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemTimeTableListBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;

import java.util.List;

public class TimeTableSubListAdapter extends RecyclerView.Adapter<TimeTableSubListAdapter.TimeTableSubListViewHolder>{
    private Context context;
    private List<TimeTableSubFrameModel> list;

    public TimeTableSubListAdapter(Context context, List<TimeTableSubFrameModel> list) {
        this.context = context;
        this.list = list;
    }

    public void addItem(String name) {
        list.add(new TimeTableSubFrameModel(name));
        notifyItemInserted(0);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimeTableSubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimeTableListBinding binding = ItemTimeTableListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TimeTableSubListViewHolder viewHolder = new TimeTableSubListViewHolder(binding);
        binding.timeTableListBtn.setOnClickListener(v -> {
            int position = viewHolder.getAbsoluteAdapterPosition();
            Log.d("position", String.valueOf(position));

            if (position != RecyclerView.NO_POSITION) {
                removeItem(position);
//                addItem("asd");
            }

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableSubListViewHolder holder, int position) {
        holder.listTitle.setText(list.get(position).listTitle);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TimeTableSubListViewHolder extends RecyclerView.ViewHolder {
        private ItemTimeTableListBinding binding;
        public TextView listTitle;
        public ImageButton listBtn;

        public TimeTableSubListViewHolder(@NonNull ItemTimeTableListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            listTitle = binding.timeTableListTitle;
            listBtn = binding.timeTableListBtn;
        }
    }
}
