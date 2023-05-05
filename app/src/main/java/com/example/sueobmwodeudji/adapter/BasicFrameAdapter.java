package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemFragmentBinding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.List;

public class BasicFrameAdapter extends RecyclerView.Adapter<BasicFrameAdapter.BasicFrameViewHolder> {
    private Context context;
    private List<BasicFrameModel> basicFrameModel;

    public BasicFrameAdapter(Context context, List<BasicFrameModel> basicFrameModel) {
        this.context = context;
        this.basicFrameModel = basicFrameModel;
    }

    @NonNull
    @Override
    public BasicFrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFragmentBinding binding = ItemFragmentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        BasicFrameViewHolder viewHolder = new BasicFrameViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BasicFrameViewHolder holder, int position) {
        holder.title.setText(basicFrameModel.get(position).title);

        int layoutId = basicFrameModel.get(position).layoutId;
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(layoutId, holder.frameLayout, false);
        holder.frameLayout.removeAllViews();
        holder.frameLayout.addView(subView);
    }

    @Override
    public int getItemCount() {
        return basicFrameModel.size();
    }

    public static class BasicFrameViewHolder extends RecyclerView.ViewHolder {
        private ItemFragmentBinding binding;
        public TextView title;
        public FrameLayout frameLayout;

        public BasicFrameViewHolder(@NonNull ItemFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            title = binding.basicFrameTitle;
            frameLayout = binding.basicFrameContent;


        }

    }

}
