package com.food.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class instruction_screen extends AppCompatActivity {
        private TextView name;
        private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_screen);

        name = findViewById(R.id.recipe_name);
        image = findViewById(R.id.image);

        Intent intent = new Intent();
        intent = getIntent();
        String label = intent.getStringExtra("name");
        String url = intent.getStringExtra("image");

        name.setText(label);
        Glide.with(getApplicationContext()).load(url).placeholder(getDrawable(R.drawable.ic_launcher_background)).into(image);





    }
}