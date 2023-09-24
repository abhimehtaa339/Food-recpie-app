package com.food.recipes.fragment_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.food.recipes.R


class recpie_Summary_Screen: Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_recpie__summary__screen, container, false)
//        val recipe_name  = view.findViewById<TextView>(R.id.recipe_txt)
        val recipe_image = view.findViewById<ImageView>(R.id.recipe_image)


//        recipe_name.text = arguments?.getString("label").toString()
        Glide.with(this).load(arguments?.getString("image").toString()).into(recipe_image)



        return view
    }




}