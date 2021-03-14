package com.example.searchforrecipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.searchforrecipesapp.database.RecipesDatabase
import kotlinx.android.synthetic.main.activity_delails.*

class MainDetailsActivity : AppCompatActivity() {

    private val db = RecipesDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delails)

        // Загрузка изображения с сервера по URL
        Glide.with(details_image).load(db.getAll(this).imageUrl).into(details_image)

        details_name.text = db.getAll(this).name
        details_descr.text = db.getAll(this).descr

        // Реализация корректного переноса строки
        val instr = db.getAll(this).instr
        val instrReplace = instr.replace("<br>", "\n")
        details_instruct.text = instrReplace

        ratingBarSecond.rating = db.getAll(this).difficulty.toFloat()
    }
}