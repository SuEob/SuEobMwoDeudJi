package com.example.sueobmwodeudji.ClickEvent;

import android.content.Context;
import android.view.View;

public class ClickFactory {
    private final Context context;

    ClickFactory(Context context){
        this.context = context;
    }

    public View.OnClickListener createFirstView(){
        return new CreateFirstView(context);
    }
}
