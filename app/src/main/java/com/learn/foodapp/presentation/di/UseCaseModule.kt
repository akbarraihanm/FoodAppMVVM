package com.learn.foodapp.presentation.di

import com.learn.foodapp.domain.repository.FoodRepository
import com.learn.foodapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetFoodCategoryUseCase(repository: FoodRepository): GetFoodCategoryUseCase {
        return GetFoodCategoryUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetFoodListUseCase(repository: FoodRepository): GetFoodListUseCase {
        return GetFoodListUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetDetailMealUseCase(repository: FoodRepository): GetDetailMealUseCase {
        return GetDetailMealUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSearchMealUseCase(repository: FoodRepository): SearchMealUseCase {
        return SearchMealUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveMealUseCase(repository: FoodRepository): SaveFoodUseCase {
        return SaveFoodUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSavedFoodUseCase(repository: FoodRepository): GetSavedFoodUseCase {
        return GetSavedFoodUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSavedMealDataUseCase(repository: FoodRepository): GetSavedMealUseCase {
        return GetSavedMealUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteMealUseCase(repository: FoodRepository): DeleteFoodUseCase {
        return DeleteFoodUseCase(repository)
    }
}