package com.example.sueobmwodeudji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sueobmwodeudji.ClickEvent.NavigationVeiwListener;
import com.example.sueobmwodeudji.Fragment.FragPage;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_test);

        NavigationBarView nbv = findViewById(R.id.navigationBarTest);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigationBarTest, new FragPage(R.layout.activity_home)).commit();
        nbv.setOnItemSelectedListener(new NavigationVeiwListener(this));
    }
}