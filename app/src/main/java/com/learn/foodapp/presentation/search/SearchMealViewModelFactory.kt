package com.learn.foodapp.presentation.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.foodapp.domain.usecase.SearchMealUseCase

class SearchMealViewModelFactory(
    private val app: Application,
    private val useCase: SearchMealUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchMealViewModel(app, useCase) as T
    }
}