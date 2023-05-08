package com.example.sueobmwodeudji;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sueobmwodeudji.ui.preferences_ui.MyInfoFragment;

public class SettingsSubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_settings);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings_sub_container, new MyInfoFragment())
                .addToBackStack("null")
                .commit();
    }
}
