package com.learn.foodapp.data.repository

import com.learn.foodapp.data.models.category.CategoryResponse
import com.learn.foodapp.data.models.detailmeal.DetailMealResponse
import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.data.models.meal.MealResponse
import com.learn.foodapp.data.repository.datasource.FoodRemoteDataSource
import com.learn.foodapp.data.repository.datasource.MealLocalDataSource
import com.learn.foodapp.data.util.Resource
import com.learn.foodapp.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class FoodRepositoryImpl(
    private val remoteDataSource: FoodRemoteDataSource,
    private val mealLocalDataSource: MealLocalDataSource
): FoodRepository {

    override suspend fun getCategories(): Resource<CategoryResponse> {
        return categoryResToResponse(remoteDataSource.getCategories())
    }

    private fun categoryResToResponse(response: Response<CategoryResponse>): Resource<CategoryResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return  Resource.Error(response.message())
    }

    override suspend fun getMeals(category: String): Resource<MealResponse> {
        return mealsResToResponse(remoteDataSource.getMeals(category))
    }

    private fun mealsResToResponse(response: Response<MealResponse>): Resource<MealResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getDetailMeal(id: String): Resource<DetailMealResponse> {
        return detailMealToResponse(remoteDataSource.getDetailMeal(id))
    }

    private fun detailMealToResponse(response: Response<DetailMealResponse>): Resource<DetailMealResponse> {
        if (response.isSuccessful) {
            response.body()?.let { return Resource.Success(it) }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getSearchMeal(s: String): Resource<DetailMealResponse> {
        return detailMealToResponse(remoteDataSource.getSearchMeal(s))
    }

    override suspend fun saveMeal(meal: MealData) {
        mealLocalDataSource.saveMeal(meal)
    }

    override suspend fun deleteMeal(id: String) {
        mealLocalDataSource.deleteMealData(id)
    }

    override fun getSavedMeals(): Flow<List<MealData>> {
        return mealLocalDataSource.getSavedMeal()
    }

    override fun getMealData(id: String): Flow<MealData> {
        return mealLocalDataSource.getMealData(id)
    }
}