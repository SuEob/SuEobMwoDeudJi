package com.example.sueobmwodeudji.model;

import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

public class BasicFrameModel {
    public String title;
    public int layoutId;

    public BasicFrameModel(String title, int layoutId) {
        this.title = title;
        this.layoutId = layoutId;
    }

    public FrameLayout frameLayout;

    public BasicFrameModel(String title, FrameLayout frameLayout) {
        this.title = title;
        this.frameLayout = frameLayout;
    }

    public RecyclerView recyclerView;

    public BasicFrameModel(String title, RecyclerView recyclerView) {
        this.title = title;
        this.recyclerView = recyclerView;
    }
}
