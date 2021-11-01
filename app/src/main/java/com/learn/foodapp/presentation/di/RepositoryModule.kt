package com.learn.foodapp.presentation.di

import com.learn.foodapp.data.repository.FoodRepositoryImpl
import com.learn.foodapp.data.repository.datasource.FoodRemoteDataSource
import com.learn.foodapp.data.repository.datasource.MealLocalDataSource
import com.learn.foodapp.domain.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideFoodRepository(
        remoteDataSource: FoodRemoteDataSource,
        mealLocalDataSource: MealLocalDataSource
    ): FoodRepository {
        return FoodRepositoryImpl(remoteDataSource, mealLocalDataSource)
    }
}