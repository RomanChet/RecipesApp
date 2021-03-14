package com.example.searchforrecipesapp.apiprocessing

import com.example.searchforrecipesapp.dataclasses.RecipeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/recipes")
    fun getRecipesCall(): Call<RecipeModel>
}