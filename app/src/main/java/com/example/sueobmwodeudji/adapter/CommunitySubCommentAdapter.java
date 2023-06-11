package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentBinding;
import com.example.sueobmwodeudji.dto.CommunitySubCommentModel;
import com.example.sueobmwodeudji.dto.CommunitySubListModel;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class CommunitySubCommentAdapter extends RecyclerView.Adapter<CommunitySubCommentAdapter.CommunitySubCommentViewHolder> implements EventListener<DocumentSnapshot> {
    private final Context context;
    //private static CommunitySubListModel mCommunitySubListModels = null;
    private ArrayList<CommunitySubCommentModel> commentModels = new ArrayList<>();
    private DocumentReference mReference;

    private String firstCP, firstDP, secondCP;

    private static OnCocommentPositiveListener ocpl;

    public void setOclp(OnCocommentPositiveListener onListener) {
        ocpl = onListener;
    }

    public CommunitySubCommentAdapter(Context context, DocumentReference documentReference) {
        this.context = context;
        mReference = documentReference;
        mReference.addSnapshotListener(this);
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot doc, @Nullable FirebaseFirestoreException e) {
        if(e != null){
            Log.w("list 에러","onEvent:error", e);
        }
        commentModels.clear();
        //Log.d("Asdfasdfdfsa", doc.toObject(CommunitySubListModel.class).toString());
        if(doc != null) {
            if(doc.toObject(CommunitySubListModel.class) != null) {
                ArrayList<CommunitySubCommentModel> data = doc.toObject(CommunitySubListModel.class).getComments();
                commentModels = (data != null) ? data : new ArrayList<>();
                notifyDataSetChanged();
            }
        }
    }

    public interface OnCocommentPositiveListener{
        void onPositive(int position);
    }

    @NonNull
    @Override
    public CommunitySubCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_community_comment, parent, false);

        return new CommunitySubCommentViewHolder(context, view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitySubCommentViewHolder holder, int position) {
        holder.onBind(commentModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }


    public static class CommunitySubCommentViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        private final ItemCommunityCommentBinding binding;

        private int mPositon;

        public CommunitySubCommentViewHolder(Context _context, @NonNull View itemView) {
            super(itemView);
            context = _context;
            binding = ItemCommunityCommentBinding.bind(itemView);
        }

        public void onBind(CommunitySubCommentModel data, int position) {
            binding.idTv.setText(data.getName());
            binding.dateTv.setText(data.getTimestamp().toString());
            binding.contentTv.setText(data.getContent());

            binding.layout.setOnClickListener(new CommentLayoutClickListenr());

            mPositon = position;

            CommunitySubCommentCommentAdapter adapter = new CommunitySubCommentCommentAdapter(context, data.getCommentModels());
            binding.recyclerView.setAdapter(adapter);
        }

        private class CommentLayoutClickListenr implements View.OnClickListener {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                dlg.setTitle("대댓글 작성")
                        .setMessage("대댓글을 작성하시겠습니까?")
                        .setNegativeButton("취소", new NegativeButtonClickListener())
                        .setPositiveButton("확인", new PositiveButtonClickListener())
                        .show();
            }

        }
        private class NegativeButtonClickListener implements DialogInterface.OnClickListener {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        private class PositiveButtonClickListener implements DialogInterface.OnClickListener{

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ocpl.onPositive(mPositon);
            }
        }
    }
}
