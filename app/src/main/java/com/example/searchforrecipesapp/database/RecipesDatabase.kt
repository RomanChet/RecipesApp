package com.example.searchforrecipesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecipesEntity::class], version = 1)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipesDao

    companion object {

        private var repository: RecipesDatabase? = null

        private fun getInstance(context: Context): RecipesDatabase {
            if (repository == null) {
                repository = Room
                    .databaseBuilder(
                        context.applicationContext,
                        RecipesDatabase::class.java,
                        "example"
                    )
                    .allowMainThreadQueries()
                    .build()
            }
            return repository as RecipesDatabase
        }

        private fun getDao(context: Context): RecipesDao {
            return getInstance(context).recipeDao()
        }

        private fun insert(
            context: Context,
            imageUrl: String,
            name: String,
            descr: String,
            instr: String,
            difficulty: Int
        ) {
            getDao(context).insert(RecipesEntity(imageUrl, name, descr, instr, difficulty))
        }

        private fun deleteAll(context: Context) {
            getDao(context).deleteAll()
        }

        fun getAll(context: Context): RecipesEntity {
            val db = getInstance(context).recipeDao()
            return db.getAll()[0]
        }

        fun insertFun(
            context: Context,
            imageUrl: String,
            name: String,
            descr: String,
            instr: String,
            difficulty: Int
        ) {
            deleteAll(context)
            insert(context, imageUrl, name, descr, instr, difficulty)
        }
    }
}