package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.RatingsSubListActivity;
import com.example.sueobmwodeudji.RatingsSubFormActivity;
import com.example.sueobmwodeudji.databinding.ItemRatingsMyClassListBinding;
import com.example.sueobmwodeudji.dto.RatingMyClassData;

import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolderPage> {
    private final ArrayList<ArrayList<String>> listData;

    public ViewPagerAdapter(ArrayList<ArrayList<String>> data){
        this.listData = data;
    }

    @NonNull
    @Override
    public ViewHolderPage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_ratings_my_class_list, parent, false);
        return new ViewHolderPage(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPage holder, int position) {
        holder.onBind(listData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class ViewHolderPage extends RecyclerView.ViewHolder {
        private final ItemRatingsMyClassListBinding binding;
        private final Context context;

        public ViewHolderPage(Context _context, View itemView) {
            super(itemView);
            this.context = _context;
            binding = ItemRatingsMyClassListBinding.bind(itemView);
        }

        public void onBind(ArrayList<String> list, int position) {
            if(context.getApplicationContext() == null) return;

            ConstraintLayout[] constraintLayouts = {binding.constraintLayout1, binding.constraintLayout2, binding.constraintLayout3};
            TextView[] class_name = {binding.className1, binding.className2, binding.className3};
            Button[] btn = {binding.btn1,binding.btn2,binding.btn3};
            Log.d("a", position + "");

            for (int i = 0; i < 3; i++) {
                if (list.size() <= i) break;

                String class_str = list.get(i);

                constraintLayouts[i].setVisibility(View.VISIBLE);
                class_name[i].setText(class_str);
                class_name[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RatingsSubListActivity.class);
                        intent.putExtra("class_name", class_str);

                        context.startActivity(intent);
                    }
                });

                btn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RatingsSubFormActivity.class);
                        intent.putExtra("class_name", class_str);

                        context.startActivity(intent);
                    }
                });
            }
        }
    }
}
