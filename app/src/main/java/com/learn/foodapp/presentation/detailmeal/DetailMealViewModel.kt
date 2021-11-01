package com.learn.foodapp.presentation.detailmeal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.data.util.AppUtil
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.usecase.DeleteFoodUseCase
import com.learn.foodapp.domain.usecase.GetDetailMealUseCase
import com.learn.foodapp.domain.usecase.GetSavedMealUseCase
import com.learn.foodapp.domain.usecase.SaveFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailMealViewModel(
    private val app: Application,
    private val useCase: GetDetailMealUseCase,
    private val saveMealUseCase: SaveFoodUseCase,
    private val savedMealDataUseCase: GetSavedMealUseCase,
    private val deleteMealUseCase: DeleteFoodUseCase
): AndroidViewModel(app) {

    val detail: MutableLiveData<Resource<DetailMealResponse>> = MutableLiveData()

    fun getDetailMeal(id: String) = viewModelScope.launch(Dispatchers.IO) {
        detail.postValue(Resource.Loading())
        try {
            if (AppUtil.isNetworkAvailable(app.applicationContext)) {
                val result = useCase.execute(id)
                detail.postValue(result)
            } else {
                detail.postValue(Resource.Error("Internet is unavailable. " +
                        "Please turn it on"))
            }
        } catch (e: Exception) {
            detail.postValue(Resource.Error("${e.message}"))
        }
    }

    fun saveMeal(meal: MealData) = viewModelScope.launch {
        saveMealUseCase.execute(meal)
    }

    fun getMeal(id: String) = liveData {
        savedMealDataUseCase.execute(id).collect {
            emit(it)
        }
    }

    fun deleteMeal(id: String) = viewModelScope.launch {
        deleteMealUseCase.execute(id)
    }
}