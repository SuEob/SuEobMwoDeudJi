package com.example.sueobmwodeudji.util;

import android.content.Context;

import com.example.sueobmwodeudji.ClickEvent.MoveFragmentPageEvent;
import com.example.sueobmwodeudji.MainActivity;

public class FragmentNavigationBar {
    Context context;
    int[] navigation_item_id, navigation_item_layout;

    public FragmentNavigationBar(Context _context){
        context = _context;
    }

    public void addEventToNavigationBar(){
        MoveFragmentPageEvent mpe = new MoveFragmentPageEvent(context);
        MainActivity mainActivity = (MainActivity) context;

        for(int i = 0; i<navigation_item_id.length; i++){
            System.out.println("씨발 : "+i + navigation_item_id[i] + "??" + navigation_item_layout[i]);
            mainActivity.findViewById(navigation_item_id[i])
                    .setOnClickListener(mpe.movePage(navigation_item_layout[i]));
        }
    }

    public void setNavigationItem(int[] navigation_item_id, int[] navigation_item_layout) {
        this.navigation_item_id = navigation_item_id;
        this.navigation_item_layout = navigation_item_layout;
    }

}
