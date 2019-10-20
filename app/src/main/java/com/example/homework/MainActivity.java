package com.example.homework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnItemSelected {
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        FirstFragment firstFragment = new FirstFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container1, firstFragment)
                    .commit();
        }


    }

    public void fromFragmentData(String data) {
        text = data;
        SecondFragment fragment;
        fragment = SecondFragment.newInstance(text);
        String backStateName = fragment.getClass().getName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container1, fragment)
                .addToBackStack(backStateName)
                .commit();

    }


    @Override
    public void loadData(String data) {
        fromFragmentData(data);
    }
}
