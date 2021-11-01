package com.learn.foodapp.data.repository.datasourceimpl

import com.learn.foodapp.data.api.FoodApiService
import com.learn.foodapp.data.models.category.CategoryResponse
import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.models.meal.MealResponse
import com.learn.foodapp.data.repository.datasource.FoodRemoteDataSource
import retrofit2.Response

class FoodRemoteDataSourceImpl(
    private val service: FoodApiService
): FoodRemoteDataSource {

    override suspend fun getCategories(): Response<CategoryResponse> = service.getCategories()

    override suspend fun getMeals(category: String): Response<MealResponse> =
        service.getMeals(category)

    override suspend fun getDetailMeal(id: String): Response<DetailMealResponse> =
        service.getDetailMeal(id)

    override suspend fun getSearchMeal(s: String): Response<DetailMealResponse> =
        service.getSearchMeal(s)
}