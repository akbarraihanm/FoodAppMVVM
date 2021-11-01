package com.learn.foodapp.presentation.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learn.foodapp.data.models.category.CategoryResponse
import com.learn.foodapp.data.util.AppUtil
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.usecase.GetFoodCategoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val app: Application,
    private val getFoodCategoryUseCase: GetFoodCategoryUseCase): AndroidViewModel(app) {
    val categories: MutableLiveData<Resource<CategoryResponse>> = MutableLiveData()

    fun getCategories() = viewModelScope.launch(Dispatchers.IO) {
        categories.postValue(Resource.Loading())
        try {
            if (AppUtil.isNetworkAvailable(app.applicationContext)) {
                val apiResult = getFoodCategoryUseCase.execute()
                categories.postValue(apiResult)
            } else {
                categories.postValue(Resource.Error("Internet is unavailable. " +
                        "Please turn it on"))
            }
        } catch (e: Exception) {
            categories.postValue(Resource.Error("${e.message}"))
        }
    }

}