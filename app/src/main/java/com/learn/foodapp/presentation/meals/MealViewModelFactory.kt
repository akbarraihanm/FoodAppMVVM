package com.learn.foodapp.presentation.meals

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.foodapp.domain.usecase.GetFoodListUseCase

class MealViewModelFactory(
    private val app: Application,
    private val useCase: GetFoodListUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModel(app, useCase) as T
    }
}