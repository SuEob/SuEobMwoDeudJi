package com.example.sueobmwodeudji.custom_view;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.adapter.CommunityCommentCommentAdapter;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentBinding;
import com.example.sueobmwodeudji.model.CommunityCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunityCommentModel;

import java.util.LinkedList;

public class CommunityCommentViewHolder extends RecyclerView.ViewHolder{
    private Context context;
    private ItemCommunityCommentBinding binding;
    public CommunityCommentViewHolder(Context _context, @NonNull View itemView) {
        super(itemView);
        context = _context;
        binding = ItemCommunityCommentBinding.bind(itemView);
    }

    public void onBind(CommunityCommentModel data){
        LinkedList<CommunityCommentCommentModel> list = new LinkedList<>();
        list.add(new CommunityCommentCommentModel("a"));
        list.add(new CommunityCommentCommentModel("a"));
        list.add(new CommunityCommentCommentModel("a"));
        CommunityCommentCommentAdapter adapter = new CommunityCommentCommentAdapter(context, list);
        binding.recyclerView.setAdapter(adapter);
    }
}
