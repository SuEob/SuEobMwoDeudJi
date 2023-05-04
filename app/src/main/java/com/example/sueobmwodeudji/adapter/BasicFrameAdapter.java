package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemBasicFrameBinding;

import java.util.List;

public class BasicFrameAdapter extends RecyclerView.Adapter<BasicFrameAdapter.BasicFrameViewHolder> {
    private Context context;
    private List<BasicFrame> basicFrame;

    public BasicFrameAdapter(Context context, List<BasicFrame> basicFrame) {
        this.context = context;
        this.basicFrame = basicFrame;
    }

    @NonNull
    @Override
    public BasicFrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBasicFrameBinding binding = ItemBasicFrameBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        BasicFrameViewHolder viewHolder = new BasicFrameViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BasicFrameViewHolder holder, int position) {
//        holder.homeBind(basicFrame.get(position));
        holder.title.setText(basicFrame.get(position).title);
    }

    @Override
    public int getItemCount() {
        return basicFrame.size();
    }

    public static class BasicFrameViewHolder extends RecyclerView.ViewHolder {
        private ItemBasicFrameBinding binding;
        public TextView title;

        public BasicFrameViewHolder(@NonNull ItemBasicFrameBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            title = binding.basicFrameTitle;
        }

//        private void homeBind(@NonNull BasicFrame basicFrame) {
//            binding.basicFrameTitle.setText(basicFrame.title);
//        }
    }

}
