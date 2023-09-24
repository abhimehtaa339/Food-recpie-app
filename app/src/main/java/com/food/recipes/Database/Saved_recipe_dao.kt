package com.food.recipes.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.food.recipes.Modals.Saved_Recipe_Entity

@Dao
interface Saved_recipe_dao {
    @Insert
    suspend fun insert(savedRecipeEntity: Saved_Recipe_Entity)

    @Query("delete from recipe where url = :url")
    suspend fun delete(url : String)
    @Query("select * From Recipe")
    fun getData():LiveData<List<Saved_Recipe_Entity>>

    @Query("select exists(select * from Recipe where url = :url)")
    suspend fun getExist(url: String) : Boolean
//    abstract fun getExist(url: String): Boolean


}