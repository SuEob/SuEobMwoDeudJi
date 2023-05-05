package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
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

        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup parent = (ViewGroup) holder.frameLayout.getParent();
        View subView = inflater.inflate(R.layout.item_fragment_home_sub_2, parent, false);
        holder.frameLayout.removeAllViews();
        holder.frameLayout.addView(subView);
        holder.setFragment(context, basicFrameModel.get(position).fragment);
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

        public void setFragment(Context context, Fragment fragment) {
            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

}
