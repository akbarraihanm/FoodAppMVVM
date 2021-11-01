package com.learn.foodapp.presentation.meals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learn.foodapp.data.models.meal.MealResponse
import com.learn.foodapp.data.util.AppUtil
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.usecase.GetFoodListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealViewModel(
    private val app: Application,
    private val useCase: GetFoodListUseCase
): AndroidViewModel(app) {

    val meals: MutableLiveData<Resource<MealResponse>> = MutableLiveData()

    fun getMeals(category: String) = viewModelScope.launch(Dispatchers.IO) {
        meals.postValue(Resource.Loading())
        try {
            if (AppUtil.isNetworkAvailable(app.applicationContext)) {
                val result = useCase.execute(category)
                meals.postValue(result)
            } else {
                meals.postValue(Resource.Error("Internet is unavailable. " +
                        "Please turn it on"))
            }
        } catch (e: Exception) {
            meals.postValue(Resource.Error("${e.message}"))
        }
    }
}