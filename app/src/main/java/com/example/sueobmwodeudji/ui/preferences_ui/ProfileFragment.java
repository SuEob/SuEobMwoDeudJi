package com.example.sueobmwodeudji.ui.preferences_ui;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.sueobmwodeudji.R;

public class ProfileFragment extends PreferenceFragmentCompat {
    Preference preference;
    private AlertDialog.Builder builder;
    private String[] profile;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.my_info_preferences, rootKey);

        preference = findPreference("info_profile");
        preference.setOnPreferenceClickListener(preference -> {
            showDialog();
            return true;
        });
    }

    public void showDialog() {
        profile = getResources().getStringArray(R.array.profile);
        builder = new AlertDialog.Builder(getContext());

        builder.setItems(profile, (dialog, which) -> {
            //
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}







