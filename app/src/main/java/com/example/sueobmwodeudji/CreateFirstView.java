package com.example.sueobmwodeudji;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class CreateFirstView implements View.OnClickListener{
    private final Context context;
    private final MainActivity m;
    CreateFirstView(Context context){
        this.context = context;
        m = (MainActivity) context;
    }
    @Override
    public void onClick(View view) {
        ViewFactory vf = new ViewFactory();
        LinearLayout firView = vf.getFirstView(context);
        LinearLayout parent = m.findViewById(R.id.mainActivity);

        parent.addView(firView);
    }
}
