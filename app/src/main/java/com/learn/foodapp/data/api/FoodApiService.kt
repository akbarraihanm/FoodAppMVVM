package com.learn.foodapp.data.api

import com.learn.foodapp.data.models.category.CategoryResponse
import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.models.meal.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApiService {
    @GET("categories.php")
    suspend fun getCategories(): Response<CategoryResponse>
    @GET("filter.php")
    suspend fun getMeals(@Query("c") category: String): Response<MealResponse>
    @GET("lookup.php")
    suspend fun getDetailMeal(@Query("i") id: String): Response<DetailMealResponse>
    @GET("search.php")
    suspend fun getSearchMeal(@Query("s")s: String): Response<DetailMealResponse>
}