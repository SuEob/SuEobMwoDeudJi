package com.example.sueobmwodeudji.util;

import android.content.Context;

import com.example.sueobmwodeudji.ClickEvent.MovePageEvent;
import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;

public class FragmentPage {
    Context context;
    MainActivity mainActivity;
    public FragmentPage(Context _context){
        context = _context;

    }

    public void addEventToNavigationVar(){
        MovePageEvent mpe = new MovePageEvent(context);
        mainActivity = (MainActivity) context;

        mainActivity.findViewById(R.id.naviHome)
                .setOnClickListener(mpe.moveHome());
        mainActivity.findViewById(R.id.naviTime)
                .setOnClickListener(mpe.moveTime());
        mainActivity.findViewById(R.id.naviCommunity)
                .setOnClickListener(mpe.moveCommunity());
        mainActivity.findViewById(R.id.naviGood)
                .setOnClickListener(mpe.moveGood());
        mainActivity.findViewById(R.id.naviSetting)
                .setOnClickListener(mpe.moveSetting());
    }
}
