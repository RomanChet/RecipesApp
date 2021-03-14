package com.example.searchforrecipesapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipesEntity(
    @PrimaryKey
    var imageUrl: String,
    var name: String,
    var descr: String,
    var instr: String,
    var difficulty: Int
)
