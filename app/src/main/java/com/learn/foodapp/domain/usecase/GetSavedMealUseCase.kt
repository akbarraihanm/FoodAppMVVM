package com.learn.foodapp.domain.usecase

import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class GetSavedMealUseCase(private val repository: FoodRepository) {
    fun execute(id: String): Flow<MealData> = repository.getMealData(id)
}