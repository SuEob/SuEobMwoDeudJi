package com.example.sueobmwodeudji;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class FirstView extends LinearLayout {
    public FirstView(Context context) {
        super(context);
    }

    public void init(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.first, this, true);
    }
}
