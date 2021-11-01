package com.learn.foodapp.presentation.di

import com.learn.foodapp.data.db.MealDao
import com.learn.foodapp.data.repository.datasource.MealLocalDataSource
import com.learn.foodapp.data.repository.datasourceimpl.MealLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(mealDao: MealDao): MealLocalDataSource {
        return MealLocalDataSourceImpl(mealDao)
    }
}