package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;

import java.util.ArrayList;

public class CommunitySubCommentAdapter extends RecyclerView.Adapter<CommunitySubCommentAdapter.CommunitySubCommentViewHolder> {
    private final Context context;
    //private static CommunitySubListModel mCommunitySubListModels = null;
    private final ArrayList<CommunitySubCommentModel> commentModels;

    public void setSibal(OnCocommentPositiveListener sibal) {
        ocpl = sibal;
    }

    private static OnCocommentPositiveListener ocpl;

    public CommunitySubCommentAdapter(Context context, CommunitySubListModel communitySubListModels) {
        this.context = context;
        commentModels = (communitySubListModels.getComments() != null) ? communitySubListModels.getComments() : new ArrayList<>();
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

        private final CommunitySubPostActivity c;
        private int mPositon;

        public CommunitySubCommentViewHolder(Context _context, @NonNull View itemView) {
            super(itemView);
            context = _context;
            binding = ItemCommunityCommentBinding.bind(itemView);
            c = (CommunitySubPostActivity) context;
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

                AlertDialog.Builder dlg = new AlertDialog.Builder(c);
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
                Toast.makeText(c, "취소되었습니다.", Toast.LENGTH_SHORT).show();
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
