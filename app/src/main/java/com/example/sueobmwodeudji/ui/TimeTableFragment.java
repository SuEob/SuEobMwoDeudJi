package com.example.sueobmwodeudji.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.TimeTableSecondActivity;
import com.example.sueobmwodeudji.TimeTableThridActivity;
import com.example.sueobmwodeudji.adapter.BasicFrameAdapter;
import com.example.sueobmwodeudji.databinding.FragmentTimeTableBinding;
import com.example.sueobmwodeudji.databinding.ItemTimeTableSub1Binding;
import com.example.sueobmwodeudji.model.BasicFrameModel;

import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

public class TimeTableFragment extends Fragment {
    private FragmentTimeTableBinding binding;
    private List<BasicFrameModel> list = new LinkedList<BasicFrameModel>();

    public static TimeTableFragment getInstance() {
        return new TimeTableFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTimeTableBinding.inflate(inflater, container, false);
        binding.tableList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), TimeTableSecondActivity.class);
                startActivity(intent);
            }
        });

        binding.tableNameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newName = new EditText(getContext());
                newName.setHint("변경할 시간표 명");
                new AlertDialog.Builder(getContext()).setTitle("시간표 이름 변경").setView(newName)
                        .setPositiveButton("작성", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d ("성sdrsdr공", "눌drsrs림");
                    }
                }).show();
            }
        });

        binding.tableDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogClick(v);
            }
        });

        return binding.getRoot();
    } // onCreateView

    public void DialogClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("시간표 삭제").setMessage("시간표를 삭제하시겠습니까?");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d ("성공", "눌림");
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d ("실패", "눌림");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(binding.getRoot(), savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}