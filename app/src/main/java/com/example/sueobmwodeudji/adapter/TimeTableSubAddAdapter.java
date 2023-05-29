package com.example.sueobmwodeudji.adapter;

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

import java.util.List;

public class TimeTableSubAddAdapter extends RecyclerView.Adapter<TimeTableSubAddAdapter.TimeTableSubAddViewHolder> {
    private Context context;
    private List<TimeTableSubFrameModel> timeTableSubFrameModel;
    private CallBackListener listener;

    public TimeTableSubAddAdapter() {}

    public TimeTableSubAddAdapter(Context context, List<TimeTableSubFrameModel> timeTableSubFrameModel) {
        this.context = context;
        this.timeTableSubFrameModel = timeTableSubFrameModel;
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
//                Toast.makeText(parent.getContext(), ""+position, Toast.LENGTH_SHORT).show();
                setCallBackListener(listener);
                if (listener != null) {
                    String value = "Hello, World!";
                    listener.callBack(value);
                }
            } else {
                Log.d("NO_POSITION", String.valueOf(RecyclerView.NO_POSITION));
            }

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableSubAddViewHolder holder, int position) {
        holder.addTitle.setText(timeTableSubFrameModel.get(position).addTitle);
        holder.addDay.setText(timeTableSubFrameModel.get(position).day);
        holder.addPeriod.setText(timeTableSubFrameModel.get(position).period);
    }

    @Override
    public int getItemCount() {
        return timeTableSubFrameModel.size();
    }

    public static class TimeTableSubAddViewHolder extends RecyclerView.ViewHolder{
        private ItemTimeTableAddBinding binding;
        private CallBackListener listener;

        public TextView addTitle;
        public TextView addPeriod;
        public TextView addDay;
        public ImageButton addBtn;

        public TimeTableSubAddViewHolder(@NonNull ItemTimeTableAddBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            addTitle = binding.timeTableAddTitle;
            addPeriod = binding.timeTableAddPeriod;
            addDay = binding.timeTableAddDay;
            addBtn = binding.timeTableAddBtn;
        }

    }

    public interface CallBackListener {
        void callBack(String data);
    }

    public void setCallBackListener(CallBackListener listener) {
        this.listener = listener;
    }

}
