package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ActivityTimeTableSubListBinding;
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

        ActivityTimeTableSubListBinding listBinding = ActivityTimeTableSubListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);


        binding.timeTableListBtn.setOnClickListener(v -> {
            int position = viewHolder.getAbsoluteAdapterPosition();
            Log.d("position", String.valueOf(position));

            if (position != RecyclerView.NO_POSITION) {
                timeTableSubFrameModel.remove(position);
            }

        });

//        아마 제거
//        binding.timeTableListBtn.setOnClickListener(v -> {
//            int position = viewHolder.getAdapterPosition();
//
//            if (position != RecyclerView.NO_POSITION) {
//                Intent intent = new Intent(parent.getContext(), TimeTableThridActivity.class);
//                parent.getContext().startActivity(intent);
//            }
//        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableSubListViewHolder holder, int position) {
        holder.listTitle.setText(timeTableSubFrameModel.get(position).listTitle);
    }

    @Override
    public int getItemCount() {
        return timeTableSubFrameModel.size();
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
