package com.food.recipes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;


public class home_screen extends Fragment {
    private final String random_recipe = randomRecipes();
    private final String url = "https://api.edamam.com/api/recipes/v2?type=public&q="+random_recipe+"&app_id=7b682c18&app_key=1f76887b48815e4c658877c6ed2d9eb8";
    private RecyclerView recyclerView;
    public ArrayList<modal> data = new ArrayList<>();
    private ProgressBar bar;
    public custom_adapter adapter;
    private MaterialCardView daily_inspiration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        recyclerView = view.findViewById(R.id.Random);
        bar = view.findViewById(R.id.loading);
        daily_inspiration = view.findViewById(R.id.materialCardView);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new custom_adapter(getContext(), data);
        recyclerView.setAdapter(adapter);
        getData();
        return view;
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET
                , url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                bar.setVisibility(View.INVISIBLE);
                daily_inspiration.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arr = obj.getJSONArray("hits");
                    for (int i = 0; i < arr.length(); i++) {
                        String name = arr.getJSONObject(i).getJSONObject("recipe").getString("label");
                        Log.d("name", name);
                        String img = arr.getJSONObject(i).getJSONObject("recipe").getJSONObject("images").getJSONObject("THUMBNAIL").getString("url");
                        data.add(new modal(name, img));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.d("tagg", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", error.toString());
                Toast.makeText(getContext(),"404", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }

    private String randomRecipes() {

        String[] recipe_array_nonveg = new String[]{"lamb" , "pasta" , "prawns" , "chicken" , "steak"};

        Random random = new Random();
        int random_index = random.nextInt(recipe_array_nonveg.length);
        return recipe_array_nonveg[random_index];

    }
}