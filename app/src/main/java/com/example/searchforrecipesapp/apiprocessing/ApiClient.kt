package com.example.searchforrecipesapp.apiprocessing

import com.example.searchforrecipesapp.dataclasses.RecipeModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val api = Retrofit.Builder()
        .baseUrl("https://test.kode-t.ru/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    fun getRecipes(): Call<RecipeModel> {
        return api.getRecipesCall()
    }
}