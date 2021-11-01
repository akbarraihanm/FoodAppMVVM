package com.learn.foodapp.domain.usecase

import com.learn.foodapp.data.models.category.CategoryResponse
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.repository.FoodRepository

class GetFoodCategoryUseCase(private val repository: FoodRepository) {
    suspend fun execute(): Resource<CategoryResponse> {
        return repository.getCategories()
    }
}