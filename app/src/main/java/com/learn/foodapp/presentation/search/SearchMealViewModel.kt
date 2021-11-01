package com.learn.foodapp.presentation.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.util.AppUtil
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.usecase.SearchMealUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchMealViewModel(
    private val app: Application,
    private val useCase: SearchMealUseCase
): AndroidViewModel(app) {

    val searchMeal: MutableLiveData<Resource<DetailMealResponse>> = MutableLiveData()

    fun searchMeal(s: String) = viewModelScope.launch(Dispatchers.IO) {
        searchMeal.postValue(Resource.Loading())
        try {
            if (AppUtil.isNetworkAvailable(app.applicationContext)) {
                val result = useCase.execute(s)
                searchMeal.postValue(result)
            } else {
                searchMeal.postValue(Resource.Error("Internet is unavailable. " +
                        "Please turn it on"))
            }
        } catch (e: Exception) {
            searchMeal.postValue(Resource.Error("${e.message}"))
        }
    }

}