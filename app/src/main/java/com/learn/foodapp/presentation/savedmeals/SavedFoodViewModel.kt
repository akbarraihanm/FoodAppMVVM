package com.learn.foodapp.presentation.savedmeals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.domain.usecase.DeleteFoodUseCase
import com.learn.foodapp.domain.usecase.GetSavedFoodUseCase
import com.learn.foodapp.domain.usecase.SaveFoodUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SavedFoodViewModel(
    app: Application,
    private val useCase: GetSavedFoodUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase,
    private val saveFoodUseCase: SaveFoodUseCase
): AndroidViewModel(app) {

    fun getSavedMeals() = liveData {
        useCase.execute().collect {
            emit(it)
        }
    }

    fun saveMeal(meal: MealData) = viewModelScope.launch {
        saveFoodUseCase.execute(meal)
    }

    fun deleteMeal(id: String) = viewModelScope.launch {
        deleteFoodUseCase.execute(id)
    }

}