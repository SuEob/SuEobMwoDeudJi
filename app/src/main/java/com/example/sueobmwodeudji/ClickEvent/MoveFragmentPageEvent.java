package com.example.sueobmwodeudji.ClickEvent;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.Fragment.FragPage;

public class MoveFragmentPageEvent {
    private final Context context;
    private Fragment frag;

    public MoveFragmentPageEvent(Context _context){
        context = _context;
    }

    public View.OnClickListener movePage(int navigation_item_layout){
        frag = new FragPage(navigation_item_layout);
        return createMovePage();
    }
    MoveFragmentPage createMovePage(){
        return new MoveFragmentPage(context, frag);
    }
}
