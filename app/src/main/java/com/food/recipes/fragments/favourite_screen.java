package com.food.recipes.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food.recipes.MainActivity;
import com.food.recipes.R;


public class favourite_screen extends Fragment {

  TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_screen, container, false);

        textView = view.findViewById(R.id.text2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                Intent i = new Intent(getContext() , MainActivity.class);
                i.putExtra("text" , text);
                startActivity(i);
            }
        });

        return view;
    }
}