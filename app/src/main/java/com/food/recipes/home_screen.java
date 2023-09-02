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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class home_screen extends Fragment {
    private final String random_url = "https://api.spoonacular.com/recipes/random?number=50";
    private RecyclerView recyclerView;
    public ArrayList<modal> data = new ArrayList<>();
    private ProgressBar bar;
    public custom_adapter adapter;
    private MaterialCardView daily_inspiration;
    private JSONArray arr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        recyclerView = view.findViewById(R.id.Random);
        bar = view.findViewById(R.id.loading);
        Sprite threebounce = new Wave();
        bar.setIndeterminateDrawable(threebounce);
        threebounce.setColor(getResources().getColor(R.color.loading , getActivity().getTheme()));
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
                , random_url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                bar.setVisibility(View.INVISIBLE);
                daily_inspiration.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                try {
                    JSONObject obj = new JSONObject(response);
                    arr = obj.getJSONArray("recipes");
                    for (int i = 0; i < arr.length(); i++) {
                        String title = arr.getJSONObject(i).getString("title");
                        Log.d("name", title);
                        String img = arr.getJSONObject(i).getString("image");
                        String url = arr.getJSONObject(i).getString("sourceUrl");
                        Log.d("url" , url);
                        int readyin = arr.getJSONObject(i).getInt("readyInMinutes");
                        data.add(new modal(title , img , readyin , url));
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
                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String , String>Headers = new HashMap<>();
                Headers.put("x-api-key" , "552d63a010254181bb0fef40c75b8a47" );
                return Headers;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
    }

}