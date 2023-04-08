package com.example.sueobmwodeudji.ClickEvent;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;
import com.example.sueobmwodeudji.ViewFactory;

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
