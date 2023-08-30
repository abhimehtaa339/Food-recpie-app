package com.food.recipes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.Objects;


public class explore_screen extends Fragment {

    private String []images = new String[]{
            "https://cdn.pixabay.com/photo/2016/02/05/15/34/pasta-1181189_1280.jpg",
            "https://cdn.pixabay.com/photo/2016/12/26/17/28/spaghetti-1932466_1280.jpg",
            "https://cdn.pixabay.com/photo/2015/10/02/15/59/olive-oil-968657_1280.jpg",
            "https://cdn.pixabay.com/photo/2016/03/05/19/02/salmon-1238248_1280.jpg"

    };

    private ImageView image1 , image2 , image3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_screen, container, false);

        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        Glide.with(getContext()).load(images[0]).placeholder(R.drawable.placeholder).into(image1);
        Glide.with(getContext()).load(images[1]).placeholder(R.drawable.placeholder).into(image2);
        Glide.with(getContext()).load(images[2]).placeholder(R.drawable.placeholder).into(image3);

        return view;
    }
}