package com.food.recipes

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton

class information_screen : AppCompatActivity() {

    private var isIconChanged = false
    private lateinit var adapter: FragmentStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_screen)

//       val label: TextView= findViewById(R.id.label)
//       val image: ImageView = findViewById(R.id.image)
//
//       val receivedIntent = intent
//        val name = receivedIntent.getStringExtra("name")
//        label.text = name
//       Glide.with(applicationContext).load(receivedIntent.getStringExtra("image")).placeholder(getDrawable(R.drawable.ic_launcher_background)).into(image)
        val topbar = findViewById<MaterialToolbar>(R.id.topbar)
        var viewpager = findViewById<ViewPager2>(R.id.viewpager)

        adapter = viewpager2_adapter(supportFragmentManager , lifecycle)
        viewpager.adapter = adapter

        setSupportActionBar(topbar)
        topbar.setNavigationOnClickListener{
            val intent =  Intent(this , MainActivity::class.java )
            startActivity(intent)
        }


        topbar.setOnMenuItemClickListener {menuItem ->
            when(menuItem.itemId){
                R.id.sharebutton ->{
                    Toast.makeText(this , "working" , Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.favourite ->{
                    isIconChanged = !isIconChanged
                    if (isIconChanged){
                        menuItem.icon = getDrawable(R.drawable.baseline_bookmark_24)
                        Toast.makeText(this , "Recipe saved in Favourites" , Toast.LENGTH_SHORT).show()
                    }else{
                        menuItem.icon = getDrawable(R.drawable.outline_bookmark_border_24)
                        Toast.makeText(this , "Recipe removed from Favourites" , Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else -> false
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar , menu)
        return true
    }

}


