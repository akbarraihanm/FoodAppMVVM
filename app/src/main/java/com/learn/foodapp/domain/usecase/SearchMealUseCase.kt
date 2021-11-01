package com.learn.foodapp.domain.usecase

import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.repository.FoodRepository

class SearchMealUseCase(private val repository: FoodRepository) {
    suspend fun execute(s: String): Resource<DetailMealResponse> {
        return repository.getSearchMeal(s)
    }
}