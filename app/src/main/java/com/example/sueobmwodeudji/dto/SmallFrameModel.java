package com.example.sueobmwodeudji.dto;

import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;

public class SmallFrameModel {
    public String asdasd;
    public int layoutId;

    public SmallFrameModel(String asd, int layoutId) {
        asdasd = asd;
        this.layoutId = layoutId;
    }

    public RecyclerView recyclerView;

    public SmallFrameModel(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
