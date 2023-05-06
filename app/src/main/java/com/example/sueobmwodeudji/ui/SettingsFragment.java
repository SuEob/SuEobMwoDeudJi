package com.example.sueobmwodeudji.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.databinding.FragmentSettingsBinding;


public class SettingsFragment extends PreferenceFragmentCompat {

    private FragmentSettingsBinding binding;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}
