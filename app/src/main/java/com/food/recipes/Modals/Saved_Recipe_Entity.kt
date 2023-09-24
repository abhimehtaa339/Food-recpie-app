package com.food.recipes.Modals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe")
data class Saved_Recipe_Entity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val name : String,
    val url : String
)
