package com.learn.foodapp.data.repository.datasource

import com.learn.foodapp.data.models.meal.MealData
import kotlinx.coroutines.flow.Flow

interface MealLocalDataSource {
    suspend fun saveMeal(meal: MealData)
    fun getSavedMeal(): Flow<List<MealData>>
    fun getMealData(id: String): Flow<MealData>
    suspend fun deleteMealData(id: String)
}