package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemTimeTableListBinding;
import com.example.sueobmwodeudji.dto.TimeTableDTO;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.example.sueobmwodeudji.model.TimeTableSubFrameModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TimeTableSubListAdapter extends RecyclerView.Adapter<TimeTableSubListAdapter.TimeTableSubListViewHolder> implements EventListener<QuerySnapshot> {
    private final Context context;
    private final Query mQuery;
    private ArrayList<DocumentSnapshot> mSnapshots = new ArrayList<>();

    private static OnListClickListener olcl;
    private static OnListDeletebtnClickListener odcl;

    public TimeTableSubListAdapter(Context context, Query query) {
        this.context = context;
        this.mQuery = query;
        mQuery.addSnapshotListener(this);
    }


    @NonNull
    @Override
    public TimeTableSubListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_time_table_list, parent, false);

        return new TimeTableSubListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeTableSubListViewHolder holder, int position) {
        holder.onBind(mSnapshots.get(position).toObject(TimeTableDTO.class));
    }

    @Override
    public int getItemCount() {
        return mSnapshots.size();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            Log.w("list 에러", "onEvent:error", e);
        }
        mSnapshots.clear();
         for (DocumentSnapshot doc : documentSnapshots.getDocuments()) {
            mSnapshots.add(doc);
            notifyDataSetChanged();
        }
    }

    public void setOlcl(OnListClickListener onListener) {
        olcl = onListener;
    }

    public interface OnListClickListener {
        void onClick(TimeTableDTO data);
    }

    public void setOdcl(OnListDeletebtnClickListener onListener) {
        TimeTableSubListAdapter.odcl = onListener;
    }

    public interface OnListDeletebtnClickListener {
        void onClick(TimeTableDTO data);
    }




    public static class TimeTableSubListViewHolder extends RecyclerView.ViewHolder {
        public TextView listTitle;
        public ImageButton listBtn;
        public ConstraintLayout layout;

        public TimeTableSubListViewHolder(View itemView) {
            super(itemView);
            ItemTimeTableListBinding binding = ItemTimeTableListBinding.bind(itemView);
            listTitle = binding.timeTableListTitle;
            listBtn = binding.timeTableListBtn;
            layout = binding.layout;
        }

        public void onBind(TimeTableDTO data) {
            listTitle.setText(data.getTimeTableName());
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v){
                    olcl.onClick(data);
                }
            });

            listBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    odcl.onClick(data);
                }
            });
        }
    }
}