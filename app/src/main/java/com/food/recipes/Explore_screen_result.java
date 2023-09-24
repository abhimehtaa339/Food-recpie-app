package com.food.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.food.recipes.Modals.modal;
import com.food.recipes.adapter.custom_adapter;
import com.food.recipes.adapter.search_screen_adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Explore_screen_result extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String recipe;
    private JSONArray arr;
    public ArrayList<modal> data = new ArrayList<>();
    public search_screen_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_screen_result);

        recyclerView = findViewById(R.id.recyclerview);

        Intent i = new Intent();
        i = getIntent();
        recipe = i.getStringExtra("recipe");
        Log.d("recip" , recipe);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new search_screen_adapter(this , data);
        recyclerView.setAdapter(adapter);
        fetchData(recipe);
    }

    private void fetchData(String recipe) {
        String url = "https://api.spoonacular.com/recipes/complexSearch?query="+recipe+"&maxFat=25&number=50&instructionsRequired=true&fillIngredients=true&addRecipeInformation=true";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            arr = obj.getJSONArray("results");
                            for (int i = 0; i < arr.length(); i++) {
                                String title = arr.getJSONObject(i).getString("title");
                                Log.d("title", title);
                                String img = arr.getJSONObject(i).getString("image");
                                String url = arr.getJSONObject(i).getString("sourceUrl");
                                Log.d("url" , url);
                                int readyin = arr.getJSONObject(i).getInt("readyInMinutes");
                                int id = arr.getJSONObject(i).getInt("id");
                                Log.d("id" , String.valueOf(id));
                                String summary = arr.getJSONObject(i).getString("summary");
                                Log.d("summary" , summary);


                                data.add(new modal(title , img , readyin , url));
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            Log.d("taggg", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", error.toString());
                Toast.makeText(getApplicationContext() ,error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String , String> Headers = new HashMap<>();
            Headers.put("x-api-key" , getApplicationContext().getString(R.string.api_key));
            return Headers;
        }
    };

    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
    }
}