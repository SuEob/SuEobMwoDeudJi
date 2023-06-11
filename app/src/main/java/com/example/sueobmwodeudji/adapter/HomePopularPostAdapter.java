package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemHomePopularPostBinding;
import com.example.sueobmwodeudji.dto.CommunitySubCommentModel;
import com.example.sueobmwodeudji.dto.CommunitySubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class HomePopularPostAdapter extends RecyclerView.Adapter<HomePopularPostAdapter.HomePopularPostViewHolder> {
    protected final Context context;
    private ArrayList<CollectionReference> mCols;
    private ArrayList<CommunitySubListModel> list = new ArrayList<>();

    private static OnPopularPostClickListener opcl;

    public HomePopularPostAdapter(Context context, ArrayList<CollectionReference> collectionReferences) {
        this.context = context;
        this.mCols = collectionReferences;
        sortItem();
    }

    private void sortItem() {
        for (CollectionReference col : mCols) {
            col.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot documentSnapshots) {
                    for (DocumentSnapshot doc : documentSnapshots.getDocuments()) {
                        list.add(doc.toObject(CommunitySubListModel.class));
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        list.sort(new Comparator<CommunitySubListModel>() {
                            @Override
                            public int compare(CommunitySubListModel o1, CommunitySubListModel o2) {
                                return o2.getLike().size() - o1.getLike().size();
                            }
                        });
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    @NonNull
    @Override
    public HomePopularPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_home_popular_post, parent, false);
        HomePopularPostViewHolder viewHolder = new HomePopularPostViewHolder(context, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePopularPostAdapter.HomePopularPostViewHolder
                                         holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOpcl(OnPopularPostClickListener opcl) {
        HomePopularPostAdapter.opcl = opcl;
    }

    public interface OnPopularPostClickListener {
        void onClick(CommunitySubListModel data);
    }

    public static class HomePopularPostViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        public TextView postTitle, postDate,likeTv, commentTv;
        private ConstraintLayout layout;

        public HomePopularPostViewHolder(Context _context, View itemView) {
            super(itemView);
            context = _context;
            ItemHomePopularPostBinding binding = ItemHomePopularPostBinding.bind(itemView);
            postTitle = binding.homePostTitle;
            postDate = binding.homePostDate;
            likeTv = binding.likeTv;
            commentTv = binding.commentTv;
            layout = binding.layout;
        }
        public void onBind(CommunitySubListModel data){
            if(context.getApplicationContext() == null) return;
            postTitle.setText(data.getTitle());
            postDate.setText(data.getCategory());
            likeTv.setText(likeCounting(data.getLike()) + "");
            commentTv.setText(commentCounting(data.getComments()) + "");
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    opcl.onClick(data);
                }
            });
        }

        private int likeCounting(Map<String, Boolean> comment) {
            if (comment == null) return 0;

            int total = 0;

            for (String key : comment.keySet()) {
                total += (comment.get(key)) ? 1 : 0;
            }

            return total;
        }

        private int commentCounting(ArrayList<CommunitySubCommentModel> commentModels) {
            int total = commentModels.size();
            for (CommunitySubCommentModel data : commentModels) {
                total += data.getCommentModels().size();
            }
            return total;
        }
    }
}
