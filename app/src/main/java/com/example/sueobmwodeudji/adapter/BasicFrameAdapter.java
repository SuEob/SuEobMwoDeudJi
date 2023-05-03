package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemBasicFrameBinding;

import java.util.ArrayList;

public class BasicFrameAdapter extends RecyclerView.Adapter<BasicFrameAdapter.BasicFrameViewHolder> {
    private Context context;
    private ArrayList<BasicFrame> basicFrame = new ArrayList<>();

    public BasicFrameAdapter(Context context, ArrayList<BasicFrame> basicFrame) {
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
        holder.title.setText(basicFrame.get(position).title);
        // holder.bind(basicFrame.get(position));
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

        public void bind(@NonNull BasicFrame basicFrame) {
            binding.basicFrameTitle.setText(basicFrame.title);
            // binding.basicFrameContent.setBackgroundColor(basicFrame.frameLayout.getB);
        }
    }

}
