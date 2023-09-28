package com.food.recipes.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.food.recipes.Database.Recipe_database_bulider
import com.food.recipes.Modals.Saved_Recipe_Entity
import com.food.recipes.Modals.modal
import com.food.recipes.R

class favouritescreen_adapter(private var data: MutableList<Saved_Recipe_Entity> , val context : Context) : RecyclerView.Adapter<favouritescreen_adapter.ViewHolder>() {
    private lateinit var database : Recipe_database_bulider
    inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val recipimg: ImageView = itemview.findViewById(R.id.recipimg)
        val reciplabel = itemview.findViewById<TextView>(R.id.heading)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view : View = LayoutInflater.from(parent.context).inflate(R.layout.favouriteitem_list, parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = data[position]
        Glide.with(context).load(pos.url).into(holder.recipimg)
        Log.d("img" , pos.url)
        holder.reciplabel.text = pos.name
    }

    fun updateUser(newrecip : List<Saved_Recipe_Entity>){
        data = newrecip.toMutableList()
        Log.d("data" , data.toString())
        notifyDataSetChanged()
    }

}


