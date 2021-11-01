package com.learn.foodapp.presentation.savedmeals

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.foodapp.domain.usecase.DeleteFoodUseCase
import com.learn.foodapp.domain.usecase.GetSavedFoodUseCase
import com.learn.foodapp.domain.usecase.SaveFoodUseCase

class SavedFoodViewModelFactory(
    private val app: Application,
    private val useCase: GetSavedFoodUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase,
    private val saveFoodUseCase: SaveFoodUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SavedFoodViewModel(app, useCase, deleteFoodUseCase, saveFoodUseCase) as T
    }
}