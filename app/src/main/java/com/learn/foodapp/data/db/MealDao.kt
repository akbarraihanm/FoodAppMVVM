package com.learn.foodapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learn.foodapp.data.models.meal.MealData
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(meal: MealData)

    @Query("SELECT * FROM meals")
    fun getMeals(): Flow<List<MealData>>

    @Query("SELECT * FROM meals WHERE idMeal = :id")
    fun getMealData(id: String): Flow<MealData>

    @Query("DELETE FROM meals WHERE idMeal = :id")
    suspend fun deleteMealData(id: String)
}