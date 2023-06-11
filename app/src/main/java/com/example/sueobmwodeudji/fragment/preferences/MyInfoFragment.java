package com.example.sueobmwodeudji.fragment.preferences;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.sueobmwodeudji.R;

public class MyInfoFragment extends PreferenceFragmentCompat {
    ImageView imageView;
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
            if (which == 0) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            } else if (which == 1) {
                // 이미지 삭제
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if (resultCode == getActivity().RESULT_OK) {
                    Uri uri = data.getData();
                    imageView.setImageURI(uri);
                }
                break;
        }
    }


}
