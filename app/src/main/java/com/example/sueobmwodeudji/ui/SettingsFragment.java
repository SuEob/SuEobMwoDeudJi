package com.example.sueobmwodeudji.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.sueobmwodeudji.LoginActivity;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SettingsFragment extends PreferenceFragmentCompat {
    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";
    public static Uri profile_uri;
    public static boolean checkImg = true;

    private android.app.AlertDialog.Builder builder;
    private String[] profile;

    private final int DEFAULT_GALLERY_REQUEST_CODE = 999;


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        // 닉네임 설정
        findPreference("info_name").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
                String name = sp.getString("info_name", "");
                nameSave(getContext(), name);
                return true;
            }
        });

        // 프로필 이미지 설정
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

    // 기본 갤러리에서 이미지 Uri를 받아옴
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) { // 이미지를 받아옴
            profile_uri = data.getData();
            imgSave(getContext(), profile_uri);
            Toast.makeText(getContext(), "사진을 불러왔습니다.", Toast.LENGTH_SHORT).show();
        } else { // 이미지를 받지 못함
            Toast.makeText(getContext(), "사진을 불러오기 못했습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // 프로필 이미지 변경 다이얼로그
    private void showDialog() {
        profile = getResources().getStringArray(R.array.profile);
        builder = new android.app.AlertDialog.Builder(getContext());

        builder.setItems(profile, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) { // 프로필 이미지 변경
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, DEFAULT_GALLERY_REQUEST_CODE);
                    checkImg = true;
                    Log.d("TAG", String.valueOf(which));
                } else if (which == 1) { // 프로필 이미지 삭제
                    profile_uri = null;
                    checkImg = false;
                    Log.d("TAG", String.valueOf(which));
                }
            }
        });

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // SharedPreferences 프로필 이미지 저장
    public static void imgSave(Context context, Uri uri) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("uri", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("uri", uri.toString());
        editor.commit();
    }

    // SharedPreferences 프로필 이미지 불러오기
    public static String imgLoad(Context context) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("uri", context.MODE_PRIVATE);
        String load = sp.getString("uri", "null");
        return load;
    }

    // SharedPreferences 닉네임 저장
    public static void nameSave(Context context, String name) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.commit();
    }

    // SharedPreferences 닉네임 불러오기
    public static String nameLoad(Context context) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("name", context.MODE_PRIVATE);
        String load = sp.getString("name", "null");
        return load;
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
