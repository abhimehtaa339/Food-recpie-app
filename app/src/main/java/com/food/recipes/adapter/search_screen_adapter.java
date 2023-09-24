package com.food.recipes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.food.recipes.Modals.modal;
import com.food.recipes.R;

import java.util.ArrayList;

public class search_screen_adapter extends RecyclerView.Adapter<search_screen_adapter.ViewHolder> {

    public Context context;
    public ArrayList<modal> arr;

    public search_screen_adapter(Context context, ArrayList<modal> arr){
        this.arr = arr;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.text , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        modal mod = arr.get(position);
        Glide.with(context).load(mod.getThumbnail_image()).into(holder.img);
        String text = mod.getLabel();
        holder.textView.setText(text);
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.thumbnail);
            textView = itemView.findViewById(R.id.recipe_name);
        }
    }
}
