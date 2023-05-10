package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.adapter.CommunitySubCommentCommentAdapter;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;

import java.util.LinkedList;

public class CommunitySubCommentViewHolder extends RecyclerView.ViewHolder{
    private final Context context;
    private final ItemCommunityCommentBinding binding;
    public CommunitySubCommentViewHolder(Context _context, @NonNull View itemView) {
        super(itemView);
        context = _context;
        binding = ItemCommunityCommentBinding.bind(itemView);
    }

    public void onBind(CommunitySubCommentModel data){
        LinkedList<CommunitySubCommentCommentModel> list = new LinkedList<>();
        list.add(new CommunitySubCommentCommentModel("a"));
        list.add(new CommunitySubCommentCommentModel("a"));
        list.add(new CommunitySubCommentCommentModel("a"));
        CommunitySubCommentCommentAdapter adapter = new CommunitySubCommentCommentAdapter(context, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
