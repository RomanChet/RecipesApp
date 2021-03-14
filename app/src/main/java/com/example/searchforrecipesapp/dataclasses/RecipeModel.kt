package com.example.searchforrecipesapp.dataclasses

data class RecipeModel(
    val recipes: List<Recipes>
)

data class Recipes(
    val uuid: String,
    val name: String,
    val images: List<String>,
    val lastUpdated: Int,
    val description: String,
    val instructions: String,
    val difficulty: Int
)

