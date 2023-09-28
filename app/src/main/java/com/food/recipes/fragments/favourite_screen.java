package com.food.recipes.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.food.recipes.Database.Recipe_database_bulider;
import com.food.recipes.Database.Recipe_database_bulider_Impl;
import com.food.recipes.Modals.Saved_Recipe_Entity;
import com.food.recipes.R;
import com.food.recipes.adapter.favouritescreen_adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class favourite_screen extends Fragment {

  private TextView textView;
  private RecyclerView itemlist ;
  private Recipe_database_bulider database;
  private favouritescreen_adapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_screen, container, false);

        database = Recipe_database_bulider.Companion.getDatabase(getContext());

        textView = view.findViewById(R.id.text2);
        itemlist = view.findViewById(R.id.favouritelist);

        adapter = new favouritescreen_adapter(Collections.emptyList() , getContext());
        itemlist.setLayoutManager(new LinearLayoutManager(getContext()));
        itemlist.setAdapter(adapter);

        observeUser();
        return view;
    }

    private void observeUser(){
        database.SavedDao().getData().observe(this, new Observer<List<Saved_Recipe_Entity>>() {
            @Override
            public void onChanged(List<Saved_Recipe_Entity> savedRecipeEntities) {
                adapter.updateUser(savedRecipeEntities);
            }
        });
    }
}