package com.learn.foodapp.data.repository.datasource

import com.learn.foodapp.data.models.category.CategoryResponse
import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.models.meal.MealResponse
import retrofit2.Response

interface FoodRemoteDataSource {
    suspend fun getCategories(): Response<CategoryResponse>
    suspend fun getMeals(category: String): Response<MealResponse>
    suspend fun getDetailMeal(id: String): Response<DetailMealResponse>
    suspend fun getSearchMeal(s: String): Response<DetailMealResponse>
}