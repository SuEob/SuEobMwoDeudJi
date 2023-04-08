package com.example.sueobmwodeudji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sueobmwodeudji.util.FragmentPage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_test);

        FragmentPage fp = new FragmentPage(this);
        fp.addEventToNavigationVar();
    }
}