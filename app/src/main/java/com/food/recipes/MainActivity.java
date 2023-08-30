package com.food.recipes;

import static com.food.recipes.R.id.favourite;
import static com.food.recipes.R.id.home;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import android.content.Context;
import android.icu.lang.UCharacter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

   private BottomNavigationView bottomNavigationView;
   private FrameLayout frameLayout;

    private FragmentManager fragmentManager;
    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.tablayout);
        frameLayout = findViewById(R.id.viewpager);


        fragmentManager = getSupportFragmentManager();


       bottomNavigationView.setOnItemSelectedListener(item -> {
           if (item.getItemId() == R.id.home) {
               replaceFragment(new home_screen());
               return true;
           } else if (item.getItemId() == R.id.explore) {
               replaceFragment(new explore_screen());
               return true;
           } else if (item.getItemId() == R.id.favourite) {
               replaceFragment(new favourite_screen());
               return true;
           }else {
              replaceFragment(new home_screen());
               return true;
           }
       });
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.viewpager, fragment)
                .commit();
        currentFragment = fragment;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (isConnectedToInternet(this)) {

            Log.d("internet" , "Connected to internet");
        } else {

            Toast.makeText(this, "Not connected to the internet", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }


}
