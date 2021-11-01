package com.learn.foodapp.presentation.detailmeal

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.foodapp.domain.usecase.DeleteFoodUseCase
import com.learn.foodapp.domain.usecase.GetDetailMealUseCase
import com.learn.foodapp.domain.usecase.GetSavedMealUseCase
import com.learn.foodapp.domain.usecase.SaveFoodUseCase

class DetailMealViewModelFactory(
    private val app: Application,
    private val useCase: GetDetailMealUseCase,
    private val saveFoodUseCase: SaveFoodUseCase,
    private val savedMealUseCase: GetSavedMealUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailMealViewModel(app, useCase, saveFoodUseCase, savedMealUseCase, deleteFoodUseCase) as T
    }

}