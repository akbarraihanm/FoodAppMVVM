package com.learn.foodapp.domain.usecase

import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.domain.repository.FoodRepository

class SaveFoodUseCase(private val repository: FoodRepository) {
    suspend fun execute(meal: MealData) = repository.saveMeal(meal)
}