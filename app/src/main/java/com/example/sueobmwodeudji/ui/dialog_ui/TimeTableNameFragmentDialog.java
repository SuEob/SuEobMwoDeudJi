package com.example.sueobmwodeudji.ui.dialog_ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sueobmwodeudji.databinding.FragmentTimeTableNameDialogBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class TimeTableNameFragmentDialog extends DialogFragment {
    private FragmentTimeTableNameDialogBinding binding;

    public interface ChangeNameInterface {
        void onClick(String name);
    }

    public ChangeNameInterface changeNameInterface;

    public void setChangeName(ChangeNameInterface changeNameInterface) {
        this.changeNameInterface = changeNameInterface;
    }

    public static TimeTableNameFragmentDialog getInstance() { return new TimeTableNameFragmentDialog(); }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true); // true -> 뒤로가기 버튼 or dialog 뒷배경 터치시 Dialog 종료
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentTimeTableNameDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "입력완료", Toast.LENGTH_SHORT).show();

                String name = binding.nameSueob.getText().toString();
                changeNameInterface.onClick(name);
                getDialog().dismiss();
            }
        });
    }
}
