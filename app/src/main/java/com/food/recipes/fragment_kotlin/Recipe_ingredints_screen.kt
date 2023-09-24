package com.food.recipes.fragment_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.food.recipes.R

// TODO: Rename parameter arguments, choose names that match

class recipe_ingredints_screen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_recipe_ingredints_screen, container, false)

        var text = view.findViewById<TextView>(R.id.txt)


        return view
    }


}