package com.learn.foodapp.presentation.di

import com.learn.foodapp.presentation.category.CategoryAdapter
import com.learn.foodapp.presentation.meals.MealAdapter
import com.learn.foodapp.presentation.search.SearchMealAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun provideCategoryAdapter(): CategoryAdapter {
        return CategoryAdapter()
    }

    @Provides
    @Singleton
    fun provideMealAdapter(): MealAdapter {
        return MealAdapter()
    }

    @Provides
    @Singleton
    fun provideSearchMealAdapter(): SearchMealAdapter {
        return SearchMealAdapter()
    }
}