package com.learn.foodapp.domain.repository

import com.learn.foodapp.data.models.category.CategoryResponse
import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.data.models.meal.MealResponse
import com.learn.foodapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    suspend fun getCategories(): Resource<CategoryResponse>
    suspend fun getMeals(category: String): Resource<MealResponse>
    suspend fun getDetailMeal(id: String): Resource<DetailMealResponse>
    suspend fun getSearchMeal(s: String): Resource<DetailMealResponse>
    suspend fun saveMeal(meal: MealData)
    suspend fun deleteMeal(id: String)
    fun getSavedMeals(): Flow<List<MealData>>
    fun getMealData(id: String): Flow<MealData>
}