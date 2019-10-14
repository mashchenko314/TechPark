package com.example.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        FirstFragment firstFragment= new FirstFragment();
        if (savedInstanceState==null){
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container1, firstFragment)
                .commit();}


    }
    public void fromFragmentData(String data) {
        text = data;
        SecondFragment fragment=new SecondFragment().newInstance(text);
        String backStateName = fragment.getClass().getName();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container1,fragment)
                .addToBackStack(backStateName)
                .commit();

    }


}
