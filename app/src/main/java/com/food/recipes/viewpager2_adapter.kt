package com.food.recipes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class viewpager2_adapter(fragmentManager : FragmentManager , lifeycle : Lifecycle) :
    FragmentStateAdapter(fragmentManager , lifeycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> recpie_Summary_Screen()
            1 -> recipe_ingredints_screen()
            else -> recpie_Summary_Screen()
        }
    }
}