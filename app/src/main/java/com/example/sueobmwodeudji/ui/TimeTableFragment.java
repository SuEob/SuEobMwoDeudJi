package com.example.sueobmwodeudji.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.example.sueobmwodeudji.TimeTableSubActivity;
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

        Intent intent = new Intent(getContext(), TimeTableSubActivity.class);
        binding.addViewBtn.setOnClickListener(v -> {
            intent.putExtra("Code", 0);
            startActivity(intent);
        });

        binding.listViewBtn.setOnClickListener(view -> {
            intent.putExtra("Code", 1);
            startActivity(intent);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.time_table_tool_bar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getContext(), TimeTableSubActivity.class);

        if (item.getItemId() == R.id.add) {
            intent.putExtra("Code", 0);
            startActivity(intent);
        } else if (item.getItemId() == R.id.list) {
            intent.putExtra("Code", 1);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onChangeTimeTable(String class_name) {
        Log.d("class_title", class_name);
        binding.timeTable.friday1.setText(class_name);// 여기 값이 없음
    }
}