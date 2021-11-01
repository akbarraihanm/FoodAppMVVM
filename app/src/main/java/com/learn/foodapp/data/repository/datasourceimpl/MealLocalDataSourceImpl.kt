package com.learn.foodapp.data.repository.datasourceimpl

import com.learn.foodapp.data.db.MealDao
import com.learn.foodapp.data.models.meal.MealData
import com.learn.foodapp.data.repository.datasource.MealLocalDataSource
import kotlinx.coroutines.flow.Flow

class MealLocalDataSourceImpl(
    private val dao: MealDao
): MealLocalDataSource {
    override suspend fun saveMeal(meal: MealData) {
        dao.insert(meal)
    }

    override fun getSavedMeal(): Flow<List<MealData>> {
        return dao.getMeals()
    }

    override fun getMealData(id: String): Flow<MealData> {
        return dao.getMealData(id)
    }

    override suspend fun deleteMealData(id: String) {
        dao.deleteMealData(id)
    }
}