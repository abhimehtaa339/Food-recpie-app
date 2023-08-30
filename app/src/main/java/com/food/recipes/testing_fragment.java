package com.food.recipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class testing_fragment extends Fragment {

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testing_fragment, container, false);
        textView = view.findViewById(R.id.text);
        Intent intent = new Intent();
        textView.setText(intent.getStringExtra("text"));
        return view;
    }
}