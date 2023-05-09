package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ActivityTimeTableSecondBinding;
import com.example.sueobmwodeudji.databinding.ItemTimeTableListBinding;
import com.example.sueobmwodeudji.model.SmallFrameModel;

import java.util.List;

public class SmallFrameAdapter extends RecyclerView.Adapter<SmallFrameAdapter.SmallFrameViewHolder> {
    private Context context;
    private List<SmallFrameModel> smallFrameModel;

    public SmallFrameAdapter(Context context, List<SmallFrameModel> smallFrameModel) {
        this.context = context;
        this.smallFrameModel = smallFrameModel;
    }

    @NonNull
    @Override
    public SmallFrameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_time_table_list, parent, false);
        SmallFrameViewHolder viewHolder = new SmallFrameViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SmallFrameViewHolder holder, int position) {
        holder.btn.setText(smallFrameModel.get(position).asdasd);
    }

    @Override
    public int getItemCount() {
        return smallFrameModel.size();
    }

    public static class SmallFrameViewHolder extends RecyclerView.ViewHolder {
        private ItemTimeTableListBinding binding;
        Button btn;

        public SmallFrameViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemTimeTableListBinding.bind(itemView);

            btn = binding.tableName;
        }
    }
}
