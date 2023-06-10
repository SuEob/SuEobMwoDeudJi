package com.example.sueobmwodeudji.ui.coach_mark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;

public class Settings extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coach_mark_settings, container, false);
        Button button = view.findViewById(R.id.coach_mark_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coachMarkSave(getContext(), false);
                Log.d("boolean", String.valueOf(false));

                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // 기존 Activity 제거
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

    public static void coachMarkSave(Context context, boolean registration) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("registration", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("registration", registration);
        editor.commit();
    }

    // SharedPreferences 프로필 이미지 불러오기
    public static boolean coachMarkLoad(Context context) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("registration", context.MODE_PRIVATE);
        boolean load = sp.getBoolean("registration", true);
        return load;
    }

}