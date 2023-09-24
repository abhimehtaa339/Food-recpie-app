package com.food.recipes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.food.recipes.R;
import com.food.recipes.information_screen;
import com.food.recipes.Modals.modal;

import java.util.ArrayList;

public class custom_adapter extends RecyclerView.Adapter<custom_adapter.viewHolder> {
    public   Context context;
    public ArrayList<modal> arr;

    public custom_adapter(Context context, ArrayList<modal> arr) {
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public custom_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.text, parent , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull custom_adapter.viewHolder holder, int position) {
        modal mod = arr.get(position);
        Glide.with(context).load(mod.getThumbnail_image()).into(holder.img);
        String text = mod.getLabel();
        holder.textView.setText(text);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context , information_screen.class);
                i.putExtra("name" , mod.getLabel());
                i.putExtra("image" , mod.getThumbnail_image());
                i.putExtra("url" , mod.getUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {

        return arr.size();
    }

    public  class viewHolder extends RecyclerView.ViewHolder {
       private ImageView img;
       private TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.thumbnail);
            textView = itemView.findViewById(R.id.recipe_name);

        }
    }
}
