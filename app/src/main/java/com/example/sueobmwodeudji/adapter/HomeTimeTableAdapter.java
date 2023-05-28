package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemHomeTimeTableBinding;
import com.example.sueobmwodeudji.dto.HomeTimaTableData;

import java.util.List;

public class HomeTimeTableAdapter extends RecyclerView.Adapter<HomeTimeTableAdapter.HomeTimeTableViewHolder> {
    private Context context;
    private List<HomeTimaTableData> list;
    private List<Integer> imgList;

    public HomeTimeTableAdapter(Context context, List<HomeTimaTableData> list) {
        this.context = context;
        this.list = list;
    }

//    public HomeTimeTableAdapter(Context context, List<Integer> imgList) {
//        this.context = context;
//        this.imgList = imgList;
//    }

    @NonNull
    @Override
    public HomeTimeTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeTimeTableBinding binding = ItemHomeTimeTableBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        HomeTimeTableViewHolder viewHolder = new HomeTimeTableViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeTimeTableViewHolder holder, int position) {
        holder.classContent.setText(list.get(position).getClass_content());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HomeTimeTableViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeTimeTableBinding binding;
        public TextView classContent;

        public HomeTimeTableViewHolder(ItemHomeTimeTableBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            classContent = binding.homeTimeTableText;
        }

    }

}
