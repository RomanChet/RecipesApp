package com.example.searchforrecipesapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchforrecipesapp.apiprocessing.ApiClient
import com.example.searchforrecipesapp.database.RecipesDatabase
import com.example.searchforrecipesapp.dataclasses.RecipeModel
import com.example.searchforrecipesapp.dataclasses.Recipes
import com.example.searchforrecipesapp.recyclerview.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var items: MutableList<Recipes> = ArrayList()
    private val db = RecipesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRecipes()
        val layoutManager = LinearLayoutManager(applicationContext)
        rv_recipes.layoutManager = layoutManager

        val myAdapter = MainAdapter(items, object : MainAdapter.Callback {
            override fun onItemClicked(item: Recipes) {
                val goDetailsActivityIntent = Intent(
                    applicationContext, MainDetailsActivity::class.java
                )
                db.insertFun(
                    applicationContext,
                    item.images[0],
                    item.name,
                    item.description,
                    item.instructions,
                    item.difficulty
                )
                startActivity(goDetailsActivityIntent)
            }
        })
        rv_recipes.adapter = myAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.to_name -> items.sortBy { it.name }
            R.id.to_date -> items.sortBy { it.lastUpdated }
        }
        rv_recipes.adapter?.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }

    // Реализация синхронного запроса
    private fun getRecipes() {
        val network = ApiClient()
        val call = network.getRecipes()
        call.enqueue(object : Callback<RecipeModel> {
            override fun onFailure(call: Call<RecipeModel>, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<RecipeModel>, response: Response<RecipeModel>) {
                progressBar_main.visibility = View.INVISIBLE
                val weather: RecipeModel? = response.body()
                if (weather != null) {
                    presentData(weather.recipes)
                }
            }
        })

    }

    private fun presentData(main: List<Recipes>) {
        var i = 0
        while (i <= main.lastIndex) {
            if (main[i].description != null && main[i].description != "") {
                items.add(
                    Recipes(
                        main[i].uuid,
                        main[i].name,
                        main[i].images,
                        main[i].lastUpdated,
                        main[i].description,
                        main[i].instructions,
                        main[i].difficulty
                    )
                )
            } else {
                items.add(
                    Recipes(
                        main[i].uuid,
                        main[i].name,
                        main[i].images,
                        main[i].lastUpdated,
                        main[i].name,
                        main[i].instructions,
                        main[i].difficulty
                    )
                )
            }
            i++
            rv_recipes.adapter?.notifyDataSetChanged()
        }
    }
}

