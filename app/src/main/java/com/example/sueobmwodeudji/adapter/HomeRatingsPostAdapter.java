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
import com.example.sueobmwodeudji.databinding.ItemHomePopularRatingsBinding;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.HomePopularRatingsData;
import com.example.sueobmwodeudji.model.RatingsSubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeRatingsPostAdapter extends RecyclerView.Adapter<HomeRatingsPostAdapter.HomeRatingsPostViewHolder> {
    protected final Context context;
    private ArrayList<CollectionReference> mCols;
    private ArrayList<RatingsSubListModel> list = new ArrayList<>();
    private static OnRatingsListClickListener orcl;

    public HomeRatingsPostAdapter(Context context, ArrayList<CollectionReference> collectionReference) {
        this.context = context;
        this.mCols = collectionReference;
        sortItem();
    }

    private void sortItem() {
        for (CollectionReference col : mCols) {
            col.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot documentSnapshots) {
                    for (DocumentSnapshot doc : documentSnapshots.getDocuments()) {
                        list.add(doc.toObject(RatingsSubListModel.class));
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        list.sort(new Comparator<RatingsSubListModel>() {
                            @Override
                            public int compare(RatingsSubListModel o1, RatingsSubListModel o2) {
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
    public HomeRatingsPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_home_popular_ratings, parent, false);
        return new HomeRatingsPostViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRatingsPostViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOrcl(OnRatingsListClickListener orcl) {
        HomeRatingsPostAdapter.orcl = orcl;
    }
    public interface OnRatingsListClickListener {
        void onClick(RatingsSubListModel data);
    }

    public class HomeRatingsPostViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        public TextView postTitle;
        private ConstraintLayout layout;
        public TextView postContent;

        public HomeRatingsPostViewHolder(Context _context, View itemView) {
            super(itemView);
            ItemHomePopularRatingsBinding binding = ItemHomePopularRatingsBinding.bind(itemView);
            context = _context;
            postTitle = binding.homePostTitle;
            postContent = binding.homePostContent;
            layout = binding.layout;
        }

        public void onBind(RatingsSubListModel data){
            if(context.getApplicationContext() == null) return;
            postTitle.setText(data.getTitle());
            postContent.setText(data.getClassName());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orcl.onClick(data);
                }
            });
        }
    }
}
