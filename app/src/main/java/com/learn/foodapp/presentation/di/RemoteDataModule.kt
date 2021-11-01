package com.learn.foodapp.presentation.di

import com.learn.foodapp.data.api.FoodApiService
import com.learn.foodapp.data.repository.datasource.FoodRemoteDataSource
import com.learn.foodapp.data.repository.datasourceimpl.FoodRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideFoodRemoteDataSource(service: FoodApiService): FoodRemoteDataSource {
        return FoodRemoteDataSourceImpl(service)
    }

}