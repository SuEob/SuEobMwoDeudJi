package com.example.sueobmwodeudji.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.sueobmwodeudji.LoginActivity;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";

    private ImageView imageView;
    private Preference preference;
    private android.app.AlertDialog.Builder builder;
    private String[] profile;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // 프로필 이미지 변경
        findPreference("info_profile").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                showDialog();
                return true;
            }
        });

        // 로그아웃
        findPreference("root_logout").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
                dlg.setTitle("로그아웃");
                dlg.setMessage("로그아웃 하시겠습니까?");
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
                        logout();
                    }
                });
                dlg.show();

                return true;
            }
        });

        // 다크모드
        findPreference("dark_mode").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                MainActivity.dark_mode = true;
                if ((boolean) newValue) { // true -> Dark Mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    modeSave(getContext(), DARK_MODE);
                    Log.d("TAG", "Dark Mode");
                } else { // false -> Light Mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    modeSave(getContext(), LIGHT_MODE);
                    Log.d("TAG", "Light Mode");
                }
                return true;
            }
        });
    }

    // SharedPreferences 로 다크모드 상태값 저장
    public static void modeSave(Context context, String select_mod) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("mode", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mode", select_mod);
        editor.commit();
    }

    // SharedPreferences 로 다크모드 상태값 호출
    public static String modeLoad(Context context) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("mode", context.MODE_PRIVATE);
        String load = sp.getString("mode", "light");
        return load;
    }

    // 프로필 이미지 변경 다이얼로그
    private void showDialog() {
        profile = getResources().getStringArray(R.array.profile);
        builder = new android.app.AlertDialog.Builder(getContext());

        builder.setItems(profile, (dialog, which) -> {
            if (which == 0) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            } else if (which == 1) {
                // 이미지 삭제
            }
        });

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // 로그아웃 확인
    private void logout() {
        AuthUI.getInstance()
                .signOut(getActivity())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
    }

}
