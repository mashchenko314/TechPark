package com.example.homework;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_text, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String text = "no value supplied";
        Bundle arguments = getArguments();
        if (arguments != null) {
            text = arguments.getString("SomeString");
        }
        int i = Integer.parseInt(text);
        if (i % 2 == 0) {
            ((TextView) view.findViewById(R.id.text_item)).setTextColor(Color.BLUE);
            ((TextView) view.findViewById(R.id.text_item)).setText(text);
        } else {
            ((TextView) view.findViewById(R.id.text_item)).setTextColor(Color.RED);
            ((TextView) view.findViewById(R.id.text_item)).setText(text);
        }
        Button button = view.findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    static SecondFragment newInstance(String someString) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString("SomeString", someString);
        fragment.setArguments(args);
        return fragment;
    }

}
