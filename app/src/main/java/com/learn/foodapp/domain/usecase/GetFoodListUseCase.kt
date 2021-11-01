package com.learn.foodapp.domain.usecase

import com.learn.foodapp.data.models.meal.MealResponse
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.repository.FoodRepository

class GetFoodListUseCase(private val repository: FoodRepository) {
    suspend fun execute(category: String): Resource<MealResponse> {
        return repository.getMeals(category)
    }
}