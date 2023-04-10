package com.example.sueobmwodeudji.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.R;

public class FragPage extends Fragment {

    private final int navigation_item_layout;

    public FragPage(int navigation_item_layout){
        this.navigation_item_layout = navigation_item_layout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(navigation_item_layout, container, false);
    }
}
