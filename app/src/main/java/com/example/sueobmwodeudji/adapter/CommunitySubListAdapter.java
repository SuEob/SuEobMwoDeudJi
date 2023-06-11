package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityListBinding;
import com.example.sueobmwodeudji.dto.CommunitySubCommentModel;
import com.example.sueobmwodeudji.dto.CommunitySubListModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class CommunitySubListAdapter extends RecyclerView.Adapter<CommunitySubListAdapter.CommunitySubListViewHolder> implements EventListener<QuerySnapshot> {
    private final Context context;
    private final Query mQuery;
    private ArrayList<DocumentSnapshot> mSnapshots  = new ArrayList<>();

    private static OnPostClickListener opcl;

    public CommunitySubListAdapter(Context context, Query query) {
        this.context = context;
        this.mQuery = query;
        mQuery.addSnapshotListener(this);
    }

    @NonNull
    @Override
    public CommunitySubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_list, parent, false);

        return new CommunitySubListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubListViewHolder holder, int position) {
        holder.onBind(mSnapshots.get(position).toObject(CommunitySubListModel.class));
    }

    @Override
    public int getItemCount() {
        return mSnapshots.size();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e != null){
            Log.w("list 에러","onEvent:error", e);
        }
        mSnapshots.clear();
        for(DocumentSnapshot doc : documentSnapshots.getDocuments()){
            mSnapshots.add(doc);
            notifyDataSetChanged();
        }
    }

    public void setOpcl(OnPostClickListener onListener) {
        opcl = onListener;
    }
    public interface OnPostClickListener{
        void onClick(CommunitySubListModel data);
    }

    public static class CommunitySubListViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, content, likeTv, commentTv;
        private final ConstraintLayout layout;
        CommunitySubListModel mData;

        public CommunitySubListViewHolder(View itemView) {
            super(itemView);
            ItemCommunityListBinding binding = ItemCommunityListBinding.bind(itemView);
            title = binding.titleTv;
            content = binding.contentTv;
            likeTv = binding.likeTv;
            commentTv = binding.commentTv;
            layout = binding.layout;
        }
        public void onBind(CommunitySubListModel data){
            mData = data;

            int like_count = likeCounting();
            int comment_count = commentCounting();


            title.setText(data.getTitle());
            content.setText(data.getContent());
            likeTv.setText(like_count + "");
            commentTv.setText(comment_count + "");

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opcl.onClick(data);
                }
            });
        }

        private int likeCounting() {
            if (mData.getLike() == null) return 0;

            int total = 0;
            Map<String, Boolean> map = mData.getLike();

            for (String key : map.keySet()) {
                total += (map.get(key)) ? 1 : 0;
            }

            return total;
        }
        private int commentCounting() {
            int total = mData.getComments().size();
            for (CommunitySubCommentModel data : mData.getComments()) {
                total += data.getCommentModels().size();
            }
            return total;
        }
    }
}
