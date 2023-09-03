package com.food.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class splash_screen extends AppCompatActivity {
    private ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        loading = findViewById(R.id.loading);

        Sprite threedot = new ThreeBounce();
        threedot.setColor(getResources().getColor(R.color.loading , getTheme()));
        loading.setIndeterminateDrawable(threedot);

      new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {

              startActivity(new Intent(splash_screen.this , MainActivity.class));
              finish();
          }
      } , 4000);
    }
}