package com.example.searchforrecipesapp.database

import androidx.room.*

@Dao
interface RecipesDao {
    @Query("SELECT * FROM recipesentity")
    fun getAll(): List<RecipesEntity>

    @Query("DELETE FROM recipesentity")
    fun deleteAll()

    @Insert
    fun insert(currententity: RecipesEntity)

    @Update
    fun update(currententity: RecipesEntity)

    @Delete
    fun delete(currententity: RecipesEntity)
}
