package com.example.sueobmwodeudji.model;

import android.view.View;

import androidx.fragment.app.Fragment;

public class BasicFrameModel {
    public String title;
    public int layoutId;
    public Fragment fragment;
    public View view;


    // 좀 빡셈
    public BasicFrameModel(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    // 이걸로 ㄱㄱ
    public BasicFrameModel(String title, View view) {
        this.title = title;
        this.view = view;
    }

    public BasicFrameModel(String title, int  layoutId) {
        this.title = title;
        this.layoutId = layoutId;
    }
}
