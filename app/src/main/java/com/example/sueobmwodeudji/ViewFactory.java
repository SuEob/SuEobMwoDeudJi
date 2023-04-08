package com.example.sueobmwodeudji;

import android.content.Context;
import android.widget.LinearLayout;

public class ViewFactory {
    public LinearLayout getFirstView(Context context){
        FirstView view = new FirstView(context);
        view.init();

        return  view;
    }
}
