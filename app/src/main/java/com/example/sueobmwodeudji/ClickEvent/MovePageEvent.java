package com.example.sueobmwodeudji.ClickEvent;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.sueobmwodeudji.Fragment.FragCommunity;
import com.example.sueobmwodeudji.Fragment.FragGood;
import com.example.sueobmwodeudji.Fragment.FragHome;
import com.example.sueobmwodeudji.Fragment.FragSettings;
import com.example.sueobmwodeudji.Fragment.FragTime;

public class MovePageEvent {
    private final Context context;
    private Fragment frag;

    public MovePageEvent(Context _context){
        context = _context;
    }

    public View.OnClickListener moveHome(){
        frag = new FragHome();
        return createMovePage();
    }
    public View.OnClickListener moveTime(){
        frag = new FragTime();
        return createMovePage();
    }
    public View.OnClickListener moveCommunity(){
        frag = new FragCommunity();
        return createMovePage();
    }
    public View.OnClickListener moveGood(){
        frag = new FragGood();
        return createMovePage();
    }
    public View.OnClickListener moveSetting(){
        frag = new FragSettings();
        return createMovePage();
    }
    MovePage createMovePage(){
        return new MovePage(context, frag);
    }
}
