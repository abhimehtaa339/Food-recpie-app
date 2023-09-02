package com.food.recipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.util.Objects;


public class explore_screen extends Fragment {

    private String []images = new String[]{
            "https://cdn.pixabay.com/photo/2016/02/05/15/34/pasta-1181189_1280.jpg",
            "https://cdn.pixabay.com/photo/2016/12/26/17/28/spaghetti-1932466_1280.jpg",
            "https://cdn.pixabay.com/photo/2015/10/02/15/59/olive-oil-968657_1280.jpg",
    };

    private ImageView image1 , image2 , image3;
    private ImageButton button1 , button2 , button3 , button4 , button5 , button6;


    private ProgressBar loading_bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore_screen, container, false);

        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        imageLoading();

        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        button6 = view.findViewById(R.id.button6);

        loading_bar = view.findViewById(R.id.loading);
        Sprite doublebounce = new ThreeBounce();
        loading_bar.setIndeterminateDrawable(doublebounce);


        OnclickListner(button1 , "Button 1 is clicked");
        OnclickListner(button2 , "Button 2 is clicked");
        OnclickListner(button3 , "Button 3 is clicked");
        OnclickListner(button4 , "Button 4 is clicked");
        OnclickListner(button5 , "Button 5 is clicked");
        OnclickListner(button6 , "Button 6 is clicked");

        return view;
    }

    private void imageLoading() {

        Glide.with(getContext()).load(images[0]).placeholder(R.drawable.placeholder).into(image1);
        Glide.with(getContext()).load(images[1]).placeholder(R.drawable.placeholder).into(image2);
        Glide.with(getContext()).load(images[2]).placeholder(R.drawable.placeholder).into(image3);
    }

    private void OnclickListner(ImageButton button, String recipe){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "pressed", Toast.LENGTH_SHORT).show();
                loading_bar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void fetchData(String recipe) {
        String url = "https://api.edamam.com/api/recipes/v2?type=public&q="+recipe+"&app_id=7b682c18&app_key=1f76887b48815e4c658877c6ed2d9eb8";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}