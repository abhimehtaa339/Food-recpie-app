package com.food.recipes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.food.recipes.Database.Recipe_database_bulider
import com.food.recipes.Modals.Saved_Recipe_Entity
import com.food.recipes.fragment_kotlin.recipe_ingredints_screen
import com.food.recipes.fragment_kotlin.recpie_Summary_Screen
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class information_screen : AppCompatActivity() {

    private var isIconChanged = true
    private lateinit var label : String
    private lateinit var image : String
    private lateinit var url : String
    private lateinit var database : Recipe_database_bulider
    private var exist = true
    private lateinit var topbar : MaterialToolbar
    private val uiScope = CoroutineScope(Dispatchers.IO)
    private var isSelected = false

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_screen)

        val backbutton = findViewById<ImageButton>(R.id.backbutton)
        val sharebutton = findViewById<ImageButton>(R.id.sharebutton)
        val favourite = findViewById<ImageButton>(R.id.favourite)
        val viewpager = findViewById<FrameLayout>(R.id.viewpager)
        val navigation = findViewById<BottomNavigationView>(R.id.bottombar)

        val extras = intent.extras
            label = extras?.getString("name").toString()
            image = extras?.getString("image").toString()
            url = extras?.getString("url").toString()
            Log.d("label" , label)


        navigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener{ item ->
            when(item.itemId){
                R.id.summary -> {
                    replaceFragment(recpie_Summary_Screen())
                    true
                }
                R.id.ingredients ->{
                    replaceFragment(recipe_ingredints_screen())
                    true
                }
                else -> false
            }
        })
        navigation.selectedItemId = R.id.summary


        database = Recipe_database_bulider.getDatabase(this)


        backbutton.setOnClickListener{
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        sharebutton.setOnClickListener{
            val i = Intent(Intent.ACTION_SEND)
            i.putExtra(Intent.EXTRA_TEXT , url)
            i.type = "text/plain"
            startActivity(Intent.createChooser(i , "Share via"))
        }

        GlobalScope.launch {
            if (database.SavedDao().getExist(image)){
                favourite.setImageResource(R.drawable.baseline_bookmark_24)
                isSelected = !isSelected
            }else{
                favourite.setImageResource(R.drawable.outline_bookmark_border_24)
            }
        }



        favourite.setOnClickListener{
            isSelected = !isSelected
            if(!isSelected){
                favourite.setImageResource(R.drawable.outline_bookmark_border_24)
                GlobalScope.launch {
                    database.SavedDao().delete(image)
                }
            }else{
                favourite.setImageResource(R.drawable.baseline_bookmark_24)
                GlobalScope.launch {
                    database.SavedDao().insert(Saved_Recipe_Entity(0 , label , image))
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("label" , label)
        bundle.putString("image" , image)
        fragment.arguments = bundle
       supportFragmentManager.beginTransaction().replace(R.id.viewpager , fragment).commit()
    }
}


