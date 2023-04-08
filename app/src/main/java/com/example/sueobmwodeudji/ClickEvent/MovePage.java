package com.example.sueobmwodeudji.ClickEvent;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sueobmwodeudji.MainActivity;
import com.example.sueobmwodeudji.R;

public class MovePage implements View.OnClickListener{
    MainActivity m;
    Fragment movePage;
    MovePage(Context context, Fragment _movePage){
        m = (MainActivity) context;
        movePage = _movePage;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = m.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.navigationBarTest, movePage);
        fragmentTransaction.commit();
    }
}
