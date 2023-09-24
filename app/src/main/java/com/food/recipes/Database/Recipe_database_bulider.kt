package com.food.recipes.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.food.recipes.Modals.Saved_Recipe_Entity

@Database(entities = [Saved_Recipe_Entity::class] , version = 1)
abstract class Recipe_database_bulider : RoomDatabase() {

    abstract fun SavedDao() : Saved_recipe_dao

    companion object{
        @Volatile
        private var Instance : Recipe_database_bulider? = null

        fun getDatabase(context : Context): Recipe_database_bulider {
            if (Instance == null){
                synchronized(this){
                    Instance = Room.databaseBuilder(context.applicationContext , Recipe_database_bulider::class.java , "Saved Recpies").build()
                }
            }
            return Instance!!
        }
    }
}