package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.RatingsActivity;
import com.example.sueobmwodeudji.databinding.ItemRatingsMyClassListBinding;
import com.example.sueobmwodeudji.dto.RatingMyClassData;

import java.util.ArrayList;

public class ViewHolderPage extends RecyclerView.ViewHolder {
    private final ItemRatingsMyClassListBinding binding;
    private final Context context;

    public ViewHolderPage(Context _context, View itemView) {
        super(itemView);
        this.context = _context;
        binding = ItemRatingsMyClassListBinding.bind(itemView);
    }

    public void onBind(ArrayList<RatingMyClassData> list, int position) {
        ConstraintLayout[] constraintLayouts = {binding.constraintLayout1, binding.constraintLayout2, binding.constraintLayout3};
        TextView[] class_name = {binding.className1, binding.className2, binding.className3};
        TextView[] teacher_name = {binding.teacherName1, binding.teacherName2, binding.teacherName3};
        Button[] btn = {binding.btn1,binding.btn2,binding.btn3};
        Log.d("a", position + "");

        for (int i = 0; i < 3; i++) {
            if (list.size() <= i) break;

            String id = list.get(i).getClass_name();
            String class_str = list.get(i).getClass_name();
            String teacher_str = list.get(i).getTeacher_name();

            constraintLayouts[i].setVisibility(View.VISIBLE);
            class_name[i].setText(class_str);
            class_name[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, RatingsActivity.class);
                    intent.putExtra("class_name", class_str);
                    intent.putExtra("teacher_name", teacher_str);

                    context.startActivity(intent);
                }
            });
            teacher_name[i].setText(teacher_str);
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
