package com.food.recipes.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.food.recipes.Explore_screen_result;
import com.food.recipes.Modals.modal;
import com.food.recipes.R;
import com.food.recipes.adapter.custom_adapter;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    private EditText search;


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


        OnclickListner(button1 , "chicken");
        OnclickListner(button2 , "Salad");
        OnclickListner(button3 , "Pasta");
        OnclickListner(button4 , "Bread");
        OnclickListner(button5 , "Pizza");
        OnclickListner(button6 , "Egg");

        search = view.findViewById(R.id.search_bar);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE){
                    String query = search.getText().toString();
                    loading_bar.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getContext() , Explore_screen_result.class);
                    intent.putExtra("recipe" , query);
                    startActivity(intent);
                    loading_bar.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });

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
                Intent intent = new Intent(getContext() , Explore_screen_result.class);
                intent.putExtra("recipe" , recipe);
                startActivity(intent);
                loading_bar.setVisibility(View.GONE);
            }
        });
    }

}