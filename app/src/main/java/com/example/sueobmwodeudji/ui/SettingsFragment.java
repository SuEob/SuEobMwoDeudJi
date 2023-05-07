package com.example.sueobmwodeudji.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.sueobmwodeudji.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}
