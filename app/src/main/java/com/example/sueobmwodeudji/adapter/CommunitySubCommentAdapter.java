package com.example.sueobmwodeudji.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sueobmwodeudji.CommunitySubPostActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.ActivityCommunitySubPostBinding;
import com.example.sueobmwodeudji.databinding.ItemCommunityCommentBinding;
import com.example.sueobmwodeudji.model.CommunitySubCommentCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubCommentModel;
import com.example.sueobmwodeudji.model.CommunitySubListModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommunitySubCommentAdapter extends RecyclerView.Adapter<CommunitySubCommentAdapter.CommunitySubCommentViewHolder> {
    private final Context context;
    private static CommunitySubListModel mCommunitySubListModels = null;
    private final ArrayList<CommunitySubCommentModel> commentModels;

    public void setSibal(OnCocommentPositiveListener sibal) {
        ocpl = sibal;
    }

    private static OnCocommentPositiveListener ocpl;

    public CommunitySubCommentAdapter(Context context, CommunitySubListModel communitySubListModels) {
        this.context = context;
        mCommunitySubListModels = communitySubListModels;
        commentModels = (mCommunitySubListModels.getComments() != null) ? communitySubListModels.getComments() : new ArrayList<>();
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

        public class CommentLayoutClickListenr implements View.OnClickListener {
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
        public class NegativeButtonClickListener implements DialogInterface.OnClickListener {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(c, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        public class PositiveButtonClickListener implements DialogInterface.OnClickListener{

            @Override
            public void onClick(DialogInterface dialog, int which) {
                ocpl.onPositive(mPositon);
            }

        }

//                post_binding.commentEt.setHint("asdfasfd");
//                Toast.makeText(context, "대대대대댓", Toast.LENGTH_SHORT).show();
//                post_binding.submitBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        //Toast.makeText(CommunitySubPostActivity.this, "댓글 작성", Toast.LENGTH_SHORT).show();

//
//        private void createComment(){
//            String comment = post_binding.commentEt.getText().toString();
//            CommunitySubCommentModel comment_model = new CommunitySubCommentModel();
//            comment_model.setContent(comment);
//            comment_model.setTimestamp(Timestamp.now().toDate());
//            comment_model.setName(id);
//
//            mCommunitySubListModels.getComments().add(comment_model);
//
//            //파베 Create
//            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
//            mFirestore.collection(firstCP)
//                    .document(firstDP)
//                    .collection(secondCP)
//                    .document(mCommunitySubListModels.getName()+mCommunitySubListModels.getTimestamp())
//                    .update("comments", mCommunitySubListModels.getComments()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(c, "작성되었습니다.", Toast.LENGTH_SHORT).show();
//                            FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
//                            mFirestore.collection("testPost")
//                                    .document("first")
//                                    .collection("1학년 게시판")
//                                    .document(mCommunitySubListModels.getName()+mCommunitySubListModels.getTimestamp())
//                                    .get().onSuccessTask(new SuccessContinuation<DocumentSnapshot, Object>() {
//                                        @NonNull
//                                        @Override
//                                        public Task<Object> then(DocumentSnapshot documentSnapshot) throws Exception {
//                                            Intent _intent = new Intent(c, CommunitySubPostActivity.class);
//                                            CommunitySubListModel _data = documentSnapshot.toObject(CommunitySubListModel.class);
//                                            _intent.putExtra("data", _data);
//                                            c.finish();
//                                            c.startActivity(_intent);
//                                            return null;
//                                        }
//                                    });
//
//
//                        }
//                    });
//       }
    }
}
