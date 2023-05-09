package com.example.sueobmwodeudji.custom_view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.databinding.ItemRatingsSub1Binding;
import com.example.sueobmwodeudji.dto.RatingMyClassData;

import java.util.ArrayList;

public class ViewHolderPage extends RecyclerView.ViewHolder {
    private ItemRatingsSub1Binding binding;

    public ViewHolderPage(View itemView) {
        super(itemView);
        binding = ItemRatingsSub1Binding.bind(itemView);
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
            constraintLayouts[i].setVisibility(View.VISIBLE);
            class_name[i].setText(list.get(i).getClass_name());
            teacher_name[i].setText(list.get(i).getTeacher_name());
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
