package com.example.sueobmwodeudji;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class ClickFactory {
    private final Context context;

    ClickFactory(Context context){
        this.context = context;
    }

    public View.OnClickListener getCreateFirstView(){
        return new CreateFirstView(context);
    }
}
