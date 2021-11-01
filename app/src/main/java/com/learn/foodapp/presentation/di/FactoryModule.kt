package com.learn.foodapp.presentation.di

import android.app.Application
import com.learn.foodapp.domain.usecase.*
import com.learn.foodapp.presentation.category.CategoryViewModelFactory
import com.learn.foodapp.presentation.detailmeal.DetailMealViewModelFactory
import com.learn.foodapp.presentation.meals.MealViewModelFactory
import com.learn.foodapp.presentation.savedmeals.SavedFoodViewModelFactory
import com.learn.foodapp.presentation.search.SearchMealViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun provideCategoryViewModelFactory(app: Application, useCase: GetFoodCategoryUseCase):
            CategoryViewModelFactory {
        return CategoryViewModelFactory(app, useCase)
    }

    @Provides
    @Singleton
    fun provideMealViewModelFactory(app: Application, useCase: GetFoodListUseCase):
            MealViewModelFactory {
        return MealViewModelFactory(app, useCase)
    }

    @Provides
    @Singleton
    fun provideDetailMealViewModelFactory(
        app: Application,
        useCase: GetDetailMealUseCase,
        saveFoodUseCase: SaveFoodUseCase,
        savedMealUseCase: GetSavedMealUseCase,
        deleteFoodUseCase: DeleteFoodUseCase
    ):
            DetailMealViewModelFactory {
        return DetailMealViewModelFactory(app, useCase, saveFoodUseCase, savedMealUseCase, deleteFoodUseCase)
    }

    @Provides
    @Singleton
    fun provideSearchMealViewModelFactory(app: Application, useCase: SearchMealUseCase):
            SearchMealViewModelFactory {
        return SearchMealViewModelFactory(app, useCase)
    }

    @Provides
    @Singleton
    fun provideSavedFoodViewModelFactory(
        app: Application,
        useCase: GetSavedFoodUseCase,
        deleteFoodUseCase: DeleteFoodUseCase,
        saveFoodUseCase: SaveFoodUseCase
    ):
            SavedFoodViewModelFactory {
        return SavedFoodViewModelFactory(app, useCase, deleteFoodUseCase, saveFoodUseCase)
    }
}