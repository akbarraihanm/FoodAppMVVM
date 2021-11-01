package com.learn.foodapp.domain.usecase

import com.learn.foodapp.domain.repository.FoodRepository

class DeleteFoodUseCase(private val repository: FoodRepository) {
    suspend fun execute(id: String) = repository.deleteMeal(id)
}