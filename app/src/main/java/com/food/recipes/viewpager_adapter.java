package com.food.recipes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class viewpager_adapter extends FragmentStateAdapter {
    public viewpager_adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: {
                return new home_screen();
            }
            case 1:{
                return new explore_screen();
            }
            case 2:{
                return new favourite_screen();
            }
            default:
                return new home_screen();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
