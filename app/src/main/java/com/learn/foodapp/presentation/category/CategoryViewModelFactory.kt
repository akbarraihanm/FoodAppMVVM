package com.learn.foodapp.presentation.category

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.foodapp.domain.usecase.GetFoodCategoryUseCase

class CategoryViewModelFactory(
    private val app: Application,
    private val getFoodCategoryUseCase: GetFoodCategoryUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(app, getFoodCategoryUseCase) as T
    }

}