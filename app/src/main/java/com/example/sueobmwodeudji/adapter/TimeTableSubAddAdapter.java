package com.example.sueobmwodeudji.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemTimeTableAddBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;
import com.example.sueobmwodeudji.ui.TimeTableFragment;

import java.util.List;

public class TimeTableSubAddAdapter extends RecyclerView.Adapter<TimeTableSubAddAdapter.TimeTableSubAddViewHolder>{
    private Context context;
    private List<TimeTableSubFrameModel> timeTableSubFrameModel;
    public TimeTableFragment timeTableFragment;
    private AlertDialog.Builder builder;

    public TimeTableSubAddAdapter(Context context, List<TimeTableSubFrameModel> timeTableSubFrameModel) {
        this.context = context;
        this.timeTableSubFrameModel = timeTableSubFrameModel;
    }

    public TimeTableSubAddAdapter(Context context, TimeTableFragment timeTableFragment) {
        this.context = context;
        this.timeTableFragment = timeTableFragment;
    }

    @NonNull
    @Override
    public TimeTableSubAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimeTableAddBinding binding = ItemTimeTableAddBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TimeTableSubAddViewHolder viewHolder = new TimeTableSubAddViewHolder(binding);

//        builder = new AlertDialog.Builder(parent.getContext());

        // + 버튼을 누르면 dialog 띄우고 확인 누르면 시간표에 저장
        binding.timeTableAddBtn.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Log.d("POSITION", String.valueOf(position));
                timeTableFragment.onChangeTimeTable("asdasd");
            } else {
                Log.d("NO_POSITION", String.valueOf(RecyclerView.NO_POSITION));
            }

//            builder.setTitle("Title")
//                    .setMessage("Hello, This is message")
//                    .create()
//                    .show();

        });

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
