package com.example.sueobmwodeudji.ui.preferences_ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.sueobmwodeudji.R;

public class SchoolFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.school_preferences, rootKey);

    }
}
