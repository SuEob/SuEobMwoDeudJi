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

import com.example.sueobmwodeudji.OnItemClick;
import com.example.sueobmwodeudji.databinding.ItemTimeTableAddBinding;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;

import java.util.List;

public class TimeTableSubAddAdapter extends RecyclerView.Adapter<TimeTableSubAddAdapter.TimeTableSubAddViewHolder> {
    private Context context;
    private List<TimeTableSubFrameModel> timeTableSubFrameModel;
    private OnItemClick mCallBack;
    private AlertDialog.Builder builder;

    public TimeTableSubAddAdapter(Context context, List<TimeTableSubFrameModel> timeTableSubFrameModel) {
        this.context = context;
        this.timeTableSubFrameModel = timeTableSubFrameModel;
    }

    public TimeTableSubAddAdapter(Context context, OnItemClick listener) {
        this.context = context;
        this.mCallBack = listener;
    }

    @NonNull
    @Override
    public TimeTableSubAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimeTableAddBinding binding = ItemTimeTableAddBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TimeTableSubAddViewHolder viewHolder = new TimeTableSubAddViewHolder(binding);

        // + 버튼을 누르면 dialog 띄우고 확인 누르면 시간표에 저장
        binding.timeTableAddBtn.setOnClickListener(v -> {
            int position = viewHolder.getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Log.d("POSITION", String.valueOf(position));
//                mCallBack.onClick("asd"); 여기서 오류뜸 
            } else {
                Log.d("NO_POSITION", String.valueOf(RecyclerView.NO_POSITION));
            }

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
